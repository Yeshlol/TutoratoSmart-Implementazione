package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.Control.DBConnection;

public class CommissionMemberDAO  {		
	public CommissionMemberDAO() {
		super();
	}

	
	public CommissionMemberBean doRetrieveByMail(String mail) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		CommissionMemberBean bean = new CommissionMemberBean();
		
		String selectSql = "SELECT * FROM TS_USER WHERE Email = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, mail);
			
			System.out.println("CommissionMember doRetrieveByMail: " + preparedStatement.toString());
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
					
					System.out.println("Membro della Commissione trovato con la email!");
	            }
	            else
	            	System.out.println("Membro della Commissione non trovato!");
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
}