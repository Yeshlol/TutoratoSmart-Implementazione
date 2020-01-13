package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.Control.DBConnection;

public class ManagesDAO {
	public ManagesDAO() {
		super();
	}
	
	
	public synchronized ArrayList<ManagesBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		ArrayList<ManagesBean>managesList = new ArrayList<ManagesBean>();
		String sql = "select * from MANAGES";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
		    ManagesBean bean = null;
        	String tutor=rs.getString("Tutor");
			int requestId=rs.getInt("RequestId");

			
			bean = new ManagesBean(tutor,requestId);
			managesList.add(bean);
		}
		return managesList;
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
