package project.Model;

import java.io.Serializable;

public class WorkDayBean implements Serializable {
	private static final long serialVersionUID = -5695700794138372876L;
	
	private int idWorkDay, startTime, finishTime;
	private boolean isOpen;
	private String workDayName, commissionMember;
	
	public WorkDayBean() {
		idWorkDay = -1;
		startTime = -1;
		finishTime = -1;
		isOpen = false;
		workDayName = "";
		commissionMember = "";
	}

	public int getIdWorkDay() {
		return idWorkDay;
	}

	public void setIdWorkDay(int idWorkDay) {
		this.idWorkDay = idWorkDay;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getWorkDayName() {
		return workDayName;
	}

	public void setWorkDayName(String workDayName) {
		this.workDayName = workDayName;
	}

	public String getCommissionMember() {
		return commissionMember;
	}

	public void setCommissionMember(String commissionMember) {
		this.commissionMember = commissionMember;
	}
	
	
	@Override
	public String toString() {
		return "Giorno lavorativo n° " + idWorkDay + ", " + workDayName + ", orario inizio: " + 
			startTime/60 + ":" + startTime%60 + ", orario termine: " + finishTime/60 + ":" + finishTime%60 + 
			", " + isOpen + "\n";
	}
}