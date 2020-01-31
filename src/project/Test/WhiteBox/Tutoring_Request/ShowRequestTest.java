package project.Test.WhiteBox.Tutoring_Request;

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

import project.Control.Tutoring_Request.ShowRequestServlet;
import project.Control.DBConnection;
import project.Model.RequestBean;
import project.Model.UserBean;
import project.Model.UserDAO;
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
	
	// Visualizzazione storico richieste di appuntamento 
	@Test
	void testFlag1ShowRequests () throws ServletException, IOException, SQLException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("e.merola@studenti.unicampania.it");
		
		request.addParameter("flag", "1");
		request.getSession().setAttribute("user", user);
		
		servlet.doGet(request, response);
		
		@SuppressWarnings("unchecked")
		Collection<RequestBean> requestsCollection = (Collection<RequestBean>) request.getAttribute("requestsCollection");
		
		assertNotNull(requestsCollection);
	}
	
	// Visualizzazione richiesta 
	@Test
	void testFlag2ShowRequest () throws ServletException, IOException, SQLException {
		request.addParameter("flag", "2");
		request.addParameter("id", "2");
		
		servlet.doGet(request, response);
		
		RequestBean req = (RequestBean) request.getSession().getAttribute("request");
		
		assertNotNull(req);
	}
}
