package project.Test.WhiteBox.Request_Management;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.DBConnection;
import project.Control.Request_Management.RequestServlet;
import project.Model.RequestBean;
import project.Model.RequestDAO;
import project.Model.UserBean;
import project.Model.UserDAO;
import project.Test.DatabaseHelper;

class RequestServletTest {
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static RequestServlet servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new RequestServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request = null;
		response = null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	
	// Accettazione prenotazione da parte di un tutor.
	@Test
	void testFlag1Success() throws ServletException, IOException, SQLException, JSONException {
		UserDAO user = new UserDAO();
		UserBean bean = user.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		request.getSession().setAttribute("user", bean);
		request.addParameter("flag", "1");
		request.addParameter("id","4");
		request.addParameter("duration","60");
				
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
	
	// Studente assente
	@Test
	void testFlag2Success() throws ServletException, IOException, SQLException, JSONException {
		RequestDAO requestDAO = new RequestDAO();
		RequestBean bean = requestDAO.doRetrieveById(4);
		request.getSession().setAttribute("request", bean);
		request.addParameter("flag", "2");
				
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
}
