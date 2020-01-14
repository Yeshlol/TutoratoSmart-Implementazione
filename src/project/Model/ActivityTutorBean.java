package project.Model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Questa classe rappresenta l'entit� Attivit�.
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
	 * @param idActivity identificativo dell'attivit�
	 * @param startTime tempo di inzio dell'attivit�
	 * @param finishTime tempo di fine dell'attivit�
	 * @param registerId identificativo del registro
	 * @param category categoria dell'attivit�
	 * @param state stato dell'attivit�
	 * @param details dettagli dell'attivit�
	 * @param tutor tutor che si occupa dell'attivit�
	 * @param activityDate data dell'attivit�
	 * @param hours ore dell'attivit�
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
	 * @return idActivity identificativo dell'attivit�
	 */
	public int getIdActivity() {
		return idActivity;
	}

	/**
	 * @param idActivity idActivity identificativo dell'attivit� da assegnare all'attivit�
	 * @return
	 */
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}

	/**
	 * @return startTime tempo di inizio dell'attivit�
	 */
	public int getStartTime() {
		return startTime;
	}

	/** 
	 * @param startTime startTime tempo di inizio da assegnare all'attivit�
	 * @return
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return finishTime tempo di fine dell'attivit�
	 */
	public int getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime finishTime tempo di fine da assegnare all'attivit�
	 * @return
	 */
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @return registerId identificativo del registro dell'attivit�
	 */
	public int getRegisterId() {
		return registerId;
	}

	/**
	 * @param registerId registerId identificativo del registro da assegnare all'attivit�
	 * @return
	 */
	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}

	/**
	 * @return category categoria dell'attivit�
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category category categoria da assegnare all'attivit�
	 * @return
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return state stato dell'attivit�
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state state stato da assegnare all'attivit�
	 * @return
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return details dettagli dell'attivit�
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details details dettagli da assegnare all'attivit�
	 * @return
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return tutor tutor che si occupa dell'attivit�
	 */
	public String getTutor() {
		return tutor;
	}

	/**
	 * @param tutor tutor da assegnare all'attivit�
	 * @return
	 */
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	/**
	 * @return activityDate data dell'attivit�
	 */
	public Date getActivityDate() {
		return activityDate;
	}
	
	/**
	 * @param activityDate activityDate data da assegnare all'attivit�
	 * @return
	 */
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	/**
	 * @return hours ore dell'attivit�
	 */
	public float getHours() {
		return hours;
	}

	/**
	 * @param hours hours ore da assegnare all'attivit�
	 * @return
	 */
	public void setHours(float hours) {
		this.hours = hours;
	}
	
	
	@Override
	public String toString() {
		return "Attivita' n " + idActivity + ", email tutor: " + tutor + ", giorno: " + activityDate + ", orario inizio: " + 
				startTime/60 + ":" + startTime%60 + ", orario termine: " + finishTime/60 + ":" + finishTime%60 + 
				", durata: " + hours + "h, categoria: " + category + ", " + state + ".\nDescrizione: " + details + "\n";
	}
}
