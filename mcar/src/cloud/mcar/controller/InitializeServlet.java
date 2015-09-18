package cloud.mcar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cloud.mcar.db.ConnectionPool;
import cloud.mcar.db.TestDBpool;
import cloud.mcar.model.Credential;
import cloud.mcar.model.Driver;
import cloud.mcar.model.Request;

/**
 * Servlet implementation class InitializeServlet
 */
@WebServlet(value = "/handleServlet", loadOnStartup = 1)
public class InitializeServlet extends HttpServlet {
	private double latitude;
	private double longitude;
	private static final long serialVersionUID = 1L;
	private List<Request> requestList = new ArrayList<Request>();
	private Request acceptedRequest;
	private int i = 0;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitializeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		ConnectionPool.initialize();
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(request.getInputStream());
		StringBuilder builder = new StringBuilder();
		while (scan.hasNextLine()) {
			builder.append(scan.nextLine());
		}
		System.out.println("here");
		String jsonData = builder.toString();
		System.out.println(jsonData);
		JSONObject jsonObject = new JSONObject(jsonData);
		String responseJsonData = "";
		String cmd = jsonObject.getString("cmd");
		switch (cmd) {
		case "Update Location":
			responseJsonData = handleDriverUpdateLocation(jsonObject);
			break;
		case "requestDriverLocation":
			responseJsonData = handleCustomerUpdateLocation(jsonObject);
			break;
		case "Driver Registion":
			responseJsonData = handleDriverRegistion(jsonObject);
			break;
		case "Drive Login":
			responseJsonData = handleDriverLogin(jsonObject);
			break;
		case "Fetch Order":
			responseJsonData = HandleDriverRequestOrder(jsonObject);
			break;
		case "requestcar":
			responseJsonData = HandleUserRequestOrder(jsonObject);
			break;
		case "requestStatus":
			responseJsonData = HandleRequestStatus(jsonObject);
			break;
//		case "requestDriverAccept":
//			responseJsonData = HandlerequestDriverAccept(jsonObject);
//			break;
		default:
			break;
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(responseJsonData);
		out.close();
	}

	String handleDriverUpdateLocation(JSONObject jsonObject) {
		acceptedRequest = requestList.get(0);
		System.out.println("lat: " + jsonObject.getDouble("Latitute")
				+ " lng: " + jsonObject.getDouble("Longitude"));
		latitude = jsonObject.getDouble("Latitute");
		longitude = jsonObject.getDouble("Longitude");
		JSONObject tmpJson = new JSONObject();
		tmpJson.put("status", "success");
		return tmpJson.toString();
	}
	String handleCustomerUpdateLocation(JSONObject jsonObject){
		JSONObject tmpJson = new JSONObject();
		tmpJson.put("status", "updatedLocation");
		tmpJson.put("latitude", latitude);
		tmpJson.put("longitude", longitude);
		return tmpJson.toString();
	}
	String handleDriverRegistion(JSONObject jsonObject) {
		String username = jsonObject.getString("username");
		Connection conn;
		JSONObject tmpJson = new JSONObject();
		try {
			conn = ConnectionPool.datasource.getConnection();
			if (TestDBpool.isUsernameExist(conn, username)) {
				tmpJson.put("status", "fail");
			} else {
				Credential credential = new Credential(username,
						jsonObject.getString("password"));
				TestDBpool.addCredential(conn, credential);
				credential = TestDBpool.getCredential(conn, username);
				Driver driver = new Driver(
						jsonObject.getString("driverlicenseID"),
						jsonObject.getString("firstname")
								+ jsonObject.getString("lastName"),
						jsonObject.getString("plat"),
						jsonObject.getString("Phone"), 0, 0, 0, 0,
						credential.getId());
				TestDBpool.addDriver(conn, driver);
				driver = TestDBpool.getDriver(conn, credential.getId());
				tmpJson.put("status", "success");
				tmpJson.put("driverid", driver.getDriverID());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("addDriverJson Response:" + tmpJson.toString());
		return tmpJson.toString();
	}

	String handleDriverLogin(JSONObject jsonObject) {
		String username = jsonObject.getString("username");
		String password = jsonObject.getString("pass");
		Connection conn;
		JSONObject tmpJson = new JSONObject();
		try {
			conn = ConnectionPool.datasource.getConnection();
			if (TestDBpool.isUsernameExist(conn, username)) {
				Credential credential = TestDBpool
						.getCredential(conn, username);
				if (credential.getPassword().equals(password)) {
					TestDBpool.addLogin(conn, credential.getId());
					tmpJson.put("status", "success");
					tmpJson.put("id", credential.getId());
				}else{
					tmpJson.put("status", "passwordfail");
				}
			} else {
				tmpJson.put("status", "usernamefail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DriverLogin Response:" + tmpJson.toString());
		return tmpJson.toString();
	}
	
	String HandleDriverRequestOrder(JSONObject jsonObject){
//		String username = jsonObject.getString("username");
		String keyName = "order";
		JSONObject outJson = new JSONObject();
		System.out.println("requestList size: "+requestList.size());
		for(int i = 0; i < requestList.size(); ++i){
			Request tempRequest = requestList.get(i);
			JSONObject tmpJson = new JSONObject();
			tmpJson.put("fromLati", tempRequest.getPickUplat());
			tmpJson.put("fromLong", tempRequest.getPickUpLng());
			tmpJson.put("toLati", tempRequest.getDestLat());
			tmpJson.put("toLong", tempRequest.getDestLng());
			tmpJson.put("from", tempRequest.getPickUpAddress());
			tmpJson.put("to",tempRequest.getDestination());
			tmpJson.put("customerID",tempRequest.getCustomerID());
			outJson.put(keyName+i, tmpJson.toString());
		}
		return outJson.toString();
	}
	String HandleUserRequestOrder(JSONObject jsonObject){
//		String username = jsonObject.getString("username");
		Request request = new Request(2,jsonObject.getDouble("pickuplat"),jsonObject.getDouble("pickuplng"),jsonObject.getDouble("destlat"),jsonObject.getDouble("destlng"),jsonObject.getString("pickupAdd"),jsonObject.getString("destAdd"));
		requestList.add(request);
		System.out.println(requestList.size());
		JSONObject tmpJson = new JSONObject();
		tmpJson.put("status", "pending");
		return tmpJson.toString();
	}
	
	String HandleRequestStatus(JSONObject jsonObject){
		JSONObject tmpJson = new JSONObject();
		if(acceptedRequest != null){
			tmpJson.put("status", "requestAccepted");
		}
		else{
			tmpJson.put("status", "pending");
		}
		return tmpJson.toString();
	}
	
//	String HandlerequestDriverAccept(JSONObject jsonObject){
//		int index = jsonObject.getInt("acceptedIndex");
//		acceptedRequest = requestList.get(index);
//		JSONObject tmpJson = new JSONObject();
//		tmpJson.put("status", "OK");
//		return tmpJson.toString();
//	}
}
