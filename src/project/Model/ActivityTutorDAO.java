package project.Model;

import java.sql.Connection;
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
			
			System.out.println("ActivityTutor doRetrieveById: " + preparedStatement.toString());
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
				
				System.out.println("Attivita' Trovata con l'id!");
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
	
	
	public synchronized void doSave(ActivityTutorBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO ACTIVITY_TUTOR(Category,ActivityDate,StartTime,FinishTime,Hours,State,Details,Tutor,RegisterId)"
						 + " VALUES (?,?,?,?,?,?,?,?,?)";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, bean.getCategory());
			preparedStatement.setDate(2, bean.getActivityDate());
			preparedStatement.setInt(3, bean.getStartTime());
			preparedStatement.setInt(4, bean.getFinishTime());
			preparedStatement.setFloat(5, bean.getHours());
			preparedStatement.setString(6, bean.getState());
			preparedStatement.setString(7, bean.getDetails());
			preparedStatement.setString(8, bean.getTutor());
			preparedStatement.setInt(9, bean.getRegisterId());
			
			System.out.println("ActivityTutor doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}	
	}
	
		
	public synchronized void doModify(ActivityTutorBean bean) throws SQLException {		
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE ACTIVITY_TUTOR SET ActivityDate=?,StartTime=?,FinishTime=?,Hours=?,Details=? WHERE IdActivity = ?";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSql);
	
			preparedStatement.setDate(1, bean.getActivityDate());
			preparedStatement.setInt(2, bean.getStartTime());
			preparedStatement.setInt(3, bean.getFinishTime());
			preparedStatement.setFloat(4, bean.getHours());
			preparedStatement.setString(5, bean.getDetails());
			preparedStatement.setInt(6, bean.getIdActivity());
			
			System.out.println("ActivityTutor doModify: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}


	public synchronized boolean doDelete(ActivityTutorBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "DELETE FROM ACTIVITY_TUTOR WHERE IdActivity = ?";
		int result;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, bean.getIdActivity());
			
			System.out.println("ActivityTutor doDelete: " + preparedStatement.toString());
			result = preparedStatement.executeUpdate();
			
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
			
			System.out.println("ActivityTutor doRetrieveAllByMail: " + preparedStatement.toString());
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
}