package project.Model;

import java.io.Serializable;

/**
 * Questa classe rappresenta l'entita' Studente.
 */
public class StudentBean extends UserBean implements Serializable {
	private static final long serialVersionUID = -5444780590893227019L;
	
	private String email;
	private int academicYear;
	
	public StudentBean() {
		super();
		academicYear = -1;
	}
	
	/**
	 * @param email email dello studente
	 * @param pwd password dello studente
	 * @param role ruolo dello studente
	 * @param firstName nome dello studente
	 * @param lastName cognome dello studente
	 * @param sex sesso dello studente
	 * @param telephoneNumber numero di telefono dello studente
	 * @param registrationNumber matricola dello studente
	 * @param academicYear anno accademico dello studente
	 */
	public StudentBean(String email, String pwd, int role, String firstName, String lastName, String telephoneNumber, String sex, String registrationNumber, int academicYear) {
		super(email, pwd, role, firstName, lastName, telephoneNumber, sex, registrationNumber);
		this.academicYear=academicYear;
	}

	/**
	 * @return academicYear anno accademico dello studente
	 */
	public int getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear academicYear anno accademico dello studente da assegnare allo studente
	 * @return
	 */
	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + ", " + sex + ", Studente, Matricola: " + registrationNumber + ", " +
				academicYear + " Anno Accademico, E-mail: " + email + ", Tel: " + telephoneNumber + "\n";
	}
}