package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

/**
 * Questa classe � un manager che si occupa di interagire con il database. Gestisce le query riguardanti Valida.
 */
public class ValidatesDAO {
	public ValidatesDAO() {
		super();
	}
	
	/** 
	 * @return un ArrayList di tutte le validazioni registrate nel database
	 * @throws SQLException lancia un'eccezione SQL
	 */
	public synchronized ArrayList<ValidatesBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		ArrayList<ValidatesBean> validatesList = new ArrayList<ValidatesBean>();
		
		String sql = "select * from VALIDATES ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			ValidatesBean bean = new ValidatesBean();
			String commissionMember = rs.getString("CommissionMember");
			int activityId = rs.getInt("ActivityId");
			
			bean = new ValidatesBean(commissionMember, activityId);
			
			validatesList.add(bean);
		}
		return validatesList;
	}

	/** 
	 * @param commissionMemberMail email del membro della commissione
	 * @return una Collection di attivita, tramite mail del membro della commissione, registrate nel database
	 * @throws SQLException lancia un'eccezione SQL
	 */
	public synchronized Collection<ActivityTutorBean> doRetrieveByCommissionMember(String commissionMemberMail) throws SQLException {
		Collection<ActivityTutorBean> list = new LinkedList<ActivityTutorBean>();
				
		if(commissionMemberMail != null && !commissionMemberMail.equals("")) {
			Connection connection = DBConnection.getInstance().getConn();
			PreparedStatement preparedStatement = null;
			
			String selectSql = "SELECT SQL_NO_CACHE * FROM VALIDATES,ACTIVITY_TUTOR WHERE CommissionMember = ?";
			
			try {
				preparedStatement = connection.prepareStatement(selectSql);
				preparedStatement.setString(1, commissionMemberMail);
				
				// System.out.println("Validates doRetrieveByCommissionMember: " + preparedStatement.toString());
				
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
	
	/** 
	 * @param bean bean
	 * @throws SQLException lancia un'eccezione SQL
	 */
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
		
		// System.out.println("Ore validate: " + register.getValidatedHours() + "\tOre totali: " + register.getTotalHours() + "\tPercentuale: " + register.getPercentageComplete() + "%");
		
		try {			
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			
			preparedStatement.setString(1,bean.getCommissionMember());
			preparedStatement.setInt(2,bean.getActivityId());
									
			// System.out.println("Validates doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(updateSql);
			preparedStatement.setInt(1, bean.getActivityId());
			
			// System.out.println("Validates doValidateActivity: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			if(register.getPercentageComplete() >= 100) {
				register.setPercentageComplete(100.f);
				register.setState("Approvato");
				
				TutorDAO tutorDAO = new TutorDAO();
				TutorBean tutor = tutorDAO.doRetrieveByRegisterId(register.getIdRegister());
				tutorDAO.doUpdateState(tutor);
			}
			
			registerDAO.doUpdate(register);
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}
}
