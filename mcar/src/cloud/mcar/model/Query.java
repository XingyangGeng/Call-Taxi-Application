package cloud.mcar.model;

public class Query {
	
	public static final String addCredential = "INSERT INTO credential (username,password,time) VALUES(?,?,NOW())";
	public static final String delCredential = "DELETE FROM credential where username = ?";
	public static final String getCredential = "SELECT * FROM credential where username = ?";
	public static final String checkUsername = "SELECT * FROM credential where username = ?";
	public static final String addLogin = "INSERT INTO login (id,time) VALUES(?,NOW())";
	
	public static final String addCustomer = "INSERT INTO customer (customerid,customername,phonenumber,address) VALUES(?,?,?,?)";
	public static final String delCustomer = "DELETE  FROM customer WHERE customerid = ?";
	public static final String getCustomer = "SELECT * FROM customer WHERE customerid = ?";
	
	public static final String addDriver = "INSERT INTO driver (driverid,drivername,licenseid,rate,rank,acceptedorders,finishedorders,phonenumber,vehicallicensenumber) VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String delDriver = "DELETE  FROM driver WHERE driverid = ?";
	public static final String getDriver = "SELECT * FROM driver WHERE driverid = ?";
	
	
	
	public static final String addOrder = "INSERT INTO order (customerid,driverid,time,fromLat,fromLongti,toLat,toLongti,fromName,toName,finished,fee,rate,real) VALUES(?,?,NOW(),?,?,?,?,?,?,?,?,?,?)";
	public static final String getOrder = "SELECT * FROM order WHERE orderid = ?";
	
}
