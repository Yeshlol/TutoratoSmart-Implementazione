package project.Model;

import java.io.Serializable;
import java.sql.Date;

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

	public int getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}

	
	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	
	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	
	public int getRegisterId() {
		return registerId;
	}

	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}

	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	
	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	
	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	
	public float getHours() {
		return hours;
	}

	public void setHours(float hours) {
		this.hours = hours;
	}
	
	
	@Override
	public String toString() {
		return "Attività n° " + idActivity + ", email tutor: " + tutor + ", giorno: " + activityDate + ", orario inizio: " + 
				startTime/60 + ":" + startTime%60 + ", orario termine: " + finishTime/60 + ":" + finishTime%60 + 
				", durata: " + hours + "h, categoria: " + category + ", " + state + ".\nDescrizione: " + details + "\n";
	}
}
