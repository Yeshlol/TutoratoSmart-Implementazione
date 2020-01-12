package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.Control.DBConnection;

public class ManagesDAO {
	public ManagesDAO() {
		super();
	}
	
	
	public synchronized void doSave(ManagesBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement=null;
		
		String insertSql = "INSERT INTO MANAGES(Tutor,RequestId) VALUES (?,?)";
		try {			
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			
			preparedStatement.setString(1,bean.getTutor());
			preparedStatement.setInt(2,bean.getRequestId());
						
			//System.out.println("Manages doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}	
}
