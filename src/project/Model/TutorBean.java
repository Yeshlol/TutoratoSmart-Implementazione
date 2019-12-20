package project.Model;

import java.io.Serializable;
import java.sql.Date;

public class TutorBean extends UserBean implements Serializable {
	private static final long serialVersionUID = 4408007616136291300L;
	
	private Date startDate;
	private String commissionMember;
	private int registerId;
	
	public TutorBean() {
		super();
		startDate = new Date(-3000000);
		registerId = -1;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
		return firstName + " " + lastName + ", " + sex + ", Tutor, Matricola: " + registrationNumber + 
				", E-mail: " + email + ", Tel: " + telephoneNumber + "\n";
	}
}