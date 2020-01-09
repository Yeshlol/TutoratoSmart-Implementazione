package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.Control.DBConnection;

public class RegisterDAO  {		
	public RegisterDAO() {
		super();
	}

	
	public RegisterBean doRetrieveById(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		RegisterBean bean = new RegisterBean();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM REGISTER WHERE IdRegister = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, id);
			
			//System.out.println("Register doRetrieveById: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setIdRegister(rs.getInt("IdRegister"));
				bean.setState(rs.getString("State"));
				bean.setValidatedHours(rs.getFloat("ValidatedHours"));
				bean.setTotalHours(rs.getInt("TotalHours"));
				bean.setPercentageComplete(rs.getFloat("PercentageComplete"));
				
				//System.out.println("Registro Trovato con l'id!");
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
	
	
	@SuppressWarnings("resource")
	public synchronized int doSave(int totalHours) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		int idRegister = -1;
		
		String insertSql = "INSERT INTO REGISTER(TotalHours) VALUES (?)";
		
		String selectSql = "SELECT MAX(IdRegister) AS IdRegister FROM REGISTER";
		
		try {
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setInt(1, totalHours);
			
			System.out.println("Register doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(selectSql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				idRegister = rs.getInt("IdRegister");
			}
			
			System.out.println("IdRegister added: " + idRegister);
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return idRegister;
	}
		
	
	public synchronized void doUpdate(RegisterBean bean) throws SQLException {		
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE REGISTER SET State=?,ValidatedHours=?,PercentageComplete=? WHERE IdRegister = ?";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSql);
	
			preparedStatement.setString(1, bean.getState());
			preparedStatement.setFloat(2, bean.getValidatedHours());
			preparedStatement.setFloat(3, bean.getPercentageComplete());
			preparedStatement.setInt(4, bean.getIdRegister());
			
			System.out.println("Register doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}
}
