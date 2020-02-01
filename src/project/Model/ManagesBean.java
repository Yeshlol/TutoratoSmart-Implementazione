package project.Model;

import java.io.Serializable;



/**
 * Questa classe rappresenta l'associazione Gestione tra l'entita Richiesta e l'entita Tutor.
 */
public class ManagesBean implements Serializable {
	private static final long serialVersionUID = 9006955833783450405L;

	private String tutor;
	private int requestId;
	
	public ManagesBean() {
		tutor = "";
		requestId = -1;
	}
	
	/**
	 * @param tutor tutor che si occupa della gestione della richiesta
	 * @param requestId identificativo della richiesta da gestire
	 */
	public ManagesBean(String tutor,int requestId) {
		this.tutor=tutor;
		this.requestId=requestId;
		
	}

	/**
	 * @return tutor tutor che si occupa della richiesta
	 */
	public String getTutor() {
		return tutor;
	}

	/**
	 * @param tutor tutor che si occupa della richiesta da assegnare all'associazione Gestione
	 * @return
	 */
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	/**
	 * @return requestId identificativo della richiesta
	 */
	public int getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId requestId identificativo della richiesta da assegnare all'associazione Gestione
	 * @return
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
}
