package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Model.ManagesBean;

class ManagesBeanTest {

	@Test
	void testManagesBeanEmpty() {
		ManagesBean manages = new ManagesBean();
		assertNotNull(manages);
	}

	@Test
	void testGetTutor() {
		ManagesBean manages = new ManagesBean("m.pisciotta@studenti.unicampania.it",1);
		assertEquals("m.pisciotta@studenti.unicampania.it",manages.getTutor());
	}

	@Test
	void testSetTutor() {
		ManagesBean manages = new ManagesBean("m.pisciotta@studenti.unicampania.it",1);
		manages.setTutor("c.ferrari@studenti.unicampania.it");
		assertEquals("c.ferrari@studenti.unicampania.it",manages.getTutor());
	}

	@Test
	void testGetRequestId() {
		ManagesBean manages = new ManagesBean("m.pisciotta@studenti.unicampania.it",1);
		assertEquals(1,manages.getRequestId());
	}

	@Test
	void testSetRequestId() {
		ManagesBean manages = new ManagesBean("m.pisciotta@studenti.unicampania.it",1);
		manages.setRequestId(2);
		assertEquals(2,manages.getRequestId());
	}
}
