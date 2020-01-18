package project.Model;

import java.io.Serializable;

/**
 * Questa classe rappresenta l'associazione tra l'entita Attivita' e l'entita Appuntamento.
 */
public class ContainedInBean implements Serializable {
	private static final long serialVersionUID = 1487359154874662002L;
	
	private int appointmentId, activityId;
	
	public ContainedInBean() {
		appointmentId = -1;
		activityId = -1;
	}
	
	/**
	 * @param appointmentId identificativo dell'appuntamento
	 * @param activityId identificativo dell'attivita'
	 */
	public ContainedInBean(int appointmentId,int activityId) {
		this.appointmentId=appointmentId;
		this.activityId=activityId;
	}

	/**
	 * @return appointmentId identificativo dell'appuntamento
	 */
	public int getAppointmentId() {
		return appointmentId;
	}

	/**
	 * @param appointmentId appointmentId identificativo dell'appuntamento da assegnare all'associazione ContainedIn
	 * @return
	 */
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	/**
	 * @return activityId identificativo dell'attivita'
	 */
	public int getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId activityId identificativo dell'attivita' da assegnare all'associazione ContainedIn
	 * @return
	 */
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	
	@Override
	public String toString() {
		return "Appuntamento n " + appointmentId + " contenuto nell'attivita' n " + activityId + ".\n";
	}
}