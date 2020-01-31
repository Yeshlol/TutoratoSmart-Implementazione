package project.Test.WhiteBox.Tutoring_Supervision;

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

import project.Control.Tutoring_Supervision.ActivityServlet;
import project.Control.DBConnection;
import project.Model.UserBean;
import project.Model.UserDAO;
import project.Test.DatabaseHelper;

class ActivityServletTest {
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static ActivityServlet servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ActivityServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request = null;
		response = null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	//Validazione attività
	@Test
	void testFlag3ValidateSuccess() throws ServletException, IOException, SQLException, JSONException {
		request.addParameter("flag", "1");
		request.addParameter("id", "2");
		
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("d.molinaro@commissione.unicampania.it");
		request.getSession().setAttribute("user", user);
		
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
	
	// Rimozione di un'attività da parte di un membro della Commissione
	@Test
	void testFlag3DeleteSuccess() throws ServletException, IOException, SQLException, JSONException {
		request.addParameter("flag", "2");
		request.addParameter("id", "2");
		
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("d.molinaro@commissione.unicampania.it");
		request.getSession().setAttribute("user", user);
		
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
}
