package project.Model;

import java.io.Serializable;

public class WorkDayBean implements Serializable {
	private static final long serialVersionUID = -5695700794138372876L;
	
	private int calendarId, startTime, finishTime, startTime2, finishTime2;
	private boolean isOpen;
	private String workDayName;
	
	public WorkDayBean() {
		calendarId = -1;
		startTime = -1;
		finishTime = -1;
		startTime2 = -1;
		finishTime2 = -1;
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
	
	
	public int getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(int startTime2) {
		this.startTime2 = startTime2;
	}

	
	public int getFinishTime2() {
		return finishTime2;
	}

	public void setFinishTime2(int finishTime2) {
		this.finishTime2 = finishTime2;
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
		String italianDay = null;
		
		switch (workDayName) {
		case("Monday"): italianDay = "Lunedì"; break;
		case("Tuesday"): italianDay = "Martedì"; break;
		case("Wednsday"): italianDay = "Mercoledì"; break;
		case("Thursday"): italianDay = "Giovedì"; break;
		case("Friday"): italianDay = "Venerdì"; break;
		case("Saturday"): italianDay = "Sabato";
		}
		
		return italianDay + ", orario inizio: " + 
			startTime/60 + ":" + startTime%60 + ", orario termine: " + finishTime/60 + ":" + finishTime%60 + 
			", " + isOpen + "\n";
	}
}
