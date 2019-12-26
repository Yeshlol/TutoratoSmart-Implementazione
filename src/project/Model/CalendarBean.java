package project.Model;

import java.io.Serializable;

public class CalendarBean implements Serializable {
	private static final long serialVersionUID = 4869184025551314845L;
	
	private int idCalendar;
	private String commissionMember;
	
	public CalendarBean() {
		idCalendar = -1;
		commissionMember = "";
	}

	public int getIdCalendar() {
		return idCalendar;
	}

	public void setIdCalendar(int idCalendar) {
		this.idCalendar = idCalendar;
	}

	public String getCommissionMember() {
		return commissionMember;
	}

	public void setCommissionMember(String commissionMember) {
		this.commissionMember = commissionMember;
	}
	
	
	@Override
	public String toString() {
		return "Calendario numero " + idCalendar + " specificato dal membro della Commissione: " + commissionMember + ".\n";
	}	
}
