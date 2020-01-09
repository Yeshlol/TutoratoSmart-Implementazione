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
	 * Constructor.
	 * @throws SQLException 
	 */
	public Calendar() throws SQLException {	    
	    RequestDAO activityTutorDAO = new RequestDAO();
	    
	    this.requests = activityTutorDAO.doRetrieveAll();
	}
	
	/**
	 * Get the instance of the calendar.
	 * @throws SQLException 
	 */
	public static Calendar getInstance() throws SQLException {
	    if (instance == null) {
	      instance = new Calendar();
	    }
	    return instance;
	}
	
	/**
	 * Returns the Collection<WorkDayBean> type object.
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
