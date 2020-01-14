package project.Model;

import java.io.Serializable;

/**
 * Questa classe rappresenta l'associazione Valida tra l'entita Attività e l'entita Utente.
 * @author 
 */
public class ValidatesBean implements Serializable {
	private static final long serialVersionUID = -8762723026814198860L;
	
	private String commissionMember;
	private int activityId;
	
	public ValidatesBean() {
		commissionMember = "";
		activityId = -1;
	}
	
	
	/**
	 * @param commissionMember membro della commissione
	 * @param activityId identificativo dell'attività
	 */
	public ValidatesBean(String commissionMember, int activityId) {
		this.commissionMember=commissionMember;
		this.activityId=activityId;
	}

	
	/**
	 * @return commissionMember membro della commissione
	 */
	public String getCommissionMember() {
		return commissionMember;
	}

	/**
	 * @param commissionMember commissionMember membro della commissione da assegnare all'associazione Valida
	 * @return
	 */
	public void setCommissionMember(String commissionMember) {
		this.commissionMember = commissionMember;
	}

	/**
	 * @return activityId identificativo dell'attività
	 */
	public int getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId activityId identificativo dell'attività da assegnare all'associazione Valida
	 * @return
	 */
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	@Override
	public String toString() {
		return "Il membro della Commissione di Tutorato (" + commissionMember + "), ha convalidato l'attivita' n " + activityId + ".\n";
	}
}