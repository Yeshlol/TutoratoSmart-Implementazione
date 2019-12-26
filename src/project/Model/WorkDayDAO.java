package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.Control.DBConnection;

public class WorkDayDAO  {		
	public WorkDayDAO() {
		super();
	}

	
	public WorkDayBean doRetrieveByName(String name) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		WorkDayBean bean = new WorkDayBean();
		
		String selectSql = "SELECT * FROM WORK_DAY WHERE WorkDayName = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, name);
			
			System.out.println("WorkDay doRetrieveById: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setCalendarId(rs.getInt("CalendarId"));
				bean.setWorkDayName(rs.getString("WorkDayName"));
				bean.setStartTime(rs.getInt("StartTime"));
				bean.setFinishTime(rs.getInt("FinishTime"));
				bean.setOpen(rs.getBoolean("IsOpen"));
							
				System.out.println("Giorno lavorativo Trovato con il nome!");
			}
		} catch (SQLException e) {
			System.out.println("Nome non trovato!");
			return null;
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return bean;
	}
	
	
	public synchronized void doSave(WorkDayBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO WORK_DAY(CalendarId,WorkDayName,StartTime,FinishTime,IsOpen,CommissionMember"
						 + " VALUES (?,?,?,?,?,?)";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setInt(1, bean.getCalendarId());
			preparedStatement.setString(2, bean.getWorkDayName());
			preparedStatement.setInt(3, bean.getStartTime());
			preparedStatement.setInt(4, bean.getFinishTime());
			preparedStatement.setBoolean(5, bean.isOpen());
						
			System.out.println("WorkDay doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}	
	}
	
		
	public synchronized void doModify(WorkDayBean bean) throws SQLException {		
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE WORK_DAY SET StartTime=?,FinishTime=?,IsOpen=? WHERE CalendarId = ? AND WorkDayName = ?";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSql);
	
			preparedStatement.setInt(1, bean.getStartTime());
			preparedStatement.setInt(2, bean.getFinishTime());
			preparedStatement.setBoolean(3, bean.isOpen());
			preparedStatement.setInt(4, bean.getCalendarId());
			preparedStatement.setString(5, bean.getWorkDayName());
			
			System.out.println("WorkDay doModify: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}


	public synchronized boolean doDelete(WorkDayBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "DELETE FROM WORK_DAY WHERE CalendarId = ? AND WorkDayName = ?";
		int result;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, bean.getCalendarId());
			preparedStatement.setString(2, bean.getWorkDayName());
			
			System.out.println("WorkDay doDelete: " + preparedStatement.toString());
			result = preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return (result!=0);		
	}
}
