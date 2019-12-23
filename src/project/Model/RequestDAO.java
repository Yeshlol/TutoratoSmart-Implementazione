package project.Model;

import java.sql.Connection;
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
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				connection.close();
			}
		}
		return bean;
	}
	
	
	public synchronized void doSave(RequestBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO REQUEST(State,StudentComment,RequestDate,RequestTime,Duration,Student)"
						 + " VALUES (?,?,?,?,?,?,?)";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, bean.getState());
			preparedStatement.setString(2, bean.getStudentComment());
			preparedStatement.setDate(3, bean.getRequestDate());
			preparedStatement.setInt(4, bean.getRequestTime());
			preparedStatement.setInt(5, bean.getDuration());			
			preparedStatement.setString(6, bean.getStudent());
			
			System.out.println("Request doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();				
			} finally {
				connection.close();
			}
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
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				connection.close();
			}
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
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				connection.close();
			}
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
			try {
				if(preparedStatement!=null) {
					preparedStatement.close();
				}				
			} finally {
				connection.close();
			}
		}
		return (result!=0);		
	}


	public Collection<RequestBean> doRetrieveAllByMail(String order, String studentMail) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<RequestBean> list = new LinkedList<RequestBean>();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM REQUEST WHERE Student = ?";
		
		if(order!=null && !order.equals("")) {
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
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
				}
			finally {
				connection.close();
			}
		}
		return list;
	}
}