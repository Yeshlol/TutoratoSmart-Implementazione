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
import project.Utils.Utils;

/**
 * Questa classe è un manager che si occupa di interagire con il database. Gestisce le query riguardanti Studenti.
 */
public class StudentDAO  {		
	public StudentDAO() {
		super();
	}
	
	/** 
	 * @param
	 * @return un ArrayList di tutti gli studenti registrati nel database
	 * @throws SQLException
	 */
	public synchronized ArrayList<StudentBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		ArrayList<StudentBean> tutorList = new ArrayList<StudentBean>();
		String sql = "select * from STUDENT,TS_USER WHERE TS_USER.Email = STUDENT.Email";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			StudentBean bean = null;
        	String email = rs.getString("Email");
			String password = rs.getString("Pwd");
			int userRole = rs.getInt("UserRole");
			String firstname = rs.getString("FirstName");
			String lastname = rs.getString("LastName");
			String telephoneNumber = rs.getString("TelephoneNumber");
			String sex = rs.getString("Sex");
			String registrationNumber = rs.getString("RegistrationNumber");
			int academicYear=rs.getInt("AcademicYear");
			
			
			bean = new StudentBean(email, password, userRole, firstname, lastname, telephoneNumber, sex, registrationNumber,academicYear);
			tutorList.add(bean);
		}
		return tutorList;
	}

	/** 
	 * @param mail
	 * @return uno studente, tramite mail, registrato nel database
	 * @throws SQLException
	 */
	public synchronized StudentBean doRetrieveByMail(String mail) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		StudentBean bean = new StudentBean();
		
		String selectSql = "SELECT * FROM TS_USER,STUDENT WHERE TS_USER.Email = ? AND TS_USER.Email = STUDENT.Email";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, mail);
			
			//System.out.println("Student doRetrieveByMail: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.wasNull()) {
				// System.out.println("Errore esecuzione query!");
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
					bean.setAcademicYear(rs.getInt("AcademicYear"));
					
					//System.out.println("Studente trovato con la email!");
	            }
	            //else
	            	//System.out.println("Studente non trovato!");
	        }
		} catch (SQLException e) {
			// System.out.println("Eccezione SQL: " + e.getMessage());
			return null;
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return bean;
	}
	
	/** 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public synchronized void doSave(StudentBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO TS_USER(Email,Pwd,UserRole,FirstName,LastName,TelephoneNumber,Sex,RegistrationNumber) VALUES (?,?,?,?,?,?,?,?)";
		String insertSql2 = "INSERT INTO STUDENT (Email,AcademicYear) VALUES (?,?)";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, bean.getEmail());
			preparedStatement.setString(2, Utils.sha256(bean.getPwd()));
			preparedStatement.setInt(3, 3);
			preparedStatement.setString(4, bean.getFirstName());
			preparedStatement.setString(5, bean.getLastName());
			preparedStatement.setString(6, bean.getTelephoneNumber());
			preparedStatement.setString(7, bean.getSex());
			preparedStatement.setString(8, bean.getRegistrationNumber());
			
			System.out.println("User doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(insertSql2);
			preparedStatement.setString(1, bean.getEmail());
			preparedStatement.setInt(2, bean.getAcademicYear());
			
			System.out.println("Student doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}	
	}
	
	/** 
	 * @param order
	 * @param startResearchDate
	 * @param finishReasearchDate
	 * @return una Collection di studenti, tramite date di inizio e di fine, registrati nel database
	 * @throws SQLException
	 */
	public synchronized Collection<StudentBean> doRetrieveAllByDates(String order, Date startResearchDate, Date finishResearchDate) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<StudentBean> studentsList = new LinkedList<StudentBean>();
		
		String selectSql = "SELECT * FROM STUDENT AS S, REQUEST AS R, TS_USER AS T "
				+ "WHERE R.RequestDate >= ? AND R.RequestDate <= ? AND R.Student = S.Email AND S.Email = T.Email";
		
		if(order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setDate(1, startResearchDate);
			preparedStatement.setDate(2, finishResearchDate);
			
			// System.out.println("Student doRetrieveAllByDates: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				StudentBean sbean = new StudentBean();
				sbean.setEmail(rs.getString("Email"));
				sbean.setPwd(rs.getString("Pwd"));
				sbean.setRole(rs.getInt("UserRole"));
				sbean.setFirstName(rs.getString("FirstName"));
				sbean.setLastName(rs.getString("LastName"));
				sbean.setTelephoneNumber(rs.getString("TelephoneNumber"));
				sbean.setSex(rs.getString("Sex"));
				sbean.setRegistrationNumber(rs.getString("RegistrationNumber"));
				sbean.setAcademicYear(rs.getInt("AcademicYear"));
				
				studentsList.add(sbean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return studentsList;
	}
}
