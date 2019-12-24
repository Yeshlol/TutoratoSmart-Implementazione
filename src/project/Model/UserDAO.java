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
			
			while(rs.next()) {
				bean.setEmail(rs.getString("Email"));
				bean.setPwd(rs.getString("Pwd"));
				bean.setFirstName(rs.getString("FirstName"));
				bean.setLastName(rs.getString("LastName"));
				bean.setTelephoneNumber(rs.getString("TelephoneNumber"));
				bean.setSex(rs.getString("Sex"));
				bean.setRegistrationNumber(rs.getString("RegistrationNumber"));
				
				System.out.println("Utente Trovato con la email!");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
			System.out.println("Email non trovata!");
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
		
		String insertSql = "INSERT INTO TS_USER(Email,Pwd,FirstName,LastName,TelephoneNumber,Sex,RegistrationNumber) VALUES (?,?,?,?,?,?,?)";
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
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}	
	}
}