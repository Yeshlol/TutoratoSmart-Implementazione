package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

public class CalendarDAO  {		
	public CalendarDAO() {
		super();
	}

	
	public CalendarBean doRetrieveById(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		CalendarBean bean = new CalendarBean();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM CALENDAR WHERE IdCalendar = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, id);
			
			System.out.println("Calendar doRetrieveById: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setIdCalendar(rs.getInt("IdCalendar"));
				bean.setCommissionMember(rs.getString("CommissionMember"));
				
				System.out.println("Calendario Trovato con l'id!");
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
	
	
	public synchronized void doSave(CalendarBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO CALENDAR(CommissionMember) VALUES (?)";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, bean.getCommissionMember());
			
			System.out.println("Calendar doSave: " + preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}	
	}
	

	public Collection<WorkDayBean> doRetrieveAllByCalendarId(String order, int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<WorkDayBean> list = new LinkedList<WorkDayBean>();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM WORK_DAY WHERE CalendarId = ?";
		
		if(order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, id);
			
			System.out.println("Calendar doRetrieveAllByCalendarId: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				WorkDayBean bean = new WorkDayBean();
				bean.setCalendarId(rs.getInt("CalendarId"));
				bean.setWorkDayName(rs.getString("WorkDayName"));
				bean.setStartTime(rs.getInt("StartTime"));
				bean.setFinishTime(rs.getInt("FinishTime"));
				bean.setOpen(rs.getBoolean("IsOpen"));
				
				list.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return list;
	}
}