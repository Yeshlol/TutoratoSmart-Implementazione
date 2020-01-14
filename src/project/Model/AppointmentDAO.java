package project.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Appuntamento.
 *
 */
public class AppointmentDAO  {		
	public AppointmentDAO() {
		super();
	}
	
	/** 
	 * @param
	 * @return un ArrayList di tutti gli appuntamenti registrati nel database
	 * @throws SQLException
	 */
	public synchronized ArrayList<AppointmentBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		ArrayList<AppointmentBean> appointmentList = new ArrayList<AppointmentBean>();
		String sql = "select * from APPOINTMENT";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			AppointmentBean bean = null;
        	int id=rs.getInt("IdAppointment");
			String details=rs.getString("Details");
			int requestId=rs.getInt("RequestId");
			String tutor=rs.getString("Tutor");
			
			bean = new AppointmentBean(id,details,requestId,tutor);
			appointmentList.add(bean);
		}
		return appointmentList;
	}

	/** 
	 * @param id
	 * @return un appuntamento, tramite id, registrato nel database
	 * @throws SQLException
	 */
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
			//System.out.println("Id non trovato!");
			return null;
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return bean;
	}
	
	/** 
	 * @param bean
	 * @return  un intero per il salvataggio di un appuntamento nel database
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public synchronized int doSave(AppointmentBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO APPOINTMENT(Details,RequestId,Tutor)"
						 + " VALUES (?,?,?)";
		
		int idAppointment = -1;
		
		String selectSql = "SELECT MAX(IdAppointment) AS IdAppointment FROM APPOINTMENT";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, bean.getDetails());
			preparedStatement.setInt(2, bean.getRequestId());
			preparedStatement.setString(3, bean.getTutor());
			
			//System.out.println("Appointment doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			RequestDAO requestDAO = new RequestDAO();
			requestDAO.confirmAppointment(bean.getRequestId());
			
			preparedStatement = connection.prepareStatement(selectSql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				idAppointment = rs.getInt("IdAppointment");
			}			
			//System.out.println("IdAppointment added: " + idAppointment);
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return idAppointment;
	}
		
	/** 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
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

	/** 
	 * @param bean
	 * @return un booleano per controllare la cancellazione di un appuntamento nel database
	 * @throws SQLException
	 */
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

	/** 
	 * @param requestId
	 * @return un appuntamento, tramite requestId, registrato nel database
	 * @throws SQLException
	 */
	public AppointmentBean doRetrieveByRequestId(int requestId) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		AppointmentBean bean = new AppointmentBean();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM APPOINTMENT AS A, REQUEST AS R WHERE R.IdRequest = A.RequestId AND R.IdRequest = ?";
						
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, requestId);
			
			//System.out.println("Appointment doRetrieveByRequestId: " + preparedStatement.toString());
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

	/** 
	 * @param order
	 * @param tutorMail
	 * @param requestDate
	 * @param startTime
	 * @param finishTime
	 * @return una Collection di appuntamenti, tramite la data, registrati nel database
	 * @throws SQLException
	 */
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
			
			//System.out.println("Appointment doRetrieveAllByDate: " + preparedStatement.toString());
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
	
	/** 
	 * @param tutorMail
	 * @return una Collection di appuntamenti, tramite mail del tutor, registrati nel database
	 * @throws SQLException
	 */
	public Collection<AppointmentBean> doRetrieveAllByTutor (String tutorMail) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<AppointmentBean> list = new LinkedList<AppointmentBean>();
		
		String selectSql = "SELECT * FROM APPOINTMENT WHERE Tutor = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, tutorMail);
			
			//System.out.println("Appointment doRetrieveAllByTutor: " + preparedStatement.toString());
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