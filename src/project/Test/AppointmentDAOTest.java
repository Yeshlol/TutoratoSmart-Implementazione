package project.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Control.DBConnection;
import project.Model.AppointmentBean;
import project.Model.AppointmentDAO;



class AppointmentDAOTest {
	AppointmentDAO appointmentDAO = new AppointmentDAO();

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
	void testDoRetrieveById() throws SQLException {
		AppointmentBean appointment = appointmentDAO.doRetrieveById(1);
		assertNotNull(appointment);
	}

	@Test
	void testDoSave() throws SQLException {
		AppointmentBean appointment = new AppointmentBean(4,"Supporto registrazione sito universit�",4,"m.pisciotta@studenti.unicampania.it");
		ArrayList<AppointmentBean> tutorlist = appointmentDAO.doRetrieveAll();
		assertEquals(3, tutorlist.size());
		appointmentDAO.doSave(appointment);
		tutorlist = appointmentDAO.doRetrieveAll();
		assertEquals(4,tutorlist.size());
	}

	@Test
	void testDoModify() throws SQLException {
		AppointmentBean appointment1= appointmentDAO.doRetrieveById(3);
		AppointmentBean appointment = new AppointmentBean(3,"Supporto prenotazione esame",3,"m.lombardo@studenti.unicampania.it");
		appointmentDAO.doModify(appointment);
		assertNotEquals(appointment1,appointment);
	}

	@Test
	void testDoDelete() throws SQLException {
		AppointmentBean appointment = appointmentDAO.doRetrieveById(1);
		boolean value;
		value=appointmentDAO.doDelete(appointment);
		assertTrue(value);	
		    
	}

	@Test
	void testDoRetrieveByRequestId() throws SQLException {
		AppointmentBean appointment = appointmentDAO.doRetrieveByRequestId(2);
		assertNotNull(appointment);

	}

	@Test
	void testDoRetrieveAllByDate() throws SQLException {
		String startDate="2019-10-26";
		Date data=Date.valueOf(startDate);
		Collection<AppointmentBean> appointmentCollection = appointmentDAO.doRetrieveAllByDate(null, "m.lombardo@studenti.unicampania.it", data , 800, 900);
		assertNotNull(appointmentCollection);
	}

	@Test
	void testDoRetrieveAllByTutor() throws SQLException {
		Collection<AppointmentBean> appointmentCollection = appointmentDAO.doRetrieveAllByTutor("c.ferrari@studenti.unicampania.it");
		assertNotNull(appointmentCollection);
		
	}

}
