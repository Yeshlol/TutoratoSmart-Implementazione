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

class RequestDAOTest {
	RequestDAO requestDAO = new RequestDAO();

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
		RequestBean request = requestDAO.doRetrieveById(1);
		assertNotNull(request);
	}

	@Test
	void testDoSave() throws SQLException {
		String requestData="2019-11-24";
		Date requestD = Date.valueOf(requestData);
		RequestBean request = new RequestBean(6, 600, 20, "In valutazione", "Supporto prenotazione esame", "e.merola@studenti.unicampania.it", requestD);
		Collection<RequestBean> requestlist = requestDAO.doRetrieveAll();
		assertEquals(5, requestlist.size());		
		requestDAO.doSave(request);
		requestlist = requestDAO.doRetrieveAll();
		assertEquals(6,requestlist.size());
	}

	@Test
	void testDoModify() throws SQLException{
		String requestData="2019-11-24";
		Date requestD = Date.valueOf(requestData);
		
		RequestBean request1 = requestDAO.doRetrieveById(2);
		RequestBean request = new RequestBean(3, 840, 30, "Accettata", "Non riesco a prenotarmi per la prova intercorso [PROVA TEST]", "g.luongo@studenti.unicampania.it", requestD);
		requestDAO.doModify(request);
		assertNotEquals(request1, request);
	}

	@Test
	void testDoAccept() throws SQLException{
		String requestData="2019-11-26";
		Date requestD = Date.valueOf(requestData);
		   RequestBean bean = new RequestBean(4, 600, 20, "In valutazione", "Supporto prenotazione esame", "e.merola@studenti.unicampania.it", requestD);
				   requestDAO.doAccept(bean, "m.pisciotta@studenti.unicampania.it");
				assertNotNull(bean);
	}

	@Test
	void testDoDelete() throws SQLException{
		String requestData="2019-11-26";
		Date requestD = Date.valueOf(requestData);
		RequestBean bean = new RequestBean(5, 600, 20, "In valutazione", "Supporto prenotazione esame", "e.merola@studenti.unicampania.it", requestD);
		boolean value;
		value=requestDAO.doDelete(bean);
		assertTrue(value);
	}

	@Test
	void testDoRetrieveAllByMail() throws SQLException {
		Collection<RequestBean> request = requestDAO.doRetrieveAllByMail(null, "a.tommasino@studenti.unicampania.it");
		assertNotNull(request);
	}

	@Test
	void testDoRetrieveAllByDates() throws SQLException {
		String startResearchDate="2019-01-01";
		Date startDate=Date.valueOf(startResearchDate);
		String finishResearchDate="2019-12-31";
		Date finishDate=Date.valueOf(finishResearchDate);
		Collection<RequestBean> requestList = (Collection<RequestBean>) requestDAO.doRetrieveAllByDates(null , "a.tommasino@studenti.unicampania.it", startDate , finishDate);
		assertNotNull(requestList);
	}

	@Test
	void testDoRetrieveAllPending() throws SQLException{
		Collection<RequestBean> request = requestDAO.doRetrieveAllPending(null);
		assertNotNull(request);
	}

	@Test
	void testIsAvailable() throws SQLException {
		String requestData="2019-11-25";
		Date requestD = Date.valueOf(requestData);
		boolean value;
		
		value = requestDAO.isAvailable(requestD, 660);
				assertFalse(value);
	}
	
	@Test
	void testDifferentRequestRegistered() throws SQLException {
		String requestData="2019-11-25";
		Date requestD = Date.valueOf(requestData);
		boolean value;
		
		value = requestDAO.differentRequestRegistered(requestD, 660, 2);
				assertTrue(value);
	}
	
	

	@Test
	void testConfirmAppointment() throws SQLException{
		String requestData="2019-11-26";
		Date requestD = Date.valueOf(requestData);
		   RequestBean bean = new RequestBean(4, 600, 20, "In valutazione", "Supporto prenotazione esame", "e.merola@studenti.unicampania.it", requestD);
				   requestDAO.confirmAppointment(1);
				assertNotNull(bean);
	}

	@Test
	void testDoRetrieveAllAccepted() throws SQLException{
		Collection<RequestBean> request = requestDAO.doRetrieveAllAccepted(null);
		assertNotNull(request);
	}
}