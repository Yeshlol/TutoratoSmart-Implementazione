package project.Model;

import java.io.Serializable;

public class CommissionMemberBean extends UserBean implements Serializable {
	private static final long serialVersionUID = -528041991688582912L;

	public CommissionMemberBean() {
		super();
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + ", " + sex + ", Membro della Commissione di Tutorato, E-mail: " 
				+ email + ", Tel: " + telephoneNumber + "\n";
	}
}
