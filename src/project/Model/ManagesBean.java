package project.Model;

import java.io.Serializable;

public class ManagesBean implements Serializable {
	private static final long serialVersionUID = 9006955833783450405L;

	private String tutor;
	private int requestId;
	
	public ManagesBean() {
		tutor = "";
		requestId = -1;
	}

	
	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	
	@Override
	public String toString() {
		return "Il tutor " + tutor + " ha gestito la richiesta n° " + requestId + ".\n";
	}
}
