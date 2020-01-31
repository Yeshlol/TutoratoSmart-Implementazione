package project.Test.WhiteBox.Tutoring_Request;

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
import project.Control.Tutoring_Request.RequestServlet;
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
	
	// Controllo altre richieste registrate, diverse da quella in modifica, non trovate
	@Test
	void testFlagAjaxAvailable() throws ServletException, IOException, SQLException, JSONException {
		request.addParameter("ajax","true");
		request.addParameter("time","10:00");
		request.addParameter("date","2020-01-15");
		request.addParameter("modify","true");
		request.addParameter("id", "1");
		
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		boolean result = (boolean) jsonObj.get("disponibile");
		
		assertEquals(result, true);
	}
	
	// Controllo altre richieste registrate, diverse da quella in modifica, trovate
	@Test
	void testFlagAjaxNotAvailable() throws ServletException, IOException, SQLException, JSONException {
		request.addParameter("ajax","true");
		request.addParameter("time","10:00");
		request.addParameter("date","");
		request.addParameter("modify","true");
		request.addParameter("id", "1");
		
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		boolean result = (boolean) jsonObj.get("disponibile");
		
		assertEquals(result, false);
	}
	
	// Controllo altre richieste registrate, non trovate
	@Test
	void testFlagAjaxModifyAvailable() throws ServletException, IOException, SQLException, JSONException {
		request.addParameter("ajax","true");
		request.addParameter("time","10:00");
		request.addParameter("date","2020-01-15");
		request.addParameter("modify","false");
		
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		boolean result = (boolean) jsonObj.get("disponibile");
		
		assertEquals(result, true);
	}
	
	// Nessun giorno selezionato
	@Test
	public void testErrorDayNotSelected() throws ServletException, IOException {
		request.addParameter("flag","1");
		request.addParameter("time","11:00");
		request.addParameter("comment","Mi serve aiuto per l'immatricolazione");
		request.addParameter("date","");
		
		final String message = "Selezionare la data";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// Selezionato giorno diverso da mercoledi e giovedi
	@Test
	public void testErrorDayNotAvailable() throws ServletException, IOException {
		request.addParameter("flag","1");
		request.addParameter("time","11:00");
		request.addParameter("comment","Mi serve aiuto per l'immatricolazione");
		request.addParameter("date","2020-01-13");
		
		final String message = "Giorno selezionato non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}

	// Orario non selezionato
	@Test
	public void testErrorTimeNotSelected() throws ServletException, IOException {
		request.addParameter("flag","1");
		request.addParameter("time","");
		request.addParameter("comment","Mi serve aiuto per l’immatricolazione");
		request.addParameter("date","2020-01-15");
		
		final String message = "Selezionare l’orario";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// Orario non valido Minuti < 540
	@Test
	public void testErrorTimeNotValid() throws ServletException, IOException {
		request.addParameter("flag","1");
		request.addParameter("time","06:00");
		request.addParameter("comment","Mi serve aiuto per l’immatricolazione");
		request.addParameter("date","2020-01-15");
		
		final String message = "Orario selezionato non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// Lunghezza commento non valida = 0
	@Test
	public void testErrorCommentLength() throws ServletException, IOException, JSONException, SQLException {	
		request.addParameter("flag","1");
		request.addParameter("time","10:00");
		request.addParameter("comment","");
		request.addParameter("date","2020-01-15");
				
		final String message = "Lunghezza commento non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// Registrazione nuova prenotazione da parte dello studente
	@Test
	void testFlag1Success() throws ServletException, IOException, SQLException, JSONException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("e.merola@studenti.unicampania.it");
		request.getSession().setAttribute("user", user);
		request.addParameter("flag","1");
		request.addParameter("time","10:00");
		request.addParameter("comment","Mi serve aiuto per l'immatricolazione");
		request.addParameter("date","2020-01-15");
		
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
	
	// Cancellazione prenotazione da parte dello studente
	@Test
	void testFlag2Success() throws ServletException, IOException, SQLException, JSONException {
		RequestDAO requestDAO = new RequestDAO();
		RequestBean requestbean =requestDAO.doRetrieveById(4);
		request.getSession().setAttribute("request", requestbean);
		request.addParameter("flag","2");
		request.addParameter("id","4");
		
		
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
		
	// Modifica prenotazione da parte dello studente.
	@Test
	void testFlag3Success() throws ServletException, IOException, SQLException, JSONException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("e.merola@studenti.unicampania.it");
		request.getSession().setAttribute("user", user);
		request.addParameter("flag","3");
		request.addParameter("time","10:00");
		request.addParameter("comment","Mi serve aiuto per l'immatricolazione");
		request.addParameter("date","2020-01-15");
		request.addParameter("id", "4");
				
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}	
}
