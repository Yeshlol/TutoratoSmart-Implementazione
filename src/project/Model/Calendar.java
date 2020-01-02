package project.Model;

import java.sql.SQLException;
import java.util.Collection;


public class Calendar {
	/**
	 * Variables.
	 */
	private static Calendar instance = null;
	private Collection<WorkDayBean> workDays;
	private Collection<ActivityTutorBean> activities;

	/**
	 * Constructor.
	 * @throws SQLException 
	 */
	public Calendar() throws SQLException {
	    WorkDayDAO workDayDAO = new WorkDayDAO();
	    
	    this.workDays = workDayDAO.doRetrieveAll();
	    
	    ActivityTutorDAO activityTutorDAO = new ActivityTutorDAO();
	    
	    this.activities = activityTutorDAO.doRetrieveAll();
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
	public Collection<WorkDayBean> getWorkDays() {
		return this.workDays;
	}
	
	/**
	 * Returns the Collection<WorkDayBean> type object.
	 */
	public Collection<ActivityTutorBean> getActivities() {
		return this.activities;
	}	
}
