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
import project.Control.RequestServlet;

public class GestioneRichiestaTest {
	private RequestServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	//CONTROLLARE MESSAGGI
	final String message = "Formato errato dati";
	final String successMessage="successo";

	@BeforeEach
	public void setUp() throws Exception {
		servlet=new RequestServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	//TC_5.0_1 Durata fuori dall’intervallo consentito
	@Test
	public void testCase_1() throws ServletException, IOException {
		request.addParameter("time", "9");
		
		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_5.0_2 Durata fuori dall’intervallo consentito
	@Test
	public void testCase_2() throws ServletException, IOException {
		request.addParameter("time", "122");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}


	//TC_5.0.3 Successo
	@Test
	public void testCase_3() throws ServletException, IOException {
		request.addParameter("time", "60");

		servlet.doPost(request, response);

		assertEquals(successMessage, (String) request.getAttribute("result"));
	}

	@AfterEach
	public void tearDown() throws Exception{
		DBConnection.setTest(false);
	}
}*/