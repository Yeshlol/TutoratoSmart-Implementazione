package project.Model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Questa classe rappresenta l'entita' Attivita'.
 */
public class ActivityTutorBean implements Serializable {
	private static final long serialVersionUID = 8213234379677788321L;
	
	private int idActivity, startTime, finishTime, registerId;
	private String category, state, details, tutor;
	Date activityDate;
	float hours;
	
	public ActivityTutorBean() {
		idActivity = -1;
		startTime = -1;
		finishTime = -1;
		registerId = -1;
		category = "";
		state = "";
		details = "";
		tutor = "";
		activityDate = new Date(-3000000);
		hours = -1.f;
	}
	
	/**
	 * @param idActivity identificativo dell'attivita'
	 * @param startTime tempo di inzio dell'attivita'
	 * @param finishTime tempo di fine dell'attivita'
	 * @param registerId identificativo del registro
	 * @param category categoria dell'attivita'
	 * @param state stato dell'attivita'
	 * @param details dettagli dell'attivita'
	 * @param tutor tutor che si occupa dell'attivita'
	 * @param activityDate data dell'attivita'
	 * @param hours ore dell'attivita'
	 */
	public ActivityTutorBean(int idActivity, int startTime, int finishTime, int registerId, String category, String state, String details, String tutor, Date activityDate, float hours) {
		this.idActivity=idActivity;
		this.startTime=startTime;
		this.finishTime=finishTime;
		this.registerId=registerId;
		this.category=category;
		this.state=state;
		this.details=details;
		this.tutor=tutor;
		this.activityDate=activityDate;
		this.hours=hours;
	}

	/**
	 * @return idActivity identificativo dell'attivita'
	 */
	public int getIdActivity() {
		return idActivity;
	}

	/**
	 * @param idActivity idActivity identificativo dell'attivit� da assegnare all'attivita'
	 * @return
	 */
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}

	/**
	 * @return startTime tempo di inizio dell'attivita'
	 */
	public int getStartTime() {
		return startTime;
	}

	/** 
	 * @param startTime startTime tempo di inizio da assegnare all'attivita'
	 * @return
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return finishTime tempo di fine dell'attivita'
	 */
	public int getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime finishTime tempo di fine da assegnare all'attivita'
	 * @return
	 */
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @return registerId identificativo del registro dell'attivita'
	 */
	public int getRegisterId() {
		return registerId;
	}

	/**
	 * @param registerId registerId identificativo del registro da assegnare all'attivita'
	 * @return
	 */
	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}

	/**
	 * @return category categoria dell'attivita'
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category category categoria da assegnare all'attivita'
	 * @return
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return state stato dell'attivita'
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state state stato da assegnare all'attivita'
	 * @return
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return details dettagli dell'attivita'
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details details dettagli da assegnare all'attivita'
	 * @return
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return tutor tutor che si occupa dell'attivita'
	 */
	public String getTutor() {
		return tutor;
	}

	/**
	 * @param tutor tutor da assegnare all'attivita'
	 * @return
	 */
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	/**
	 * @return activityDate data dell'attivita'
	 */
	public Date getActivityDate() {
		return activityDate;
	}
	
	/**
	 * @param activityDate activityDate data da assegnare all'attivita'
	 * @return
	 */
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	/**
	 * @return hours ore dell'attivita'
	 */
	public float getHours() {
		return hours;
	}

	/**
	 * @param hours hours ore da assegnare all'attivita'
	 * @return
	 */
	public void setHours(float hours) {
		this.hours = hours;
	}
}
