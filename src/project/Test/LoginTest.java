/*package project.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.DBConnection;
import project.Control.LoginServlet;

public class LoginTest {
	private LoginServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	//CONTROLLARE MESSAGGI
	final String message = "Formato errato dati";
	final String successMessage="successo";

	@BeforeEach
	public void setUp() throws Exception {
		servlet=new LoginServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	//TC_1.0_1 Lunghezza email insufficiente
	@Test
	public void testCase_1() throws ServletException, IOException {
		request.addParameter("Email", "@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		
		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.0_2 Lunchezza email non corretta
	@Test
	public void testCase_2() throws ServletException, IOException {
		request.addParameter("Email", "a.olivieriabcdertghilp@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.0_3 Formato email errato
	@Test
	public void testCase_3() throws ServletException, IOException {
		request.addParameter("Email", "a.olivieri@studenti.unisa.it");
		request.addParameter("Password", "M12345678");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.0_4 Lunghezza password insufficiente
	@Test
	public void testCase_4() throws ServletException, IOException {
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_1.0_5 Lunghezza password non corretta
	@Test
	public void testCase_5() throws ServletException, IOException {
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M1234567890");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}


	//TC_1.0_6 Formato password non corretto
	@Test
	public void testCase_6() throws ServletException, IOException {
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "123456789");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	
	//TC_1.0.7 Successo
	@Test
	public void testCase_7() throws ServletException, IOException {
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");

		servlet.doPost(request, response);

		assertEquals(successMessage, (String) request.getAttribute("result"));
	}

	@AfterEach
	public void tearDown() throws Exception{
		DBConnection.setTest(false);
	}
}*/