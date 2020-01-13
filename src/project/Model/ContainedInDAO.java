package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

public class ContainedInDAO {
	public ContainedInDAO() {
		super();
	}
	
	public synchronized ArrayList<ContainedInBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		ArrayList<ContainedInBean> containedInList = new ArrayList<ContainedInBean>();
		String sql = "select * from CONTAINED_IN";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
		    ContainedInBean bean = null;
        	int appointmentId=rs.getInt("AppointmentId");
			int requestId=rs.getInt("ActivityId");

			
			bean = new ContainedInBean(appointmentId,requestId);
			containedInList.add(bean);
		}
		return containedInList;
	}
	
	
	public synchronized void doDeleteByActivityId(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "DELETE FROM CONTAINED_IN WHERE ActivityId = ?";
			
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, id);
			
			System.out.println("ContainedIn doDeleteByActivityId: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
	}
	
	public synchronized Collection<AppointmentBean> doRetrieveByActivityId(int id) throws SQLException {
		Collection<AppointmentBean> list = new LinkedList<AppointmentBean>();
				
		if(id > 0) {
			Connection connection = DBConnection.getInstance().getConn();
			PreparedStatement preparedStatement = null;
			
			String selectSql = "SELECT SQL_NO_CACHE * FROM CONTAINED_IN WHERE ActivityId = ?";
			
			try {
				preparedStatement = connection.prepareStatement(selectSql);
				preparedStatement.setInt(1, id);
				
				System.out.println("Contained_In doRetrieveByActivityId: " + preparedStatement.toString());
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					AppointmentBean bean = new AppointmentBean();
					bean.setIdAppointment(rs.getInt("IdAppointment"));
					bean.setDetails(rs.getString("Details"));
					bean.setRequestId(rs.getInt("RequestId"));
					bean.setTutor(rs.getString("Tutor"));
					
					list.add(bean);
				}	
			} finally {
				if(preparedStatement != null)
					preparedStatement.close();
			}
		}
		return list;
	}
		
	
	public synchronized ContainedInBean doRetrieveByAppointmentId (int appointmentId) throws SQLException {
		ContainedInBean bean = new ContainedInBean();
				
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
			
		String selectSql = "SELECT SQL_NO_CACHE * FROM CONTAINED_IN WHERE AppointmentId = ?";
			
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, appointmentId);
			
			System.out.println("Contained_In doRetrieveByAppointmentId: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				bean.setActivityId(rs.getInt("ActivityId"));
			}	
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return bean;
	}
	

	public synchronized void doSave(ContainedInBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement=null;
		
		String insertSql = "INSERT INTO CONTAINED_IN(AppointmentId,ActivityId) VALUES (?,?)";
		try {			
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			
			preparedStatement.setInt(1,bean.getAppointmentId());
			preparedStatement.setInt(2,bean.getActivityId());
						
			System.out.println("Contained_In doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		}finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}
	

	public boolean doDelete(ContainedInBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "DELETE FROM CONTAINED_IN WHERE AppointmentId = ? AND ActivityId = ?";
		int result;
		
		try {
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1,bean.getAppointmentId());
			preparedStatement.setInt(2, bean.getActivityId());
			
			System.out.println("Contained_In doDelete: " + preparedStatement.toString());
			
			result = preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();connection.close();
		}
		
		return (result!=0);		
	}	
}
