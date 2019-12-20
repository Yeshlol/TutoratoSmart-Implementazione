package project.Model;

import java.io.Serializable;

public class ContainedInBean implements Serializable {
	private static final long serialVersionUID = 1487359154874662002L;
	
	private int appointmentId, activityId;
	
	public ContainedInBean() {
		appointmentId = -1;
		activityId = -1;
	}

	
	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	
	@Override
	public String toString() {
		return "Appuntamento' n° " + appointmentId + " contenuto nell'attivita' n° " + activityId + ".\n";
	}
}