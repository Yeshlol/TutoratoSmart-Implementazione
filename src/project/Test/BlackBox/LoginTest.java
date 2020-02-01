package project.Test.BlackBox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import project.Control.User.LoginServlet;
import project.Test.DatabaseHelper;


public class LoginTest {
	private LoginServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	

	@BeforeEach
	public void setUp() throws Exception {
		servlet = new LoginServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}

	//TC_1.0_1 Lunghezza email == 0
	@Test
	public void testCase_1_0_1() throws ServletException, IOException {
		request.addParameter("email", "");
		request.addParameter("password", "M12345678");
		
		final String message = "Lunghezza email non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_1.0_2 Lunghezza email > 45
	@Test
	public void testCase_1_0_2() throws ServletException, IOException {
		request.addParameter("email", "a.olivieriabcdertghilp@studenti.unicampania.it");
		request.addParameter("password", "M12345678");
		
		final String message = "Lunghezza email non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_1.0_3 Formato email errato
	@Test
	public void testCase_1_0_3() throws ServletException, IOException {
		request.addParameter("email", "a.olivieri@studenti.unisa.it");
		request.addParameter("password", "M12345678");

		final String message = "Formato email non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}

	//TC_1.0_4 Lunghezza password < 8
	@Test
	public void testCase_1_0_4() throws ServletException, IOException {
		request.addParameter("email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("password", "M12345");

		final String message = "Lunghezza password non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}

	//TC_1.0_5 Lunghezza password > 10
	@Test
	public void testCase_1_0_5() throws ServletException, IOException {
		request.addParameter("email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("password", "M1234567890");

		final String message = "Lunghezza password non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}


	//TC_1.0_6 Formato password non corretto
	@Test
	public void testCase_1_0_6() throws ServletException, IOException {
		request.addParameter("email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("password", "123456789");

		final String message = "Formato password non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_1.0.7 Successo
	@Test
	public void testCase_1_0_7() throws ServletException, IOException, JSONException {
		request.addParameter("email", "m.pisciotta@studenti.unicampania.it");
		request.addParameter("password", "M12345678");

		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
}