package cloud.mcar.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cloud.mcar.model.Credential;
import cloud.mcar.model.Customer;
import cloud.mcar.model.Driver;
import cloud.mcar.model.Order;
import cloud.mcar.model.Query;
import cloud.mcar.model.Request;

public class TestDBpool {
    
	public static void main(String[] args) throws Exception {
		// PoolProperties p = new PoolProperties();
		// // p.setUrl("jdbc:mysql://localhost:3306/mysql");
		// p.setUrl("jdbc:mysql://mcardb.cxh0fcrzaysq.us-east-1.rds.amazonaws.com:3306/testmcar");
		// p.setDriverClassName("com.mysql.jdbc.Driver");
		// p.setUsername("mcaruser");
		// p.setPassword("mcar123456");
		// p.setJmxEnabled(true);
		// p.setTestWhileIdle(false);
		// p.setTestOnBorrow(true);
		// p.setValidationQuery("SELECT 1");
		// p.setTestOnReturn(false);
		// p.setValidationInterval(30000);
		// p.setTimeBetweenEvictionRunsMillis(30000);
		// p.setMaxActive(100);
		// p.setInitialSize(10);
		// p.setMaxWait(10000);
		// p.setRemoveAbandonedTimeout(60);
		// p.setMinEvictableIdleTimeMillis(30000);
		// p.setMinIdle(10);
		// p.setLogAbandoned(true);
		// p.setRemoveAbandoned(true);
		// p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
		// "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		// DataSource datasource = new DataSource();
		// datasource.setPoolProperties(p);
		ConnectionPool.initialize();
		Connection conn = null;
		Credential credential = null;
		Customer customer = null;
		Driver driver = null;
		try {
			conn = ConnectionPool.datasource.getConnection();
			// credential = new Credential("mike","miaa");
			// addCredential(conn, credential);
			credential = getCredential(conn, "gengsb");
			System.out.println("id: " + credential.getId() + " username: "
					+ credential.getUsername() + " password: "
					+ credential.getPassword());
			addLogin(conn, credential.getId());
			// customer = new Customer("geng", "1972 70 th street",
			// "9178611341", credential.getId());
			// addCustomer(conn, customer);
			customer = getCustomer(conn, credential.getId());
			System.out.println("customerID: " + customer.getCustomerid()
					+ " customerName: " + customer.getCustomerName()
					+ " phoneNumber: " + customer.getPhoneNumber()
					+ " address: " + customer.getAddress());
			// driver = new Driver("492414", "gengson", "nyc4211", "9178612424",
			// 2, 3, 40, 3.5, credential.getId());
			// addDriver(conn, driver);
			driver = getDriver(conn, credential.getId());
			System.out.println("driverID: " + driver.getDriverID()
					+ " driverName: " + driver.getDriverName()
					+ " phoneNumber: " + driver.getPhoneNumber()
					+ " licenseID: " + driver.getLicenseID()
					+ " vehicallicensenumber: "
					+ driver.getVehicalLicenseNumber() + " rank: "
					+ driver.getRank() + " rate: " + driver.getRate()
					+ " acceptedorders: " + driver.getAcceptedorders()
					+ " finishedorders: " + driver.getFinishedorders());
			// con = datasource.getConnection();
			// Statement st = con.createStatement();
			// ResultSet rs = st.executeQuery("select * from user");
			// ResultSet rs = st.executeQuery("show tables");
			// insertCredential(conn,"first","11111111");
			// ResultSet rs = getCredential(conn, "first");
			// deleteCredential(con, "first");
			// int cnt = 1;
			// while (rs.next()) {
			// System.out.println("id: " + rs.getString(1) + " username: "
			// + rs.getString(2) + " password: " + rs.getString(3));
			// // System.out.println((cnt++)+". Host:" +rs.getString("Host")+
			// //
			// " User:"+rs.getString("User")+" Password:"+rs.getString("Password"));
			// }
			// rs.close();
			// st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isUsernameExist(Connection conn, String username) {
		PreparedStatement checkUsernameStmt = null;
		ResultSet rs = null;
		try {
			checkUsernameStmt = conn.prepareStatement(Query.checkUsername);
			checkUsernameStmt.setString(1, username);
			rs = checkUsernameStmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void addCredential(Connection conn, Credential credential) {
		PreparedStatement addCredentialStmt = null;
		try {
			addCredentialStmt = conn.prepareStatement(Query.addCredential);
			addCredentialStmt.setString(1, credential.getUsername());
			addCredentialStmt.setString(2, credential.getPassword());
			addCredentialStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deleteCredential(Connection conn, String username) {
		PreparedStatement deleteCredentialStmt = null;
		try {
			deleteCredentialStmt = conn.prepareStatement(Query.delCredential);
			deleteCredentialStmt.setString(1, username);
			deleteCredentialStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Credential getCredential(Connection conn, String username) {
		PreparedStatement getCredentialStmt = null;
		Credential credential = null;
		ResultSet rs = null;
		try {
			getCredentialStmt = conn.prepareStatement(Query.getCredential);
			getCredentialStmt.setString(1, username);
			rs = getCredentialStmt.executeQuery();
			if (!rs.next()) {
				return credential;
			} else {
				credential = new Credential(rs.getInt(1), rs.getString(2),
						rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return credential;
	}

	public static void addCustomer(Connection conn, Customer customer) {
		PreparedStatement addCustomerStmt = null;
		try {
			addCustomerStmt = conn.prepareStatement(Query.addCustomer);
			addCustomerStmt.setInt(1, customer.getCustomerid());
			addCustomerStmt.setString(2, customer.getCustomerName());
			addCustomerStmt.setString(3, customer.getPhoneNumber());
			addCustomerStmt.setString(4, customer.getAddress());
			addCustomerStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void delCustomer(Connection conn, int customerID) {
		PreparedStatement delCustomerStmt = null;
		try {
			delCustomerStmt = conn.prepareStatement(Query.delCustomer);
			delCustomerStmt.setInt(1, customerID);
			delCustomerStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Customer getCustomer(Connection conn, int customerID) {
		PreparedStatement getCustomerStmt = null;
		Customer customer = null;
		try {
			getCustomerStmt = conn.prepareStatement(Query.getCustomer);
			getCustomerStmt.setInt(1, customerID);
			ResultSet rs = getCustomerStmt.executeQuery();
			if (!rs.next()) {
				return customer;
			} else {
				customer = new Customer(rs.getString(2), rs.getString(4),
						rs.getString(3), rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	public static void addDriver(Connection conn, Driver driver) {
		PreparedStatement addDriverStmt = null;
		try {
			addDriverStmt = conn.prepareStatement(Query.addDriver);
			addDriverStmt.setInt(1, driver.getDriverID());
			addDriverStmt.setString(2, driver.getDriverName());
			addDriverStmt.setString(3, driver.getLicenseID());
			addDriverStmt.setDouble(4, driver.getRate());
			addDriverStmt.setInt(5, driver.getRank());
			addDriverStmt.setInt(6, driver.getAcceptedorders());
			addDriverStmt.setInt(7, driver.getFinishedorders());
			addDriverStmt.setString(8, driver.getPhoneNumber());
			addDriverStmt.setString(9, driver.getVehicalLicenseNumber());
			addDriverStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void delDriver(Connection conn, int driverID) {
		PreparedStatement delDriverStmt = null;
		try {
			delDriverStmt = conn.prepareStatement(Query.delDriver);
			delDriverStmt.setInt(1, driverID);
			delDriverStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Driver getDriver(Connection conn, int driverID) {
		PreparedStatement getDriverStmt = null;
		Driver driver = null;
		try {
			getDriverStmt = conn.prepareStatement(Query.getDriver);
			getDriverStmt.setInt(1, driverID);
			ResultSet rs = getDriverStmt.executeQuery();
			if (!rs.next()) {
				return driver;
			} else {
				driver = new Driver(rs.getString(1), rs.getString(2),
						rs.getString(9), rs.getString(8), rs.getInt(6),
						rs.getInt(7), rs.getInt(5), rs.getDouble(4),
						rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

  public static void addLogin(Connection conn, int id) {
		PreparedStatement addLoginStmt = null;
		try {
			addLoginStmt = conn.prepareStatement(Query.addLogin);
			addLoginStmt.setInt(1, id);
			addLoginStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addOrder(Connection conn, Request request) {

		PreparedStatement addOrderStmt = null;
		Order order = new Order(request.getCustomerID(), request.getDriverID(),
				request.getPickUplat(), request.getPickUpLng(),
				request.getDestLat(), request.getDestLng(),
				request.getPickUpAddress(), request.getDestination());
		try {
			addOrderStmt = conn.prepareStatement(Query.addOrder);
			addOrderStmt.setInt(1, order.getCustomerID());
			addOrderStmt.setInt(2, order.getDriverID());
			addOrderStmt.setDouble(3, order.getFromLat());
			addOrderStmt.setDouble(4, order.getFromLongti());
			addOrderStmt.setDouble(5, order.getToLat());
			addOrderStmt.setDouble(6, order.getToLongti());
			addOrderStmt.setString(7, order.getFromName());
			addOrderStmt.setString(8, order.getToName());
			addOrderStmt.setInt(9, order.getFinished());
			addOrderStmt.setDouble(10, order.getFee());
			addOrderStmt.setDouble(11, order.getRate());
			addOrderStmt.setDouble(12, order.getReal());
			addOrderStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}