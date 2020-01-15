package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import project.Model.*;

class UserBeanTest {



	@Test
	void testUserBeanEmpty() {
		UserBean user = new UserBean();
		assertNotNull(user);
	}

	@Test
	void testGetEmail() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		assertEquals("e.merola@studenti.unicampania.it", user.getEmail());
	
	}

	@Test
	void testSetEmail() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		user.setEmail("p.pallino@commissione.unicampania.it");
		assertEquals("p.pallino@commissione.unicampania.it",user.getEmail());
	}

	@Test
	void testGetPwd() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		assertEquals("M12345678", user.getPwd());
	}

	@Test
	void testSetPwd() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		user.setPwd("E12345678");
		assertEquals("E12345678",user.getPwd());
	}

	@Test
	void testGetRole() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		assertEquals(3, user.getRole());
	}

	@Test
	void testSetRole() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		user.setRole(2);
		assertEquals(2,user.getRole());
	}

	@Test
	void testGetFirstName() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		assertEquals("Eduardo", user.getFirstName());
	}

	@Test
	void testSetFirstName() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		user.setFirstName("Giovanni");
		assertEquals("Giovanni",user.getFirstName());
	}

	@Test
	void testGetLastName() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		assertEquals("Merola", user.getLastName());
	}

	@Test
	void testSetLastName() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		user.setLastName("Gentile");
		assertEquals("Gentile",user.getLastName());
	}

	@Test
	void testGetSex() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		assertEquals("M", user.getSex());
	}

	@Test
	void testSetSex() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		user.setSex("F");
		assertEquals("F",user.getSex());
	}

	@Test
	void testGetTelephoneNumber() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		assertEquals("3343355637", user.getTelephoneNumber());
	}

	@Test
	void testSetTelephoneNumber() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		user.setTelephoneNumber("3343355638");
		assertEquals("3343355638",user.getTelephoneNumber());
	}

	@Test
	void testGetRegistrationNumber() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		assertEquals("A512102497", user.getRegistrationNumber());
	}

	@Test
	void testSetRegistrationNumber() {
		UserBean user = new UserBean("e.merola@studenti.unicampania.it", "M12345678", 3, "Eduardo", "Merola", "3343355637", "M", "A512102497");
		user.setRegistrationNumber("A512102498");
		assertEquals("A512102498",user.getRegistrationNumber());
	}

}
