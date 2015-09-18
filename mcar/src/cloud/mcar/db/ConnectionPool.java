package cloud.mcar.db;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class ConnectionPool {
	public static final DataSource  datasource = new DataSource();
    public static void initialize(){
    	PoolProperties p = new PoolProperties();
    	   // p.setUrl("jdbc:mysql://localhost:3306/mysql");
    	    p.setUrl("jdbc:mysql://mcardb.cxh0fcrzaysq.us-east-1.rds.amazonaws.com:3306/testmcar");
    	    p.setDriverClassName("com.mysql.jdbc.Driver");
    	    p.setUsername("username");
    	    p.setPassword("password");
    	    p.setJmxEnabled(true);
    	    p.setTestWhileIdle(false);
    	    p.setTestOnBorrow(true);
    	    p.setValidationQuery("SELECT 1");
    	    p.setTestOnReturn(false);
    	    p.setValidationInterval(30000);
    	    p.setTimeBetweenEvictionRunsMillis(30000);
    	    p.setMaxActive(100);
    	    p.setInitialSize(10);
    	    p.setMaxWait(10000);
    	    p.setRemoveAbandonedTimeout(60);
    	    p.setMinEvictableIdleTimeMillis(30000);
    	    p.setMinIdle(10);
    	    p.setLogAbandoned(true);
    	    p.setRemoveAbandoned(true);
    	    p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
    	      "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
    	    datasource.setPoolProperties(p);
    }
	public static DataSource getDatasource() {
		return datasource;
	}

}
