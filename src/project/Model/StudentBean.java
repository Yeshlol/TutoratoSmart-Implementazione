package project.Model;

import java.io.Serializable;

public class StudentBean extends UserBean implements Serializable {
	private static final long serialVersionUID = -5444780590893227019L;
	
	private String email;
	private int academicYear;
	
	public StudentBean() {
		super();
		academicYear = -1;
	}
	
	public StudentBean(String email, String pwd, int role, String firstName, String lastName, String telephoneNumber, String sex, String registrationNumber, int academicYear) {
		super(email, pwd, role, firstName, lastName, telephoneNumber, sex, registrationNumber);
		this.academicYear=academicYear;
	}


	public int getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + ", " + sex + ", Studente, Matricola: " + registrationNumber + ", " +
				academicYear + " Anno Accademico, E-mail: " + email + ", Tel: " + telephoneNumber + "\n";
	}
}
