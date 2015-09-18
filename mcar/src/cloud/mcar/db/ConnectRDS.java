package cloud.mcar.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectRDS {
	//private String MYSQL_DRIVER =  "com.mysql.jdbc.Driver";
	public static void main(String[] args) {
		Connection conn = null;
		String MYSQL_DRIVER =  "com.mysql.jdbc.Driver";
		
		// String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" +
		// dbName + "?user=" + userName + "&password=" + password;
		String jdbcUrl = "jdbc:mysql://mcardb.cxh0fcrzaysq.us-east-1.rds.amazonaws.com:3306/testmcar?user=mcaruser&password=mcar123456";
		// String host = "jdbc:mysql://rds-ip:3306/rds-database-name";
		// String uName = "rds-database-username";
		// String uPass = "rds-database-pasword";
		// Connection con = DriverManager.getConnection( host, uName, uPass );

		try {
			Class.forName(MYSQL_DRIVER);
			System.out.println("Class Loaded....");
			conn = DriverManager.getConnection(jdbcUrl);

			// Do something with the Connection
			Statement stmt = conn.createStatement();
			String sql = "SHOW TABLES";
			//int c =stmt.executeUpdate("CREATE TABLE Accounts (Name VARCHAR(30))");
	       // System.out.println("Table have been created.");
	       // System.out.println(c+" Row(s) have been affected");
			ResultSet rs = stmt.executeQuery(sql);
//			
			int i = 0;
			while(rs.next()){
				System.out.println(rs.getString(++i));
			}
			
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
