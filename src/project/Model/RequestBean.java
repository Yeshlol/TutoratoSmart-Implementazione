package project.Model;

import java.io.Serializable;
import java.sql.Date;

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

	public int getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}

	
	public int getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(int requestTime) {
		this.requestTime = requestTime;
	}

	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	public String getStudentComment() {
		return studentComment;
	}

	public void setStudentComment(String studentComment) {
		this.studentComment = studentComment;
	}

	
	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	
	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	
	@Override
	public String toString() {
		return "Richiesta n " + idRequest + ", " + state +", per il giorno " + requestDate + ", ore: " + requestTime/60 + ":" + requestTime%60 + 
				", durata stimata: " + duration + ", studente: " + student + ", commento: " + studentComment + "\n";
	}
}