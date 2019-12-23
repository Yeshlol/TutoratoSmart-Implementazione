package project.Model;

import java.io.Serializable;

public class RegisterBean implements Serializable {
	private static final long serialVersionUID = -479570144818932080L;
	
	private int idRegister, totalHours;
	private float percentageComplete, validatedHours;
	private String state;
	
	public RegisterBean() {
		idRegister = -1;
		totalHours = -1;
		percentageComplete = -1;
		validatedHours = -1;		
		state = "";
	}

	public int getIdRegister() {
		return idRegister;
	}

	public void setIdRegister(int idRegister) {
		this.idRegister = idRegister;
	}

	
	public float getValidatedHours() {
		return validatedHours;
	}

	public void setValidatedHours(float validatedHours) {
		this.validatedHours = validatedHours;
	}

	
	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	
	public float getPercentageComplete() {
		return percentageComplete;
	}

	public void setPercentageComplete(float percentageComplete) {
		this.percentageComplete = percentageComplete;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Registro n " + idRegister + ", Ore totali: " + totalHours + ", Ore convalidate: " 
				+ validatedHours + ", Completo al " + percentageComplete + "%, Stato: " + state + "\n";
	}
}