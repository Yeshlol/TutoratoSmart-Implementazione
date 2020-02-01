package project.Control;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	/**
	 * Variables.
	 */
	private static DBConnection instance = null;
	private Connection conn;
	private String databaseName;
	private String userName;
	private String password;
	private int hostPort;
	private String hostName;
	private static boolean isTest=false;
	
	
	/**
	 * Constructor.
	 */
	public DBConnection() {
	    this.conn = null;
	    this.hostPort = 3306;
	    this.hostName = "localhost";

	    try {
	    	if(!isTest) {
	    		this.databaseName="TutoratoSmart";
	    		this.userName = "root";
	    		this.password = "root";
	    	} else {
	    		this.databaseName="TutoratoSmartTest";
	    		this.userName = "tester";
	    		this.password = "test";
	    	}
	      Class.forName("com.mysql.jdbc.Driver");
	      String url = "jdbc:mysql://" + this.hostName + ":" + this.hostPort + "/" + this.databaseName
	          + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	      this.conn = DriverManager.getConnection(url, this.userName, this.password);
	      this.conn.setAutoCommit(false);
	    } catch (Exception exc) {
	      System.out.println(exc.getMessage());
	    }
	}
	
	public static boolean isTest() {
		return isTest;
	}
	
	public static void setTest(boolean isTest) {
		DBConnection.isTest=isTest;
	}
	
	/**
	 * Get the instance of the database.
	 * @return Returns the instance that allows DB connection.
	 */
	public static DBConnection getInstance() {
	    if (instance == null) {
	      instance = new DBConnection();
	    }
	    return instance;
	}

	/**
	 * Returns the Connection type object.
	 * @return Returns the Connection type object.
	 */
	public synchronized Connection getConn() {
		return this.conn;
	}

	/**
	 * Get the name of the database.
	 * @return Return the DB Name.
	 */
	public String getDatabaseName() {
		return this.databaseName;
	}

	/**
	 * Set the name of the database.
	 * 
	 * @param databaseName is the variable that contains the name of the database.
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * Get the name of the user.
	 * @return Returns the user name.
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Set the name of the user.
	 * 
	 * @param userName is the variable that contains the name of the user.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get the password.
	 * @return Returns the password used to connect to DB.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Set the name of the user.
	 * 
	 * @param password is the variable that contains the password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the number of the host port.
	 * @return Returns the connection port.
	 */
	public int getHostPort() {
		return this.hostPort;
	}

	/**
	 * Set the number of the host port.
	 * 
	 * @param hostPort is the variable that contains the number of the host port.
	 */
	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}

	/**
	 * Get the name of the host port.
	 * @return Returns the host port.
	 */
	public String getHostName() {
		return this.hostName;
	}

	/**
	 * Set the number of the host port.
	 * 
	 * @param hostName is the variable that contains the name of the host port.
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
} 
