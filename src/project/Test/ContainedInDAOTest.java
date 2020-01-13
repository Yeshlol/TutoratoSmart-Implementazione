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
import project.Model.ActivityTutorBean;
import project.Model.ActivityTutorDAO;
import project.Model.AppointmentBean;
import project.Model.AppointmentDAO;
import project.Model.ContainedInBean;
import project.Model.ContainedInDAO;
import project.Model.RegisterDAO;
import project.Model.RequestBean;
import project.Model.RequestDAO;

class ContainedInDAOTest {
	ContainedInDAO containedDAO = new ContainedInDAO();

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
	void testDoDeleteByActivityId() throws SQLException {
		ArrayList<ContainedInBean> containedInList = containedDAO.doRetrieveAll();
		assertEquals(3,containedInList.size());
		containedDAO.doDeleteByActivityId(1);
		containedInList=containedDAO.doRetrieveAll();
		assertEquals(2,containedInList.size());
	}

	@Test
	void testDoRetrieveByActivityId() throws SQLException {
		 Collection<AppointmentBean> list = containedDAO.doRetrieveByActivityId(1);
		 assertNotNull(list);
	}

	@Test
	void testDoRetrieveByAppointmentId() throws SQLException {
		ContainedInBean containedInBean;
		containedInBean = containedDAO.doRetrieveByAppointmentId(3);
		assertNotNull(containedInBean);
	}

	
	@Test
	void testDoSave() throws SQLException {
		String activityDate1 = "2019-11-27";
		Date date1 = Date.valueOf(activityDate1);
		RequestDAO requestDAO = new RequestDAO();
		RequestBean requestBean = new RequestBean(4,720,60,"Accettata","abacavbab","g.luongo@studenti.unisa.it",date1);
		requestDAO.doSave(requestBean);
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		AppointmentBean appointmentBean = new AppointmentBean(4,"Supporto prenotazione appello",4,"m.pisciotta@studenti.unicampania.it");
		appointmentDAO.doSave(appointmentBean);
		ActivityTutorDAO activityTutorDAO = new ActivityTutorDAO();
		String activityDate = "2019-11-27";
		Date date = Date.valueOf(activityDate);
		RegisterDAO registerDAO = new RegisterDAO();
		int id=registerDAO.doSave(10);
		ActivityTutorBean activityTutorBean = new ActivityTutorBean(4,720,780,1,"Sportello Tutorato","Convalidata","L'attivit� riguarda il supporto per la prenotazione dell'apello","m.pisciotta@studenti.unicampania.it",date, 1);
		activityTutorDAO.doSave(activityTutorBean);
		ContainedInBean bean = new ContainedInBean(4,4);
		ArrayList<ContainedInBean> list = containedDAO.doRetrieveAll();
		assertEquals(3,list.size());
		containedDAO.doSave(bean);
		list = containedDAO.doRetrieveAll();
		assertEquals(4,list.size());
			
	}
	

	@Test
	void testDoDelete() throws SQLException {
		ContainedInBean bean = new ContainedInBean(3,3);
		boolean value;
		value = containedDAO.doDelete(bean);
		assertTrue(value);
	}
}
