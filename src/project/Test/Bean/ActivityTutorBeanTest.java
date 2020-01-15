package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import project.Model.ActivityTutorBean;

class ActivityTutorBeanTest {
    
	@Test
	void testActivityTutorBeanEmpty() {
		ActivityTutorBean activity = new ActivityTutorBean();
		assertNotNull(activity);
		
	}

	@Test
	void testGetIdActivity() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(4,activity.getIdActivity());
	}

	@Test
	void testSetIdActivity() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setIdActivity(5);
	    assertEquals(5,activity.getIdActivity());
		
	}

	@Test
	void testGetStartTime() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(10,activity.getStartTime());
	}

	@Test
	void testSetStartTime() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setStartTime(11);
	    assertEquals(11,activity.getStartTime());
	}

	@Test
	void testGetFinishTime() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(20,activity.getFinishTime());
	}

	@Test
	void testSetFinishTime() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setFinishTime(21);
	    assertEquals(21,activity.getFinishTime());
	}

	@Test
	void testGetRegisterId() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(3,activity.getRegisterId());
	}

	@Test
	void testSetRegisterId() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setRegisterId(4);
	    assertEquals(4,activity.getRegisterId());
	}

	@Test
	void testGetCategory() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals("Sportello",activity.getCategory());
	}

	@Test
	void testSetCategory() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setCategory("Seminario");
	    assertEquals("Seminario",activity.getCategory());
	}

	@Test
	void testGetState() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals("In valutazione",activity.getState());
	}

	@Test
	void testSetState() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setState("Convalidata");
	    assertEquals("Convalidata",activity.getState());
	}

	@Test
	void testGetDetails() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals("abc",activity.getDetails());
	}

	@Test
	void testSetDetails() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setDetails("abcdef");
	    assertEquals("abcdef",activity.getDetails());
	}

	@Test
	void testGetTutor() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals("m.pisciotta@studenti.unicampania.it",activity.getTutor());
	}

	@Test
	void testSetTutor() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setTutor("m.lombardo@studenti.unicampania.it");
	    assertEquals("m.lombardo@studenti.unicampania.it",activity.getTutor());
	}

	@Test
	void testGetActivityDate() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(date,activity.getActivityDate());
	}

	@Test
	void testSetActivityDate() {
		String data="2020-01-16";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setActivityDate(date);
	    assertEquals(date,activity.getActivityDate());
	}

	@Test
	void testGetHours() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(5,activity.getHours());
	}

	@Test
	void testSetHours() {
		String data="2020-01-15";
		Date date = Date.valueOf(data);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setHours(6);
	    assertEquals(6,activity.getHours());
	}
}
