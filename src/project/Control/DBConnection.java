package project.Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBConnection {
	private static List<Connection> freeDbConnections;
	private static boolean isTest = false;
	
	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found:"+ e.getMessage());
		} 
	}
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

	/**
	 * Constructor.
	 */
	
	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
		String username = "root";
		String password = "root";
		String db;
		if(!isTest) {
			db = "TutoratoSmart";
		} else {
			db = "TutoratoSmartTest";
		}
		
		String url = "jdbc:mysql://localhost:3306/"+db+"?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

		newConnection = DriverManager.getConnection(url, username, password);

		//newConnection.setAutoCommit(false);
		return newConnection;
		
	}
	
  /**	public DBConnection() {
	    this.conn = null;
	    this.databaseName = "TutoratoSmart";
	    this.userName = "root";
	    this.password = "root";
	    this.hostPort = 3306;
	    this.hostName = "localhost";

	    try {
	      Class.forName("com.mysql.jdbc.Driver");
	      String url = "jdbc:mysql://" + this.hostName + ":" + this.hostPort + "/" + this.databaseName
	          + "?useSSL=false";
	      this.conn = DriverManager.getConnection(url, this.userName, this.password);
	      this.conn.setAutoCommit(false);
	    } catch (Exception exc) {
	      System.out.println(exc.getMessage());
	    }
	}  */

	/**
	 * Get the instance of the database.
	 */
	public static DBConnection getInstance() {
	    if (instance == null) {
	      instance = new DBConnection();
	    }
	    return instance;
	}

	/**
	 * Returns the Connection type object.
	 */
	public static synchronized Connection getConn() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConn();
			} catch (SQLException e) {
				connection.close();
				connection = getConn();
			}
		} else {
			connection = createDBConnection();		
		}

		return connection;
	}
	
	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		if(connection != null) freeDbConnections.add(connection);
	}
	
	public static boolean isTest() {
		return isTest;
	}


	public static void setTest(boolean isTest) {
		DBConnection.isTest = isTest;
	}
	
	
	
	
	
	
	
	/*public Connection getConn() {
		return this.conn;
	}

	/**
	 * Set the connection with the database.
	 * 
	 * @param conn is the variable that contains the object that allows you to connect the database to
	 * the code.
	 */
	public void setConn(Connection conn) {
	  this.conn = conn;
	}

	/**
	 * Get the name of the database.
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
