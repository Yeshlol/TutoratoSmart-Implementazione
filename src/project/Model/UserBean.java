package project.Model;

import java.io.Serializable;

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
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPwd() {
		return pwd;
	}

	public void setPwd(String password) {
		this.pwd = password;
	}
	
	
	public int getRole() {
		return role;
	}
	
	public void setRole(int role) {
		this.role = role;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}


	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	@Override
	public String toString() {
		return firstName + " " + lastName + ", " + sex + ", Matricola: " + registrationNumber + 
				", E-mail: " + email + ", Tel: " + telephoneNumber + "\n";
	}
}