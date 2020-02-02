package project.Model;

import java.io.Serializable;

/**
 * Questa classe rappresenta l'entita' Appuntamento.
 */
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
	
	/**
	 * @param idAppointment identificativo dell'appuntamento
	 * @param details dettagli dell'appuntamento
	 * @param requestId identificativo della richiesta
	 * @param tutor tutor che si occupa dell'appuntamento
	 */
	public AppointmentBean(int idAppointment,String details,int requestId,String tutor) {
		this.idAppointment=idAppointment;
		this.requestId=requestId;
		this.details=details;
		this.tutor=tutor;
	}

	/**
	 * @return idAppointment identificativo dell'appuntamento
	 */
	public int getIdAppointment() {
		return idAppointment;
	}

	/**
	 * @param idAppointment idAppointment identificativo dell'appuntamento da assegnare all'appuntamento
	 */
	public void setIdAppointment(int idAppointment) {
		this.idAppointment = idAppointment;
	}

	/**
	 * @return requestId identificativo dell'appuntamento
	 */
	public int getRequestId() {
		return requestId;
	}

	/**
	 * @param  requestId requestId identificativo della richiesta da assegnare all'appuntamento
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return details dettagli dell'appuntamento
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details details dettagli dell'appuntamento da assegnare all'appuntamento
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return tutor tutor che si occupa dell'appuntamento
	 */
	public String getTutor() {
		return tutor;
	}

	/**
	 * @param tutor tutor da assegnare all'appuntamento
	 */
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
}