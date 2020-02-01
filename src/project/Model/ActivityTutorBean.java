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
	 * Imposta l'id dell'attivita'
	 * @param idActivity idActivity identificativo dell'attivita' da assegnare all'attivita'
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
	 * Imposta il tempo di inizio dell'attività.
	 * @param startTime startTime tempo di inizio da assegnare all'attivita'.
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
	 * Imposta il tempo di fine attivita'.
	 * @param finishTime finishTime tempo di fine da assegnare all'attivita'.
	 */
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @return registerId identificativo del registro dell'attivita'.
	 */
	public int getRegisterId() {
		return registerId;
	}

	/**
	 * Imposta l'id del registro a cui è associata l'attivita'.
	 * @param registerId registerId identificativo del registro da assegnare all'attivita'.
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
	 * Imposta la categoria dell'attivita'.
	 * @param category Categoria da assegnare all'attivita'.
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
	 * Imposta lo stato dell'attivita'.
	 * @param state Stato da assegnare all'attivita'.
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
	 * Imposta i dettagli dell'attivita'.
	 * @param details Dettagli da assegnare all'attivita'.
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
	 * Imposta il tutor dell'attivita'.
	 * @param tutor Tutor da assegnare all'attivita'.
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
	 * Imposta la data dell'attivita'.
	 * @param activityDate activityDate data da assegnare all'attivita'.
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
	 * Imposta le ore di attivita' svolte.
	 * @param hours hours ore da assegnare all'attivita'.
	 */
	public void setHours(float hours) {
		this.hours = hours;
	}
}
