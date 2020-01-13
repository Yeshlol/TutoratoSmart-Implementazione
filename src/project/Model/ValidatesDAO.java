package project.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

public class ValidatesDAO {
	public ValidatesDAO() {
		super();
	}
	
	public synchronized ArrayList<ActivityTutorBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		ArrayList<ActivityTutorBean> activityList = new ArrayList<ActivityTutorBean>();
		String sql = "select * from VALIDATES,ACTIVITY_TUTOR WHERE VALIDATES.ActivityId = ACTIVITY_TUTOR.IdActivity";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			ActivityTutorBean bean = new ActivityTutorBean();
			int idActivity = rs.getInt("IdActivity");
			String category = rs.getString("Category");
			Date activityDate = rs.getDate("ActivityDate");
			int startTime = rs.getInt("StartTime");
			int finishTime = rs.getInt("FinishTime");
			float hours = rs.getFloat("Hours");
			String state = rs.getString("State");
			String details = rs.getString("Details");
			String tutor = rs.getString("Tutor");
			int registerId = rs.getInt("RegisterId");
			
			
			bean = new ActivityTutorBean(idActivity, startTime, finishTime, registerId, category, state, details, tutor, activityDate, hours);
			activityList.add(bean);
		}
		return activityList;
	}

	public synchronized Collection<ActivityTutorBean> doRetrieveByCommissionMember(String commissionMemberMail) throws SQLException {
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
				if(preparedStatement != null)
					preparedStatement.close();
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
		
		ActivityTutorDAO activityDAO = new ActivityTutorDAO();
		ActivityTutorBean activity = activityDAO.doRetrieveById(bean.getActivityId());
		
		RegisterDAO registerDAO = new RegisterDAO();
		RegisterBean register = registerDAO.doRetrieveById(activity.getRegisterId());
		
		float hours = activity.getHours();
		register.setValidatedHours(register.getValidatedHours() + hours);
		register.setPercentageComplete((register.getValidatedHours() / register.getTotalHours()) * 100);
		
		System.out.println("Ore validate: " + register.getValidatedHours() + "\tOre totali: " + register.getTotalHours() + "\tPercentuale: " + register.getPercentageComplete() + "%");
		
		if(register.getPercentageComplete() >= 100) {
			register.setState("Approvato");
		}
		
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
			
			registerDAO.doUpdate(register);
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}
}
