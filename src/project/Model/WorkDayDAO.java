package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

public class WorkDayDAO  {		
	public WorkDayDAO() {
		super();
	}
	
	
	public Collection<WorkDayBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<WorkDayBean> list = new LinkedList<WorkDayBean>();
		
		String selectSql = "SELECT * FROM WORK_DAY";
				
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			
			System.out.println("WorkDay doRetrieveAll: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				WorkDayBean bean = new WorkDayBean();
				bean.setCalendarId(rs.getInt("CalendarId"));
				bean.setWorkDayName(rs.getString("WorkDayName"));
				bean.setStartTime(rs.getInt("StartTime"));
				bean.setFinishTime(rs.getInt("FinishTime"));
				bean.setStartTime2(rs.getInt("StartTime2"));
				bean.setFinishTime2(rs.getInt("FinishTime2"));
				bean.setOpen(rs.getBoolean("IsOpen"));
				
				list.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return list;
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
