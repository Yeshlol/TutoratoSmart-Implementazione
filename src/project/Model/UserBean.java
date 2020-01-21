package project.Model;

import java.io.Serializable;

/**
 * Questa classe rappresenta l'entita' Utente.
 */
public class UserBean implements Serializable {
	private static final long serialVersionUID = 8664330832532346093L;
		
	protected String email, pwd, firstName, lastName, sex, telephoneNumber, registrationNumber;
	protected int role;
			
	public UserBean() {
		email = "";
		pwd = "";
		role = -1;
		firstName = "";
		lastName = "";
		sex = "";
		telephoneNumber = "";
		registrationNumber = "";
	}
	
	
	/**
	 * @param email email dell'utente
	 * @param pwd password dell'utente
	 * @param role ruolo dell'utente
	 * @param firstName nome dell'utente
	 * @param lastName cognome dell'utente
	 * @param sex sesso dell'utente
	 * @param telephoneNumber numero di telefono dell'utente
	 * @param registrationNumber matricola dell'utente
	 */
	public UserBean(String email, String pwd, int role, String firstName, String lastName, String telephoneNumber, String sex, String registrationNumber) {
		this.email=email;
		this.pwd=pwd;
		this.role=role;
		this.firstName=firstName;
		this.lastName=lastName;
		this.sex=sex;
		this.telephoneNumber=telephoneNumber;
		this.registrationNumber=registrationNumber;
	}
	
	/**
	 * @return email email dell'utente
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email email dell'utente da assegnare all'utente
	 * @return
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return pwd password dell'utente
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd pwd password dell'utente da assegnare all'utente
	 * @return
	 */
	public void setPwd(String password) {
		this.pwd = password;
	}
	
	/**
	 * @return role ruolo dell'utente
	 */
	public int getRole() {
		return role;
	}
	
	/**
	 * @param role role ruolo dell'utente da assegnare all'utente
	 * @return
	 */
	public void setRole(int role) {
		this.role = role;
	}
	
	/**
	 * @return firstName nome dell'utente
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName firstName nome dell'utente da assegnare all'utente
	 * @return
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return lastName cognome dell'utente
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName lastName cognome dell'utente da assegnare all'utente
	 * @return
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return sex sesso dell'utente
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex sex sesso dell'utente da assegnare all'utente
	 * @return
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return telephoneNumber numero di telefono dell'utente
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber telephoneNumber numero di telefono dell'utente da assegnare all'utente
	 * @return
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return registerNumber matricola dell'utente
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber registrationNumber matricola dell'utente da assegnare all'utente
	 * @return
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	@Override
	public String toString() {
		return firstName + " " + lastName + ", " + sex + ", Matricola: " + registrationNumber + 
				", E-mail: " + email + ", Tel: " + telephoneNumber + "\n";
	}
}