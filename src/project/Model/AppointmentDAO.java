package project.Model;

import java.sql.Connection;
import java.sql.Date;
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
			
			//System.out.println("Appointment doRetrieveById: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setIdAppointment(rs.getInt("IdAppointment"));
				bean.setDetails(rs.getString("Details"));
				bean.setRequestId(rs.getInt("RequestId"));
				bean.setTutor(rs.getString("Tutor"));
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
			
			RequestDAO requestDAO = new RequestDAO();
			requestDAO.confirmAppointment(bean.getRequestId());
			
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
			
			// System.out.println("Appointment doModify: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			RequestDAO requestDAO = new RequestDAO();
			RequestBean request = requestDAO.doRetrieveById(bean.getRequestId());
			request.setState("Appuntamento effettuato");
			
			requestDAO.doModify(request);
			
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
			
			// System.out.println("Appointment doDelete: " + preparedStatement.toString());
			result = preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return (result!=0);		
	}


	public AppointmentBean doRetrieveByRequestId(int requestId) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		AppointmentBean bean = new AppointmentBean();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM APPOINTMENT AS A, REQUEST AS R WHERE R.IdRequest = A.RequestId AND R.IdRequest = ?";
						
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

	public Collection<AppointmentBean> doRetrieveAllByDate (String order, String tutorMail, Date requestDate, int startTime, int finishTime) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<AppointmentBean> list = new LinkedList<AppointmentBean>();
		
		String selectSql = "SELECT * FROM APPOINTMENT AS A, REQUEST AS R WHERE A.Tutor = ? AND A.RequestId = R.IdRequest "
						 + "AND R.RequestDate = ? AND R.RequestTime BETWEEN ? AND ?";
		
		if(order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, tutorMail);
			preparedStatement.setDate(2, requestDate);
			preparedStatement.setInt(3, startTime);
			preparedStatement.setInt(4, finishTime);
			
			System.out.println("Appointment doRetrieveAllByDate: " + preparedStatement.toString());
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
		return list;
	}
	
	public Collection<AppointmentBean> doRetrieveAllByTutor (String tutorMail) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<AppointmentBean> list = new LinkedList<AppointmentBean>();
		
		String selectSql = "SELECT * FROM APPOINTMENT WHERE Tutor = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, tutorMail);
			
			System.out.println("Appointment doRetrieveAllByTutor: " + preparedStatement.toString());
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
		return list;
	}
}