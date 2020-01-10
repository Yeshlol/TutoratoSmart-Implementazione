package project.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

public class RequestDAO  {		
	public RequestDAO() {
		super();
	}

	
	public RequestBean doRetrieveById(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		RequestBean bean = new RequestBean();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM REQUEST WHERE IdRequest = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, id);
			
			System.out.println("Request doRetrieveById: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setIdRequest(rs.getInt("IdRequest"));
				bean.setState(rs.getString("State"));
				bean.setStudentComment(rs.getString("StudentComment"));
				bean.setRequestDate(rs.getDate("RequestDate"));
				bean.setRequestTime(rs.getInt("RequestTime"));
				bean.setDuration(rs.getInt("Duration"));
				bean.setStudent(rs.getString("Student"));
				
				System.out.println("Richiesta Trovata con l'id!");
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
	
	public synchronized void doSave(RequestBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO REQUEST(StudentComment,RequestDate,RequestTime,Student)"
						 + " VALUES (?,?,?,?)";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, bean.getStudentComment());
			preparedStatement.setDate(2, bean.getRequestDate());
			preparedStatement.setInt(3, bean.getRequestTime());		
			preparedStatement.setString(4, bean.getStudent());
			
			System.out.println("Request doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}	
	}
		
	
	public synchronized void doModify(RequestBean bean) throws SQLException {		
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE REQUEST SET StudentComment=?,RequestDate=?,RequestTime=? WHERE IdRequest = ?";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSql);
	
			preparedStatement.setString(1, bean.getStudentComment());
			preparedStatement.setDate(2, bean.getRequestDate());
			preparedStatement.setInt(3, bean.getRequestTime());
			preparedStatement.setInt(4, bean.getIdRequest());
			
			System.out.println("Request doModify: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}
	
	
	public synchronized void doAccept(RequestBean bean) throws SQLException {		
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE REQUEST SET State='Accettata',Duration=? WHERE IdRequest = ?";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSql);
	
			preparedStatement.setInt(1, bean.getDuration());
			preparedStatement.setInt(2, bean.getIdRequest());
			
			System.out.println("Request doAccept: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}


	public synchronized boolean doDelete(RequestBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "DELETE FROM REQUEST WHERE IdRequest = ?";
		int result;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, bean.getIdRequest());
			
			System.out.println("Request doDelete: " + preparedStatement.toString());
			result = preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return (result!=0);		
	}


	public Collection<RequestBean> doRetrieveAllByMail(String order, String studentMail) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<RequestBean> list = new LinkedList<RequestBean>();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM REQUEST WHERE Student = ? ";
		
		if (order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, studentMail);
			
			System.out.println("Request doRetrieveAllByMail: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				RequestBean bean = new RequestBean();
				bean.setIdRequest(rs.getInt("IdRequest"));
				bean.setState(rs.getString("State"));
				bean.setStudentComment(rs.getString("StudentComment"));
				bean.setRequestDate(rs.getDate("RequestDate"));
				bean.setRequestTime(rs.getInt("RequestTime"));
				bean.setDuration(rs.getInt("Duration"));
				bean.setStudent(rs.getString("Student"));
							
				list.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return list;
	}
	
	
	public Collection<RequestBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<RequestBean> list = new LinkedList<RequestBean>();
		
		String selectSql = "SELECT * FROM REQUEST";
				
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			
			System.out.println("Request doRetrieveAll: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				RequestBean bean = new RequestBean();
				bean.setIdRequest(rs.getInt("IdRequest"));
				bean.setState(rs.getString("State"));
				bean.setStudentComment(rs.getString("StudentComment"));
				bean.setRequestDate(rs.getDate("RequestDate"));
				bean.setRequestTime(rs.getInt("RequestTime"));
				bean.setDuration(rs.getInt("Duration"));
				bean.setState(rs.getString("State"));
				bean.setStudent(rs.getString("Student"));
				
				list.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return list;
	}

	public Collection<RequestBean> doRetrieveAllByDates(String order, String studentMail, Date startResearchDate, Date finishResearchDate) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<RequestBean> requestsList = new LinkedList<RequestBean>();
		
		String selectSql = "SELECT * FROM REQUEST AS R WHERE R.RequestDate >= ? AND R.RequestDate <= ? AND R.Student = ?";
		
		if(order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setDate(1, startResearchDate);
			preparedStatement.setDate(2, finishResearchDate);
			preparedStatement.setString(3, studentMail);
			
			System.out.println("Request doRetrieveAllByDates: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				RequestBean bean = new RequestBean();
				bean.setIdRequest(rs.getInt("IdRequest"));
				bean.setState(rs.getString("State"));
				bean.setStudentComment(rs.getString("StudentComment"));
				bean.setRequestDate(rs.getDate("RequestDate"));
				bean.setRequestTime(rs.getInt("RequestTime"));
				bean.setDuration(rs.getInt("Duration"));
				bean.setStudent(rs.getString("Student"));
				
				requestsList.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return requestsList;
	}


	public boolean isAvailable(Date requestDate, int requestTime) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "SELECT IdRequest FROM REQUEST WHERE RequestDate = ? AND ? BETWEEN RequestTime AND RequestTime+Duration ";
		
		try {
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setDate(1, requestDate);
			preparedStatement.setInt(2, requestTime);
			
			//System.out.println("Request isAvailable: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.wasNull()) {
				//System.out.println("Errore esecuzione query!");
				return false;
	        } else {
	        	int count = rs.last() ? rs.getRow() : 0;
	            if (count == 1) {
	            	//System.out.println("Orario non disponibile! Esiste già una prenotazione!");
	            	return false;
	            }
	            else {
	            	//System.out.println("Orario disponibile!");
	            	return true;
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return false;
	}
}
