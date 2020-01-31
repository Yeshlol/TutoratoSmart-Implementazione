package project.Test.WhiteBox.Request_Management;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.Request_Management.ShowRequestServlet;
import project.Control.DBConnection;
import project.Model.RequestBean;
import project.Test.DatabaseHelper;

class ShowRequestTest {
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static ShowRequestServlet servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ShowRequestServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request = null;
		response = null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	// Visualizzazione dettagli di una richiesta di appuntamento
	@Test
	void testFlag1ShowRequest () throws ServletException, IOException, SQLException {
		request.addParameter("id", "2");
		request.addParameter("flag", "1");
		
		servlet.doGet(request, response);
		
		RequestBean req = (RequestBean) request.getSession().getAttribute("request");
		
		assertNotNull(req);
	}
	
	// Visualizzazione richieste da valutare
	@Test
	void testFlag2ShowRequest () throws ServletException, IOException, SQLException {
		request.addParameter("flag", "2");
		
		servlet.doGet(request, response);
		
		@SuppressWarnings("unchecked")
		Collection<RequestBean> requestsCollection = (Collection<RequestBean>) request.getAttribute("requestsCollection");
		
		assertNotNull(requestsCollection);
	}
	
	// Visualizzazione richieste da validare (conferma appuntamento/studente assente)
	@Test
	void testFlag3ShowRequest () throws ServletException, IOException, SQLException {
		request.addParameter("flag", "3");
		
		servlet.doGet(request, response);
		
		@SuppressWarnings("unchecked")
		Collection<RequestBean> requestsCollection = (Collection<RequestBean>) request.getAttribute("requestsCollection");
		
		assertNotNull(requestsCollection);
	}
}
