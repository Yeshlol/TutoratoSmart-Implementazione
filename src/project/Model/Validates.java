package project.Model;

import java.io.Serializable;

public class Validates implements Serializable {
	private static final long serialVersionUID = -8762723026814198860L;
	
	private String commissionMember;
	private int activityId;
	
	public Validates() {
		commissionMember = "";
		activityId = -1;
	}

	
	public String getCommissionMember() {
		return commissionMember;
	}

	public void setCommissionMember(String commissionMember) {
		this.commissionMember = commissionMember;
	}

	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	
	@Override
	public String toString() {
		return "Il membro della Commissione di Tutorato (" + commissionMember + "), ha convalidato l'attivita' n " + activityId + ".\n";
	}
}