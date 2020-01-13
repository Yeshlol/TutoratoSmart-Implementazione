package project.Model;

import java.io.Serializable;

public class AppointmentBean implements Serializable {
	private static final long serialVersionUID = -680124132381248316L;
	
	private int idAppointment, requestId;
	private String details, tutor;
	
	public AppointmentBean() {
		idAppointment = -1;
		requestId = -1;
		details = "";
		tutor = "";
	}
	
	public AppointmentBean(int idAppointment,String details,int requestId,String tutor) {
		this.idAppointment=idAppointment;
		this.requestId=requestId;
		this.details=details;
		this.tutor=tutor;
	}

	public int getIdAppointment() {
		return idAppointment;
	}

	public void setIdAppointment(int idAppointment) {
		this.idAppointment = idAppointment;
	}

	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
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
	
	
	@Override
	public String toString() {
		return "Appuntamento n " + idAppointment + "\nDescrizione: " + details + "\nAssociato alla richiesta n° "+ requestId + " e al tutor " + tutor + "\n";
	}
}