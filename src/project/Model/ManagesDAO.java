package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

public class ManagesDAO {
	public ManagesDAO() {
		super();
	}

	public  synchronized Collection<RequestBean> doRetrieveByTutor(String tutorMail) throws SQLException {
		Collection<RequestBean> list = new LinkedList<RequestBean>();
				
		if(tutorMail != null && !tutorMail.equals("")) {
			Connection connection = DBConnection.getInstance().getConn();
			PreparedStatement preparedStatement = null;
			
			String selectSql = "SELECT SQL_NO_CACHE * FROM MANAGES,REQUEST WHERE Tutor = ?";
			
			try {
				preparedStatement = connection.prepareStatement(selectSql);
				preparedStatement.setString(1, tutorMail);
				
				System.out.println("Manages doRetrieveByTutor: " + preparedStatement.toString());
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					RequestBean bean = new RequestBean();
					bean.setIdRequest(rs.getInt("IdAppointment"));
					bean.setRequestTime(rs.getInt("RequestTime"));
					bean.setDuration(rs.getInt("Duration"));
					bean.setState(rs.getString("State"));
					bean.setStudentComment(rs.getString("StudentComment"));
					bean.setRequestDate(rs.getDate("RequestDate"));
					bean.setStudent(rs.getString("Student"));
					
					list.add(bean);
				}	
			} finally {
				if(preparedStatement != null)
					preparedStatement.close();
			}
		}
		return list;
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
						
			System.out.println("Manages doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}
	

	public boolean doDelete(ManagesBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "DELETE FROM MANAGES WHERE Tutor = ? AND RequestId = ?";
		int result;
		
		try {
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setString(1,bean.getTutor());
			preparedStatement.setInt(2, bean.getRequestId());
			
			System.out.println("Manages doDelete: " + preparedStatement.toString());
			
			result = preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return (result!=0);
	}	
}
