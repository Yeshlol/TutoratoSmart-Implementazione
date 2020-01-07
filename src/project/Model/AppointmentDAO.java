package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

public class AppointmentDAO  {		
	public AppointmentDAO() {
		super();
	}

	
	public AppointmentBean doRetrieveById(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		AppointmentBean bean = new AppointmentBean();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM APPOINTMENT WHERE IdAppointment = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, id);
			
			System.out.println("Appointment doRetrieveById: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setIdAppointment(rs.getInt("IdAppointment"));
				bean.setDetails(rs.getString("Details"));
				bean.setRequestId(rs.getInt("RequestId"));
				bean.setTutor(rs.getString("Tutor"));
				
				System.out.println("Appuntamento Trovato con l'id!");
			}
		} catch (SQLException e) {
			System.out.println("Id non trovato!");
			return null;
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return bean;
	}
	
	
	public synchronized void doSave(AppointmentBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO APPOINTMENT(Details,RequestId,Tutor)"
						 + " VALUES (?,?,?)";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, bean.getDetails());
			preparedStatement.setInt(2, bean.getRequestId());
			preparedStatement.setString(3, bean.getTutor());
			
			System.out.println("Appointment doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}	
	}
		
	
	public synchronized void doModify(AppointmentBean bean) throws SQLException {		
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE APPOINTMENT SET Details=? WHERE IdAppointment = ?";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSql);
	
			preparedStatement.setString(1, bean.getDetails());
			preparedStatement.setInt(2, bean.getIdAppointment());
			
			System.out.println("Appointment doModify: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}


	public synchronized boolean doDelete(AppointmentBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "DELETE FROM APPOINTMENT WHERE IdAppointment = ?";
		int result;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, bean.getIdAppointment());
			
			System.out.println("Appointment doDelete: " + preparedStatement.toString());
			result = preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return (result!=0);		
	}


	public AppointmentBean doRetrieveByRequestId(String order, int requestId) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		AppointmentBean bean = new AppointmentBean();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM APPOINTMENT AS A, REQUEST AS R WHERE R.IdRequest = A.RequestId AND R.IdRequest = ?";
		
		if(order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, requestId);
			
			System.out.println("Appointment doRetrieveByRequestId: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {				
				bean.setIdAppointment(rs.getInt("IdAppointment"));
				bean.setDetails(rs.getString("Details"));
				bean.setRequestId(rs.getInt("RequestId"));
				bean.setTutor(rs.getString("Tutor"));
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return bean;
	}
}