package project.Model;

import java.io.Serializable;
import java.sql.Date;

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
	
	public TutorBean(String email, String pwd, int role, String firstName, String lastName, String telephoneNumber, String sex, String registrationNumber, Date startDate, Date finishDate, String commissionMember, String state, int registerId) {
		super(email, pwd, role, firstName, lastName, telephoneNumber, sex, registrationNumber);
		this.startDate=startDate;
		this.finishDate=finishDate;
		this.commissionMember=commissionMember;
		this.state=state;
		this.registerId=registerId;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	
	public String getCommissionMember() {
		return commissionMember;
	}

	public void setCommissionMember(String commissionMember) {
		this.commissionMember = commissionMember;
	}

	
	public int getRegisterId() {
		return registerId;
	}

	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}
	
	
	@Override
	public String toString() {
		return firstName + " " + lastName + ", " + sex + ", Tutor " + state + ", Matricola: " + registrationNumber + 
				", E-mail: " + email + ", Tel: " + telephoneNumber + "Attivo dal " + startDate + " al " + finishDate + ".\n";
	}
}