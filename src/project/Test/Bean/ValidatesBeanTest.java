package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Model.*;

class ValidatesBeanTest {



	@Test
	void testValidatesBeanEmpty() {
		ValidatesBean validate = new ValidatesBean();
		assertNotNull(validate);
		
	}

	@Test
	void testGetCommissionMember() {
		ValidatesBean validate = new ValidatesBean("d.molinaro@commissione.unicampania.it", 1);
		assertEquals("d.molinaro@commissione.unicampania.it",validate.getCommissionMember());
	}

	@Test
	void testSetCommissionMember() {
		ValidatesBean validate = new ValidatesBean("d.molinari@commissione.unicampania.it", 1);
		validate.setCommissionMember("g.paoli@commissione.unicampania.it");
		assertEquals("g.paoli@commissione.unicampania.it",validate.getCommissionMember());
	}

	@Test
	void testGetActivityId() {
		ValidatesBean validate = new ValidatesBean("d.molinaro@commissione.unicampania.it", 1);
		assertEquals(1,validate.getActivityId());
	}

	@Test
	void testSetActivityId() {
		ValidatesBean validate = new ValidatesBean("d.molinari@commissione.unicampania.it", 1);
		validate.setActivityId(2);
		assertEquals(2,validate.getActivityId());
	}

}
