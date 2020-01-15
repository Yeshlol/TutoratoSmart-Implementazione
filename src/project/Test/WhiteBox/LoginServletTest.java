package project.Test.WhiteBox;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.DBConnection;
import project.Control.LoginServlet;
import project.Test.DatabaseHelper;

class LoginServletTest {
   
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static LoginServlet servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new LoginServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	//Login (successo)
	@Test
	public void testsuccess() throws ServletException, IOException, JSONException {
		request.addParameter("email", "m.pisciotta@studenti.unicampania.it");
		request.addParameter("password", "M12345678");

		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
	//Login (fallimento)
	@Test
	public void testunssuccess() throws ServletException, IOException, JSONException {
		request.addParameter("email", "m.dellecave@studenti.unicampania.it");
		request.addParameter("password", "M12345678");   
		
        final String message = "Utente non trovato";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
		
	}
}
