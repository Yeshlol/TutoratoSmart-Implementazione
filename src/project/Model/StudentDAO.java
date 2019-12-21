package project.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;
import project.Utils.Utils;

public class StudentDAO  {		
	public StudentDAO() {
		super();
	}

	
	public StudentBean doRetrieveByMail(String mail) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		StudentBean bean = new StudentBean();
		
		String selectSql = "SELECT * FROM TS_USER,STUDENT WHERE Email = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, mail);
			
			System.out.println("Student doRetrieveByMail: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setEmail(rs.getString("Email"));
				bean.setPwd(rs.getString("Pwd"));
				bean.setFirstName(rs.getString("FirstName"));
				bean.setLastName(rs.getString("LastName"));
				bean.setTelephoneNumber(rs.getString("TelephoneNumber"));
				bean.setSex(rs.getString("Sex"));
				bean.setRegistrationNumber(rs.getString("RegistrationNumber"));
				bean.setAcademicYear(rs.getInt("AcademicYear"));
				
				System.out.println("Studente Trovato con la email!");
			}
		} catch (SQLException e) {
			System.out.println("Email non trovata!");
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
	
	
	@SuppressWarnings("resource")
	public synchronized void doSave(StudentBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO TS_USER(Email,Pwd,FirstName,LastName,TelephoneNumber,Sex,RegistrationNumber) VALUES (?,?,?,?,?,?,?)";
		String insertSql2 = "INSERT INTO STUDENT (Email,AcademicYear) VALUES (?,?)";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, bean.getEmail());
			preparedStatement.setString(2, Utils.sha256(bean.getPwd()));
			preparedStatement.setString(3, bean.getFirstName());
			preparedStatement.setString(4, bean.getLastName());
			preparedStatement.setString(5, bean.getTelephoneNumber());
			preparedStatement.setString(6, bean.getSex());
			preparedStatement.setString(7, bean.getRegistrationNumber());
			
			System.out.println("User doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(insertSql2);
			preparedStatement.setString(1, bean.getEmail());
			preparedStatement.setInt(2, bean.getAcademicYear());
			
			System.out.println("Student doSave: "+ preparedStatement.toString());
			
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
	
	
	public Collection<StudentBean> doRetrieveAllByDates(String order, Date startResearchDate, Date finishResearchDate) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<StudentBean> list = new LinkedList<StudentBean>();
		
		String selectSql = "SELECT * FROM APPOINTMENT AS A, STUDENT AS S, REQUEST AS Q "
				+ "WHERE RequestDate >= ? AND RequestDate <= ?";
		
		if(order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setDate(1, startResearchDate);
			preparedStatement.setDate(2, finishResearchDate);
			
			System.out.println("Student doRetrieveAllByDates: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				StudentBean sbean = new StudentBean();
				sbean.setEmail(rs.getString("Email"));
				sbean.setPwd(rs.getString("Pwd"));
				sbean.setFirstName(rs.getString("FirstName"));
				sbean.setLastName(rs.getString("LastName"));
				sbean.setTelephoneNumber(rs.getString("TelephoneNumber"));
				sbean.setSex(rs.getString("Sex"));
				sbean.setRegistrationNumber(rs.getString("RegistrationNumber"));
				sbean.setAcademicYear(rs.getInt("AcademicYear"));
				
				list.add(sbean);
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