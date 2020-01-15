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

import project.Control.DBConnection;
import project.Control.RequestServlet;
import project.Model.UserBean;
import project.Model.UserDAO;

class ManageRequestTest {
	private RequestServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@BeforeEach
	void setUp() throws Exception {
		servlet = new RequestServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}

	//TC_5.0_1 Ore inserite <10
	@Test
	public void testCase_5_0_1() throws ServletException, IOException {
		request.addParameter("flag", "4");
		request.addParameter("id","4");
		request.addParameter("duration","6");
		
		
		final String message = "Durata inserita non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_5.0_2 Ore inserite > 120
	@Test
	public void testCase_5_0_2() throws ServletException, IOException {
		request.addParameter("flag", "4");
		request.addParameter("id","4");
		request.addParameter("duration","150");
		
		
		final String message = "Durata inserita non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_5.0_3 Inserimento
	@Test
	public void testCase_5_0_3() throws ServletException, IOException, JSONException, SQLException {
		UserDAO user = new UserDAO();
		UserBean bean = user.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		request.getSession().setAttribute("user", bean);
		request.addParameter("flag", "4");
		request.addParameter("id","4");
		request.addParameter("duration","60");
				
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
}
