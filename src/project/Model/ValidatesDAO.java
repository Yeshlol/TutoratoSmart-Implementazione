package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

public class ValidatesDAO {
	public ValidatesDAO() {
		super();
	}

	public  synchronized Collection<ActivityTutorBean> doRetrieveByCommissionMember(String commissionMemberMail) throws SQLException {
		Collection<ActivityTutorBean> list = new LinkedList<ActivityTutorBean>();
				
		if(commissionMemberMail != null && !commissionMemberMail.equals("")) {
			Connection connection = DBConnection.getInstance().getConn();
			PreparedStatement preparedStatement = null;
			
			String selectSql = "SELECT SQL_NO_CACHE * FROM VALIDATES,ACTIVITY_TUTOR WHERE CommissionMember = ?";
			
			try {
				preparedStatement = connection.prepareStatement(selectSql);
				preparedStatement.setString(1, commissionMemberMail);
				
				System.out.println("Validates doRetrieveByCommissionMember: " + preparedStatement.toString());
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					ActivityTutorBean bean = new ActivityTutorBean();
					bean.setIdActivity(rs.getInt("IdActivity"));
					bean.setCategory(rs.getString("Category"));
					bean.setActivityDate(rs.getDate("ActivityDate"));
					bean.setStartTime(rs.getInt("StartTime"));
					bean.setFinishTime(rs.getInt("FinishTime"));
					bean.setHours(rs.getFloat("Hours"));
					bean.setState(rs.getString("State"));
					bean.setDetails(rs.getString("Details"));
					bean.setTutor(rs.getString("Tutor"));
					bean.setRegisterId(rs.getInt("RegisterId"));
					
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
		}
		return list;
	}
	

	@SuppressWarnings("resource")
	public synchronized void doSave(ValidatesBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement=null;
		
		String insertSql = "INSERT INTO VALIDATES(CommissionMember,ActivityId) VALUES (?,?)";
		String updateSql = "UPDATE ACTIVITY_TUTOR SET State='Convalidata' WHERE IdActivity = ?";
		
		try {			
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			
			preparedStatement.setString(1,bean.getCommissionMember());
			preparedStatement.setInt(2,bean.getActivityId());
									
			System.out.println("Validates doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(updateSql);
			preparedStatement.setInt(1, bean.getActivityId());
			
			System.out.println("Validates doValidateActivity: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		}finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			}finally {
				connection.close();
			}
		}		
	}
}
