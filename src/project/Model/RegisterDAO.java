package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.Control.DBConnection;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Registro.
 */
public class RegisterDAO  {		
	public RegisterDAO() {
		super();
	}

	/** 
	 * @param
	 * @return un ArrayList di tutti i registri registrati nel database
	 * @throws SQLException
	 */
	public synchronized ArrayList<RegisterBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		ArrayList<RegisterBean> registerList = new ArrayList<RegisterBean>();
		String sql = "select * from REGISTER";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			RegisterBean bean = null;
        	int id=rs.getInt("IdRegister");
			String state=rs.getString("State");
			float validateHours=rs.getFloat("ValidatedHours");
			float percentageComplete=rs.getInt("PercentageComplete");
			
			bean = new RegisterBean(id,state,validateHours,percentageComplete);
			registerList.add(bean);
		}
		return registerList;
	}
	
	/** 
	 * @param id
	 * @return un registro, tramite id, registrato nel database
	 * @throws SQLException
	 */
	public synchronized RegisterBean doRetrieveById(int id) throws SQLException {
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
			// System.out.println("Id non trovato!");
			return null;
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return bean;
	}
	
	/** 
	 * @param totalHours
	 * @return un intero per il salvataggio di un registro nel database
	 * @throws SQLException
	 */
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
			
			// System.out.println("Register doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(selectSql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				idRegister = rs.getInt("IdRegister");
			}
			
			// System.out.println("IdRegister added: " + idRegister);
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return idRegister;
	}
		
	/** 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
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
			
			// System.out.println("Register doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}
}
