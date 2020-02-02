package project.Model;

import java.io.Serializable;
import java.sql.Date;


/**
 * Questa classe rappresenta l'entita' Richiesta.
 */
public class RequestBean implements Serializable {
	private static final long serialVersionUID = -1814614838729922562L;
	
	private int idRequest, requestTime, duration;
	private String state, studentComment, student;
	private Date requestDate;
	
	public RequestBean() {
		idRequest = -1;
		requestTime = -1;
		duration = -1;
		state = "";
		studentComment = "";
		student = "";
		requestDate = new Date(-3000000);
	}
	
	
	/**
	 * @param idRequest identificativo della richiesta
	 * @param requestTime tempo della richiesta
	 * @param duration durata della richiesta
	 * @param state stato della richiesta
	 * @param studentComment commento dello studente per una richiesta
	 * @param student studente che effettua la richiesta
	 * @param requestDate data della richiesta
	 */
	public RequestBean(int idRequest, int requestTime, int duration, String state, String studentComment, String student, Date requestDate) {
		this.idRequest=idRequest;
		this.requestTime=requestTime;
		this.duration=duration;
		this.state=state;
		this.studentComment=studentComment;
		this.student=student;
		this.requestDate=requestDate;
	}

	/**
	 * @return idRequest identificativo della richiesta
	 */
	public int getIdRequest() {
		return idRequest;
	}

	/**
	 * @param idRequest idRequest identificativo della richiesta da assegnare alla richiesta
	 */
	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}

	/**
	 * @return requestTime tempo della richiesta 
	 */
	public int getRequestTime() {
		return requestTime;
	}

	/**
	 * @param requestTime requestTime tempo della richiesta da assegnare alla richiesta
	 */
	public void setRequestTime(int requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * @return duration durata della richiesta
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param  duration duration durata della richiesta da assegnare alla richiesta
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return state stato della richiesta
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state state stato della richiesta da assegnare alla richiesta
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return studentComment commento dello studente per una richiesta
	 */
	public String getStudentComment() {
		return studentComment;
	}

	/**
	 * @param studentComment studentComment commento dello studente per una richesta da assegnare alla richiesta
	 */
	public void setStudentComment(String studentComment) {
		this.studentComment = studentComment;
	}

	/**
	 * @return student studente che effettua la richiesta
	 */
	public String getStudent() {
		return student;
	}

	/**
	 * @param student student studente che effettua la richiesta da assegnare alla richiesta
	 */
	public void setStudent(String student) {
		this.student = student;
	}

	/**
	 * @return requestDate data della richiesta
	 */
	public Date getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate requestDate data della richiesta da assegnare alla richiesta
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}	
}