package project.Test;

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

import project.Control.ActivityServlet;
import project.Control.DBConnection;
import project.Model.UserBean;
import project.Model.UserDAO;

class AddActivityTest {
	private ActivityServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	

	@BeforeEach
	void setUp() throws Exception {
		servlet = new ActivityServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}

	// TC_8.0_1 Lunghezza commento non valida = 0
	@Test
	public void testCase_8_0_1() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("description", "");
		request.addParameter("category", "Sportello Tutorato");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "10:00");
		request.addParameter("finishTime", "11:00");
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Lunghezza commento non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_2 Lunghezza commento non valida > 240 
	@Test
	public void testCase_8_0_2() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("description", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		request.addParameter("category", "Sportello Tutorato");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "10:00");
		request.addParameter("finishTime", "11:00");
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Lunghezza commento non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_3 Successo
		@Test
		public void testCase_8_0_3() throws ServletException, IOException, SQLException, JSONException {
			request.addParameter("flag", "1");
			request.addParameter("description", "Svolto sportello di tutorato");
			request.addParameter("category", "Sportello Tutorato");
			request.addParameter("date", "2020-01-20");
			request.addParameter("startTime", "10:00");
			request.addParameter("finishTime", "11:00");
			UserDAO userDAO = new UserDAO();
			UserBean userBean = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
			
			request.getSession().setAttribute("user", userBean);
			
			servlet.doPost(request, response);
			
			String content = response.getContentAsString();
			JSONObject jsonObj = new JSONObject(content);
			int result = (int) jsonObj.get("result");
			
			assertEquals(result, 1);
		}
}