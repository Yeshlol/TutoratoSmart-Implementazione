package project.Test.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Control.DBConnection;
import project.Model.TutorBean;
import project.Model.TutorDAO;
import project.Test.DatabaseHelper;

class TutorDAOTest {
	TutorDAO tutorDAO = new TutorDAO();

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
	void testDoRetrieveByMail() throws SQLException {
		TutorBean tutor = tutorDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		assertNotNull(tutor);
	}
 
	@Test
	void testDoSave() throws SQLException {
		String startDate="2019-10-24";
		Date startData=Date.valueOf(startDate);
		String finishDate="2019-10-29";
		Date finishData=Date.valueOf(finishDate);
		TutorBean user = new TutorBean("m.dellecave@studenti.unicampania.it", "M12345678", 2, "Marco", "Delle Cave", "3334568768", "M", "B512102121", startData , finishData , "d.molinaro@commissione.unicampania.it" , "Attivo", 4);
		ArrayList<TutorBean> tutorlist = tutorDAO.doRetrieveAll();
		assertEquals(3, tutorlist.size());
		tutorDAO.doSave(user,15);
		tutorlist = tutorDAO.doRetrieveAll();
		assertEquals(4,tutorlist.size());
	}

	@Test
	void testDoRetrieveAllByDates() throws SQLException {
		String startResearchDate="2019-01-01";
		Date startDate=Date.valueOf(startResearchDate);
		String finishResearchDate="2019-12-31";
		Date finishDate=Date.valueOf(finishResearchDate);
		Collection<TutorBean> tutorList = (Collection<TutorBean>) tutorDAO.doRetrieveAllByDates(null , startDate , finishDate);
		assertNotNull(tutorList);
	}

	@Test
	void testDoRetrieveAllActive() throws SQLException {
		LinkedList<TutorBean> tutorList= (LinkedList<TutorBean>) tutorDAO.doRetrieveAllActive(null);
		assertNotNull(tutorList);
	}
}