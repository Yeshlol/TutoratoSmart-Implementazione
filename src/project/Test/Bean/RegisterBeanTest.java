package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Model.RegisterBean;

class RegisterBeanTest {

	@Test
	void testRegisterBeanEmpty() {
		RegisterBean register = new RegisterBean();
		assertNotNull(register);
	}

	@Test
	void testGetIdRegister() {
		RegisterBean register = new RegisterBean(1,"Non approvato",6,60);
		assertEquals(1,register.getIdRegister());
	}

	@Test
	void testSetIdRegister() {
		RegisterBean register = new RegisterBean(1,"Non approvato",6,60);
		register.setIdRegister(2);
		assertEquals(2,register.getIdRegister());
	}

	@Test
	void testGetValidatedHours() {
		RegisterBean register = new RegisterBean(1,"Non approvato",6,60);
		assertEquals(6,register.getValidatedHours());
	}

	@Test
	void testSetValidatedHours() {
		RegisterBean register = new RegisterBean(1,"Non approvato",6,60);
		register.setValidatedHours(9);
		assertEquals(9,register.getValidatedHours());
	}

	@Test
	void testGetPercentageComplete() {
		RegisterBean register = new RegisterBean(1,"Non approvato",6,60);
		assertEquals(60,register.getPercentageComplete());
	}

	@Test
	void testSetPercentageComplete() {
		RegisterBean register = new RegisterBean(1,"Non approvato",6,60);
		register.setPercentageComplete(70);
		assertEquals(70,register.getPercentageComplete());
	}

	@Test
	void testGetState() {
		RegisterBean register = new RegisterBean(1,"Non approvato",6,60);
		assertEquals("Non approvato",register.getState());
	}

	@Test
	void testSetState() {
		RegisterBean register = new RegisterBean(1,"Non approvato",6,60);
		register.setState("Approvato");
		assertEquals("Approvato",register.getState());
	}
}
