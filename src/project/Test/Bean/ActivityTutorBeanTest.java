package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import project.Model.ActivityTutorBean;

class ActivityTutorBeanTest {
	
	private Date date = Date.valueOf("2020-01-15");
    
	@Test
	void testActivityTutorBeanEmpty() {
		ActivityTutorBean activity = new ActivityTutorBean();
		assertNotNull(activity);
		
	}
	
	@Test
	void testGetIdActivity() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(4,activity.getIdActivity());
	}

	@Test
	void testSetIdActivity() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setIdActivity(5);
	    assertEquals(5,activity.getIdActivity());
		
	}

	@Test
	void testGetStartTime() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(10,activity.getStartTime());
	}

	@Test
	void testSetStartTime() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setStartTime(11);
	    assertEquals(11,activity.getStartTime());
	}

	@Test
	void testGetFinishTime() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(20,activity.getFinishTime());
	}

	@Test
	void testSetFinishTime() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setFinishTime(21);
	    assertEquals(21,activity.getFinishTime());
	}

	@Test
	void testGetRegisterId() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(3,activity.getRegisterId());
	}

	@Test
	void testSetRegisterId() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setRegisterId(4);
	    assertEquals(4,activity.getRegisterId());
	}

	@Test
	void testGetCategory() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals("Sportello",activity.getCategory());
	}

	@Test
	void testSetCategory() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setCategory("Seminario");
	    assertEquals("Seminario",activity.getCategory());
	}

	@Test
	void testGetState() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals("In valutazione",activity.getState());
	}

	@Test
	void testSetState() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setState("Convalidata");
	    assertEquals("Convalidata",activity.getState());
	}

	@Test
	void testGetDetails() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals("abc",activity.getDetails());
	}

	@Test
	void testSetDetails() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setDetails("abcdef");
	    assertEquals("abcdef",activity.getDetails());
	}

	@Test
	void testGetTutor() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals("m.pisciotta@studenti.unicampania.it",activity.getTutor());
	}

	@Test
	void testSetTutor() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setTutor("m.lombardo@studenti.unicampania.it");
	    assertEquals("m.lombardo@studenti.unicampania.it",activity.getTutor());
	}

	@Test
	void testGetActivityDate() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(date,activity.getActivityDate());
	}

	@Test
	void testSetActivityDate() {
		String data1="2020-01-16";
		Date date1 = Date.valueOf(data1);
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setActivityDate(date1);
	    assertEquals(date1,activity.getActivityDate());
	}

	@Test
	void testGetHours() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
		assertEquals(5,activity.getHours());
	}

	@Test
	void testSetHours() {
		ActivityTutorBean activity = new ActivityTutorBean(4,10,20,3,"Sportello","In valutazione","abc","m.pisciotta@studenti.unicampania.it",date,5);
	    activity.setHours(6);
	    assertEquals(6,activity.getHours());
	}
}
