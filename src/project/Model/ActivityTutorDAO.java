package project.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

public class ActivityTutorDAO  {		
	public ActivityTutorDAO() {
		super();
	}

	
	public ActivityTutorBean doRetrieveById(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		ActivityTutorBean bean = new ActivityTutorBean();
		
		String selectSql = "SELECT * FROM ACTIVITY_TUTOR WHERE IdActivity = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, id);
			
			//System.out.println("ActivityTutor doRetrieveById: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setIdActivity(rs.getInt("IdActivity"));
				bean.setCategory(rs.getString("Category"));
				bean.setActivityDate(rs.getDate("ActivityDate"));
				bean.setStartTime(rs.getInt("StartTime"));
				bean.setFinishTime(rs.getInt("FinishTime"));
				bean.setHours(rs.getFloat("Hours"));
				bean.setState(rs.getString("State"));
				bean.setDetails(rs.getString("Details"));
				bean.setTutor(rs.getString("Tutor"));
				bean.setRegisterId(rs.getInt("RegisterId"));
				
			//	System.out.println("Attivita' Trovata con l'id!");
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
	
	
	public boolean anyActivityRegistered(String tutorMail, Date activityDate, int start, int finish) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
				
		String selectSql = "SELECT * FROM ACTIVITY_TUTOR WHERE Tutor = ? AND ActivityDate = ? "
						 + "AND ((? <  StartTime AND ? > StartTime) "
						 + "OR (? >= StartTime AND ? <= FinishTime)"
						 + "OR (? > StartTime AND ? < FinishTime) " 
						 + "OR (? < FinishTime AND ? >  FinishTime))";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, tutorMail);
			preparedStatement.setDate(2, activityDate);
			preparedStatement.setInt(3, start);
			preparedStatement.setInt(4, finish);
			preparedStatement.setInt(5, start);
			preparedStatement.setInt(6, finish);
			preparedStatement.setInt(7, start);
			preparedStatement.setInt(8, finish);
			preparedStatement.setInt(9, start);
			preparedStatement.setInt(10, finish);
			
			// System.out.println("ActivityTutor isActivityRegistered: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.wasNull()) {
				System.out.println("Errore esecuzione query!");
	        } else {
	        	int count = rs.last() ? rs.getRow() : 0;
	            if (count > 0) 
	            	return true;
	            else 
	            	return false;
	        }
		} catch (SQLException e) {
			// System.out.println("Attività non trovata!");
			return false;
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return false;
	}
	
	
	@SuppressWarnings("resource")
	public synchronized int doSave(ActivityTutorBean activity) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO ACTIVITY_TUTOR(Category,ActivityDate,StartTime,FinishTime,Hours,Details,Tutor,RegisterId)"
						 + " VALUES (?,?,?,?,?,?,?,?)";
		
		int idActivity = -1;
		
		String selectSql = "SELECT MAX(IdActivity) AS IdActivity FROM ACTIVITY_TUTOR";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, activity.getCategory());
			preparedStatement.setDate(2, activity.getActivityDate());
			preparedStatement.setInt(3, activity.getStartTime());
			preparedStatement.setInt(4, activity.getFinishTime());
			preparedStatement.setFloat(5, activity.getHours());
			preparedStatement.setString(6, activity.getDetails());
			preparedStatement.setString(7, activity.getTutor());
			preparedStatement.setInt(8, activity.getRegisterId());
			
			System.out.println("ActivityTutor doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(selectSql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				idActivity = rs.getInt("IdActivity");
			}			
			System.out.println("IdActivity added: " + idActivity);
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return idActivity;
	}
	
		
	public synchronized void doModify(ActivityTutorBean bean) throws SQLException {		
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE ACTIVITY_TUTOR SET Category=?,ActivityDate=?,StartTime=?,FinishTime=?,Hours=?,Details=? WHERE IdActivity = ?";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSql);			
			
			preparedStatement.setString(1, bean.getCategory());
			preparedStatement.setDate(2, bean.getActivityDate());
			preparedStatement.setInt(3, bean.getStartTime());
			preparedStatement.setInt(4, bean.getFinishTime());
			preparedStatement.setFloat(5, bean.getHours());
			preparedStatement.setString(6, bean.getDetails());
			preparedStatement.setInt(7, bean.getIdActivity());
			
			System.out.println("ActivityTutor doModify: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}


	public synchronized boolean doDelete(ActivityTutorBean activity) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		RegisterDAO registerDAO = new RegisterDAO();
		RegisterBean register = registerDAO.doRetrieveById(activity.getRegisterId());
		
		if(activity.getState().equals("Convalidata")) {
			float hours = activity.getHours();
			register.setValidatedHours(register.getValidatedHours() - hours);
			register.setPercentageComplete(register.getValidatedHours() / register.getTotalHours());
		}
		
		String deleteSql = "DELETE FROM ACTIVITY_TUTOR WHERE IdActivity = ?";
		int result;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, activity.getIdActivity());
			
			System.out.println("ActivityTutor doDelete: " + preparedStatement.toString());
			result = preparedStatement.executeUpdate();
			
			registerDAO.doUpdate(register);
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return (result!=0);		
	}

	
	public Collection<ActivityTutorBean> doRetrieveAllByMail(String order, String tutorMail) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<ActivityTutorBean> list = new LinkedList<ActivityTutorBean>();
		
		String selectSql = "SELECT * FROM ACTIVITY_TUTOR WHERE Tutor = ?";
		
		if(order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, tutorMail);
			
			// System.out.println("ActivityTutor doRetrieveAllByMail: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				ActivityTutorBean bean = new ActivityTutorBean();
				bean.setIdActivity(rs.getInt("IdActivity"));
				bean.setCategory(rs.getString("Category"));
				bean.setActivityDate(rs.getDate("ActivityDate"));
				bean.setStartTime(rs.getInt("StartTime"));
				bean.setFinishTime(rs.getInt("FinishTime"));
				bean.setHours(rs.getFloat("Hours"));
				bean.setState(rs.getString("State"));
				bean.setDetails(rs.getString("Details"));
				bean.setTutor(rs.getString("Tutor"));
				bean.setRegisterId(rs.getInt("RegisterId"));
				
				list.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return list;
	}


	public boolean differentActivityRegistered(String tutorMail, int activityId, Date activityDate, int start, int finish) {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
				
		String selectSql = "SELECT * FROM ACTIVITY_TUTOR WHERE Tutor = ? AND ActivityDate = ? "
						 + "AND ((? <  StartTime AND ? > StartTime) "
						 + "OR (? >= StartTime AND ? <= FinishTime)"
						 + "OR (? > StartTime AND ? < FinishTime) " 
						 + "OR (? < FinishTime AND ? >  FinishTime)) AND IdActivity != ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, tutorMail);
			preparedStatement.setDate(2, activityDate);
			preparedStatement.setInt(3, start);
			preparedStatement.setInt(4, finish);
			preparedStatement.setInt(5, start);
			preparedStatement.setInt(6, finish);
			preparedStatement.setInt(7, start);
			preparedStatement.setInt(8, finish);
			preparedStatement.setInt(9, start);
			preparedStatement.setInt(10, finish);
			preparedStatement.setInt(11, activityId);
			
			System.out.println("ActivityTutor isActivityRegistered: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.wasNull()) {
				System.out.println("Errore esecuzione query!");
	        } else {
	        	int count = rs.last() ? rs.getRow() : 0;
	            if (count > 0) 
	            	return true;
	            else 
	            	return false;
	        }
		} catch (SQLException e) {
			System.out.println("Attività non trovata!");
			return false;
		}
		
		return false;
	}
}