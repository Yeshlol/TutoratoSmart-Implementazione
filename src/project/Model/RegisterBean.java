package project.Model;

import java.io.Serializable;

/**
 * Questa classe rappresenta l'entità Registro.
 * @author 
 */
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
	
	
	/**
	 * @param idRegister identificativo del registro
	 * @param state stato del registro
	 * @param validateHours ore validate del registro
	 * @param percentageComplete percentuale di completamento del registro
	 */
	public RegisterBean(int idRegister,String state, float validateHours,float percentageComplete) {
		this.idRegister=idRegister;
		this.totalHours=totalHours;
		this.percentageComplete=percentageComplete;
		this.validatedHours=validateHours;
		this.state=state;
	}

	/**
	 * @return idRegister identificativo del registro
	 */
	public int getIdRegister() {
		return idRegister;
	}

	/**
	 * @param idRegister idRegister identificativo del registro da assegnare al registro
	 * @return
	 */
	public void setIdRegister(int idRegister) {
		this.idRegister = idRegister;
	}

	/**
	 * @return validateHours ore validate del registro 
	 */
	public float getValidatedHours() {
		return validatedHours;
	}

	/**
	 * @param validateHours validateHours ore del registro da assegnare al registro
	 * @return
	 */
	public void setValidatedHours(float validatedHours) {
		this.validatedHours = validatedHours;
	}

	/**
	 * @return totalHours ore totali del registro
	 */
	public int getTotalHours() {
		return totalHours;
	}

	/**
	 * @param totalHours totalHours ore totali del registro da assegnare al registro
	 * @return
	 */
	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	/**
	 * @return percentageComplete percentuale di completamento del registro
	 */
	public float getPercentageComplete() {
		return percentageComplete;
	}

	/**
	 * @param percentageComplete percentuale di completamento del registro da assegnare al registro
	 * @return
	 */
	public void setPercentageComplete(float percentageComplete) {
		this.percentageComplete = percentageComplete;
	}

	/**
	 * @return state stato del registro
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state state del registro da assegnare al registro
	 * @return
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Registro n " + idRegister + ", Ore totali: " + totalHours + ", Ore convalidate: " 
				+ validatedHours + ", Completo al " + percentageComplete + "%, Stato: " + state + "\n";
	}
}