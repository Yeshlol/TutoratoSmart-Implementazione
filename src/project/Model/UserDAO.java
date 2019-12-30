package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.Control.DBConnection;
import project.Utils.Utils;

public class UserDAO  {

	public UserDAO() {
		super();
	}

	public UserBean doRetrieveByMail(String mail) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		UserBean bean = new UserBean();
		
		String selectSql = "SELECT * FROM TS_USER WHERE Email = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, mail);
			
			System.out.println("User doRetrieveByMail: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.wasNull()) {
				System.out.println("Errore esecuzione query!");
	        } else {
	        	int count = rs.last() ? rs.getRow() : 0;
	            if (count == 1) {
	            	bean.setEmail(rs.getString("Email"));
					bean.setPwd(rs.getString("Pwd"));
					bean.setRole(rs.getInt("UserRole"));
					bean.setFirstName(rs.getString("FirstName"));
					bean.setLastName(rs.getString("LastName"));
					bean.setTelephoneNumber(rs.getString("TelephoneNumber"));
					bean.setSex(rs.getString("Sex"));
					bean.setRegistrationNumber(rs.getString("RegistrationNumber"));
										
					System.out.println("Utente trovato con la email!");
	            }
	            else {
	            	System.out.println("Utente non trovato!");
	            	return null;
	            }
	        }
		} catch (SQLException e) {
			System.out.println("Eccezione SQL: " + e.getMessage());
			return null;
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return bean;
	}
	
	
	public synchronized void doSave(UserBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO TS_USER(Email,Pwd,UserRole,FirstName,LastName,TelephoneNumber,Sex,RegistrationNumber) VALUES (?,?,?,?,?,?,?,?)";
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, bean.getEmail());
			preparedStatement.setString(2, Utils.sha256(bean.getPwd()));
			preparedStatement.setInt(3, bean.getRole());
			preparedStatement.setString(4, bean.getFirstName());
			preparedStatement.setString(5, bean.getLastName());
			preparedStatement.setString(6, bean.getTelephoneNumber());
			preparedStatement.setString(7, bean.getSex());
			preparedStatement.setString(8, bean.getRegistrationNumber());
			
			System.out.println("User doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}	
	}
}