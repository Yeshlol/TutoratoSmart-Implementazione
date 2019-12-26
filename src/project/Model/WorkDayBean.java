package project.Model;

import java.io.Serializable;

public class WorkDayBean implements Serializable {
	private static final long serialVersionUID = -5695700794138372876L;
	
	private int calendarId, startTime, finishTime;
	private boolean isOpen;
	private String workDayName;
	
	public WorkDayBean() {
		calendarId = -1;
		startTime = -1;
		finishTime = -1;
		isOpen = false;
		workDayName = "";
	}


	public int getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(int calendarId) {
		this.calendarId = calendarId;
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
	
	
	@Override
	public String toString() {
		return "Calendario numero " + calendarId + ", " + workDayName + ", orario inizio: " + 
			startTime/60 + ":" + startTime%60 + ", orario termine: " + finishTime/60 + ":" + finishTime%60 + 
			", " + isOpen + "\n";
	}
}
