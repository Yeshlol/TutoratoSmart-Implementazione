package project.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Control.DBConnection;
import project.Model.*;

class ActivityTutorDAOTest {
	ActivityTutorDAO activityTutorDAO = new ActivityTutorDAO();

	@BeforeEach
	void setUp() throws Exception {
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}

	@Test
	void testDoRetrieveById() throws SQLException{
		ActivityTutorBean activityTutor = activityTutorDAO.doRetrieveById(1);
		assertNotNull(activityTutor);
	}

	@Test
	void testAnyActivityRegistered() throws SQLException{
		String activityData="2019-11-24";
		Date activityD = Date.valueOf(activityData);
		boolean value;
		
		value = activityTutorDAO.anyActivityRegistered("m.pisciotta@studenti.unicampania.it", activityD, 540, 900);
				assertTrue(value);
	}

	
	/* DA CONTROLLARE
	@Test
	void testDoSave() throws SQLException{
		String activityDate="2019-11-27";
		Date activityData=Date.valueOf(activityDate);
		ActivityTutorBean activityTutor = new ActivityTutorBean(4, 540, 900, 4, "Sportello Tutorato", "In valutazione", "DettagliTestActivity", "c.ferrari@studenti.unicampania.it", activityData, 6);
		ArrayList<ActivityTutorBean> activitytutorlist = activityTutorDAO.doRetrieveAll();
		assertEquals(3, activitytutorlist.size());		
		activityTutorDAO.doSave(activityTutor);
		activitytutorlist = activityTutorDAO.doRetrieveAll();
		assertEquals(4,activitytutorlist.size());
	} */

	@Test
	void testDoModify() throws SQLException {
		String activityData="2019-11-24";
		Date activityD = Date.valueOf(activityData);
		
		ActivityTutorBean activity1 = activityTutorDAO.doRetrieveById(2);
		ActivityTutorBean activity = new ActivityTutorBean(2, 540, 900, 1, "Sportello Tutorato", "In valutazione", "ModificaAttivit�Test", "m.pisciotta@studenti.unicampania.it", activityD, 6);
		activityTutorDAO.doModify(activity);
		assertNotEquals(activity1, activity);
	}

	@Test
	void testDoDelete() throws SQLException{
		String activityData="2019-11-24";
		Date activityD = Date.valueOf(activityData);
		ActivityTutorBean bean = new ActivityTutorBean(1, 540, 900, 1, "Sportello Tutorato", "In valutazione", "L' attività riguarda informazioni relative all'immatricolazione dello studente", "m.pisciotta@studenti.unicampania.it", activityD, 6);
		boolean value;
		value=activityTutorDAO.doDelete(bean);
		assertTrue(value);
	}

	@Test
	void testDoRetrieveAllByMail() throws SQLException{
		Collection<ActivityTutorBean> activityTutor = activityTutorDAO.doRetrieveAllByMail(null, "c.ferrari@studenti.unicampania.it");
		assertNotNull(activityTutor);
	}

	@Test
	void testDifferentActivityRegistered() throws SQLException{	
		String activityData="2019-11-24";
		Date activityD = Date.valueOf(activityData);
		boolean value;
		
		value = activityTutorDAO.differentActivityRegistered("m.pisciotta@studenti.unicampania.it", 1, activityD, 540, 900);
				assertFalse(value);	
	}
}
