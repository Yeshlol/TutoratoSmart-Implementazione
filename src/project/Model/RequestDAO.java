package project.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import project.Control.DBConnection;

/**
 * Questa classe � un manager che si occupa di interagire con il database. Gestisce le query riguardanti Richiesta.
 */
public class RequestDAO  {		
	public RequestDAO() {
		super();
	}

	/** 
	 * @param id
	 * @return una richiesta, tramite id, registrata nel database
	 * @throws SQLException
	 */
	public synchronized RequestBean doRetrieveById(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		RequestBean bean = new RequestBean();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM REQUEST WHERE IdRequest = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setInt(1, id);
			
			//System.out.println("Request doRetrieveById: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setIdRequest(rs.getInt("IdRequest"));
				bean.setState(rs.getString("State"));
				bean.setStudentComment(rs.getString("StudentComment"));
				bean.setRequestDate(rs.getDate("RequestDate"));
				bean.setRequestTime(rs.getInt("RequestTime"));
				bean.setDuration(rs.getInt("Duration"));
				bean.setStudent(rs.getString("Student"));
				
				//System.out.println("Richiesta Trovata con l'id!");
			}
		} catch (SQLException e) {
			//System.out.println("Id non trovato!");
			return null;
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return bean;
	}
	
	/** 
	 * @param bean
	 * @return un intero per il salvataggio di una richiesta nel database
	 * @throws SQLException
	*/
	@SuppressWarnings("resource")
	public synchronized int doSave(RequestBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String insertSql = "INSERT INTO REQUEST(StudentComment,RequestDate,RequestTime,Student)"
						 + " VALUES (?,?,?,?)";
		
		int idRequest = -1;
		
		String selectSql = "SELECT MAX(IdRequest) AS IdRequest FROM REQUEST";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, bean.getStudentComment());
			preparedStatement.setDate(2, bean.getRequestDate());
			preparedStatement.setInt(3, bean.getRequestTime());		
			preparedStatement.setString(4, bean.getStudent());
			
			// System.out.println("Request doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(selectSql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				idRequest = rs.getInt("IdRequest");
			}			
			// System.out.println("IdRequest added: " + idRequest);
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return idRequest;
	}
		
	/** 
	 * @param bean
	 * @return
	 * @throws SQLException
	*/
	public synchronized void doModify(RequestBean bean) throws SQLException {		
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE REQUEST SET State=?,StudentComment=?,RequestDate=?,RequestTime=? WHERE IdRequest = ?";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSql);
			
			preparedStatement.setString(1, bean.getState());
			preparedStatement.setString(2, bean.getStudentComment());
			preparedStatement.setDate(3, bean.getRequestDate());
			preparedStatement.setInt(4, bean.getRequestTime());
			preparedStatement.setInt(5, bean.getIdRequest());
			
			// System.out.println("Request doModify: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}
	
	/** 
	 * @param bean
	 * @param tutorMail
	 * @return
	 * @throws SQLException
	*/
	public synchronized void doAccept(RequestBean bean, String tutorMail) throws SQLException {		
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE REQUEST SET State='Accettata',Duration=? WHERE IdRequest = ?";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSql);
	
			preparedStatement.setInt(1, bean.getDuration());
			preparedStatement.setInt(2, bean.getIdRequest());
			
			// System.out.println("Request doAccept: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			ManagesDAO managesDAO = new ManagesDAO();
			ManagesBean manage = new ManagesBean();
			manage.setRequestId(bean.getIdRequest());
			manage.setTutor(tutorMail);
			managesDAO.doSave(manage);
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}

	/** 
	 * @param bean
	 * @return un booleano per controllare la cancellazione di un'attivit� nel database
	 * @throws SQLException
	*/
	public synchronized boolean doDelete(RequestBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "DELETE FROM REQUEST WHERE IdRequest = ?";
		int result;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, bean.getIdRequest());
			
			// System.out.println("Request doDelete: " + preparedStatement.toString());
			result = preparedStatement.executeUpdate();
			
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return (result!=0);		
	}

	/** 
	 * @param order
	 * @param studentMail
	 * @return una Collection di richieste, tramite mail dello studente, registrate nel database
	 * @throws SQLException
	 */
	public Collection<RequestBean> doRetrieveAllByMail(String order, String studentMail) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<RequestBean> list = new LinkedList<RequestBean>();
		
		String selectSql = "SELECT SQL_NO_CACHE * FROM REQUEST WHERE Student = ? ";
		
		if (order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setString(1, studentMail);
			
			// System.out.println("Request doRetrieveAllByMail: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				RequestBean bean = new RequestBean();
				bean.setIdRequest(rs.getInt("IdRequest"));
				bean.setState(rs.getString("State"));
				bean.setStudentComment(rs.getString("StudentComment"));
				bean.setRequestDate(rs.getDate("RequestDate"));
				bean.setRequestTime(rs.getInt("RequestTime"));
				bean.setDuration(rs.getInt("Duration"));
				bean.setStudent(rs.getString("Student"));
							
				list.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return list;
	}
	
	/** 
	 * @param 
	 * @return una Collection di tutte le richieste registrate nel database
	 * @throws SQLException
	 */
	public Collection<RequestBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<RequestBean> list = new LinkedList<RequestBean>();
		
		String selectSql = "SELECT * FROM REQUEST";
				
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			
			// System.out.println("Request doRetrieveAll: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				RequestBean bean = new RequestBean();
				bean.setIdRequest(rs.getInt("IdRequest"));
				bean.setState(rs.getString("State"));
				bean.setStudentComment(rs.getString("StudentComment"));
				bean.setRequestDate(rs.getDate("RequestDate"));
				bean.setRequestTime(rs.getInt("RequestTime"));
				bean.setDuration(rs.getInt("Duration"));
				bean.setState(rs.getString("State"));
				bean.setStudent(rs.getString("Student"));
				
				list.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return list;
	}

	/** 
	 * @param order
	 * @param startResearchDate
	 * @param finishResearchDate
	 * @return una Collection di richieste, tramite date di inizio e di fine, registrate nel database
	 * @throws SQLException
	 */
	public Collection<RequestBean> doRetrieveAllByDates(String order, String studentMail, Date startResearchDate, Date finishResearchDate) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<RequestBean> requestsList = new LinkedList<RequestBean>();
		
		String selectSql = "SELECT * FROM REQUEST AS R WHERE R.RequestDate >= ? AND R.RequestDate <= ? AND R.Student = ?";
		
		if(order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			preparedStatement.setDate(1, startResearchDate);
			preparedStatement.setDate(2, finishResearchDate);
			preparedStatement.setString(3, studentMail);
			
			// System.out.println("Request doRetrieveAllByDates: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				RequestBean bean = new RequestBean();
				bean.setIdRequest(rs.getInt("IdRequest"));
				bean.setState(rs.getString("State"));
				bean.setStudentComment(rs.getString("StudentComment"));
				bean.setRequestDate(rs.getDate("RequestDate"));
				bean.setRequestTime(rs.getInt("RequestTime"));
				bean.setDuration(rs.getInt("Duration"));
				bean.setStudent(rs.getString("Student"));
				
				requestsList.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		
		return requestsList;
	}
	
	/** 
	 * @param order
	 * @return una Collection di richieste, tramite order, registrate nel database
	 * @throws SQLException
	 */
	public Collection<RequestBean> doRetrieveAllPending(String order) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<RequestBean> list = new LinkedList<RequestBean>();
		
		String selectSql = "SELECT * FROM REQUEST WHERE STATE = 'In valutazione'";
		
		if(order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			
			// System.out.println("Request doRetrieveAllPending: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				RequestBean bean = new RequestBean();
				bean.setIdRequest(rs.getInt("IdRequest"));
				bean.setState(rs.getString("State"));
				bean.setStudentComment(rs.getString("StudentComment"));
				bean.setRequestDate(rs.getDate("RequestDate"));
				bean.setRequestTime(rs.getInt("RequestTime"));
				bean.setDuration(rs.getInt("Duration"));
				bean.setStudent(rs.getString("Student"));
				
				list.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return list;
	}
	
	/** 
	 * @param requestDate
	 * @param requestTime
	 * @return un booleano per controllare se la richiesta � disponibile e registrata nel database
	 * @throws SQLException
	 */
	public boolean isAvailable(Date requestDate, int requestTime) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "SELECT IdRequest FROM REQUEST WHERE RequestDate = ? AND ? BETWEEN RequestTime AND RequestTime+Duration ";
		
		try {
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setDate(1, requestDate);
			preparedStatement.setInt(2, requestTime);
			
			System.out.println("Request isAvailable: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.wasNull()) {
				System.out.println("Errore esecuzione query!");
				return false;
	        } else {
	        	int count = rs.last() ? rs.getRow() : 0;
	        	
	        	if (count > 0) {
	            	// System.out.println("Orario non disponibile! Esiste già una prenotazione!");
	        		return false;
	            }
	            else {
	            	System.out.println("Orario disponibile!");
	            	return true;
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return false;
	}

	/** 
	 * @param requestDate
	 * @param requestTime
	 * @param requestId
	 * @return un booleano per controllare se vi sono differenti richieste registrate nel database
	 * @throws SQLException
	 */
	public boolean differentRequestRegistered(Date requestDate, int requestTime, int requestId) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "SELECT IdRequest FROM REQUEST WHERE RequestDate = ? AND ? BETWEEN RequestTime AND RequestTime+Duration AND IdRequest != ?";
		
		try {
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setDate(1, requestDate);
			preparedStatement.setInt(2, requestTime);
			preparedStatement.setInt(3, requestId);
			
			System.out.println("Request isAvailableModify: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.wasNull()) {
				System.out.println("Errore esecuzione query!");
				return false;
	        } else {
	        	int count = rs.last() ? rs.getRow() : 0;
	            if (count != 0) {
	            	System.out.println("Orario non disponibile! Esiste già una prenotazione!");
	            	return false;
	            }
	            else {
	            	System.out.println("Orario disponibile!");
	            	return true;
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return false;
	}
	
	/** 
	 * @param idRequest
	 * @return 
	 * @throws SQLException
	 */
	public void confirmAppointment(int idRequest) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE REQUEST SET State='Appuntamento effettuato' WHERE IdRequest = ?";
		
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSql);
	
			preparedStatement.setInt(1, idRequest);
			
			// System.out.println("Request doConfirmAppointment: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
						
			connection.commit();
		}
		finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}

	/** 
	 * @param order
	 * @return una Collection di richieste accettate, tramite order, registrate nel database
	 * @throws SQLException
	 */
	public Collection<RequestBean> doRetrieveAllAccepted(String order) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement = null;
		
		Collection<RequestBean> list = new LinkedList<RequestBean>();
		
		String selectSql = "SELECT * FROM REQUEST WHERE STATE = 'Accettata'";
		
		if(order!=null && !order.equals("")) {
			selectSql +=" ORDER BY " + order;
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectSql);
			
			// System.out.println("Request doRetrieveAllAccepted: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				RequestBean bean = new RequestBean();
				bean.setIdRequest(rs.getInt("IdRequest"));
				bean.setState(rs.getString("State"));
				bean.setStudentComment(rs.getString("StudentComment"));
				bean.setRequestDate(rs.getDate("RequestDate"));
				bean.setRequestTime(rs.getInt("RequestTime"));
				bean.setDuration(rs.getInt("Duration"));
				bean.setStudent(rs.getString("Student"));
				
				list.add(bean);
			}			
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}
		return list;
	}
}
