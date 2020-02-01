package project.Model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Questa classe rappresenta l'entita' Tutor.
 */
public class TutorBean extends UserBean implements Serializable {
	private static final long serialVersionUID = 4408007616136291300L;
	
	private Date startDate, finishDate;
	private String commissionMember, state;
	private int registerId;
	
	public TutorBean() {
		super();
		startDate = new Date(-3000000);
		finishDate = new Date(-3000000);
		commissionMember = "";
		state = "";
		registerId = -1;
	}
	
	/**
	 * @param email email del tutor
	 * @param pwd password del tutor
	 * @param role ruolo del tutor
	 * @param firstName nome del tutor
	 * @param lastName cognome del tutor
	 * @param sex sesso del tutor
	 * @param telephoneNumber numero di telefono del tutor
	 * @param registrationNumber matricola del tutor
	 * @param startDate data di inizio del tutor
	 * @param finishDate data di fine del tutor
	 * @param commissionMember membro della commissione associato al tutor
	 * @param state stato del tutor
	 * @param registerId identificati del registro 
	 */
	public TutorBean(String email, String pwd, int role, String firstName, String lastName, String telephoneNumber, String sex, String registrationNumber, Date startDate, Date finishDate, String commissionMember, String state, int registerId) {
		super(email, pwd, role, firstName, lastName, telephoneNumber, sex, registrationNumber);
		this.startDate=startDate;
		this.finishDate=finishDate;
		this.commissionMember=commissionMember;
		this.state=state;
		this.registerId=registerId;
	}

	/**
	 * @return state stato del tutor
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state state stato del tutor da assegnare al tutor
	 * @return
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return startDate data di inizio del tutor
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate startDate data di inizio del tutor da assegnare al tutor
	 * @return
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return finishDate data di fine del tutor
	 */
	public Date getFinishDate() {
		return finishDate;
	}

	/**
	 * @param finishDate finishDate data di fine del tutor da assegnare al tutor
	 * @return
	 */
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	/**
	 * @return commissionMember membro della commissione associato al tutor
	 */
	public String getCommissionMember() {
		return commissionMember;
	}

	/**
	 * @param commissionMember commissionMember membro della commissione associato al tutor da assegnare al tutor
	 * @return
	 */
	public void setCommissionMember(String commissionMember) {
		this.commissionMember = commissionMember;
	}

	/**
	 * @return registerId identificativo del registro
	 */
	public int getRegisterId() {
		return registerId;
	}

	/**
	 * @param registerId registerId identificativo del registro da assegnare al tutor
	 * @return
	 */
	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}
}