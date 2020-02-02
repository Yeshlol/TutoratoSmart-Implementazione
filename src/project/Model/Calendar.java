package project.Model;

import java.sql.SQLException;
import java.util.Collection;


public class Calendar {
	/**
	 * Variables.
	 */
	private static Calendar instance = null;
	private Collection<RequestBean> requests;

	/**
	 * Crea il calendario.
	 * @throws SQLException Eccezione lanciata nel ritrovare le richieste di appuntamento dal DB.
	 */
	public Calendar() throws SQLException {	    
	    RequestDAO activityTutorDAO = new RequestDAO();
	    
	    this.requests = activityTutorDAO.doRetrieveAll();
	}
	
	/**
	 * Get the instance of the calendar.
	 * @throws SQLException Eccezione lanciata nel ritrovare le richieste di appuntamento dal DB.
	 * @return Ritorna l'istanza del Calendario, usata per la visualizzazione tramite FullCalendar.
	 */
	public static Calendar getInstance() throws SQLException {
	    if (instance == null) {
	      instance = new Calendar();
	    }
	    return instance;
	}
	
	/**
	 * Ritorna una collezione di RequestBean.
	 * @return Collection Ritorna la collezione di RequestBean, le richieste di appuntamento presenti nel DB.
	 */
	public Collection<RequestBean> getRequests() {
		return this.requests;
	}
	
	
	/**
	 * Set the Calendar instance to null.
	 */
	public void reset() {
		instance = null;
	}
}
