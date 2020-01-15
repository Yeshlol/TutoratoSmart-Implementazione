package project.Test.WhiteBox;

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
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new RequestServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	//caso di testing con parametro ajax settato a true 
	@Test
	void testflagajaxsucces() throws ServletException, IOException, SQLException, JSONException {
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
	//caso di testing con parametro ajax settato a true e parametro data non settato 
	@Test
	void testflagajaxfail() throws ServletException, IOException, SQLException, JSONException {
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
	//caso di testing con parametro ajax settato a true e parametro modify settato a false 
	@Test
	void testflagmodify() throws ServletException, IOException, SQLException, JSONException {
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
	//Registrazione nuova prenotazione
	@Test
	void testflag1succes() throws ServletException, IOException, SQLException, JSONException {
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
	//Cancellazione prenotazione
	@Test
	void testflag2succes() throws ServletException, IOException, SQLException, JSONException {
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
	//Modifica prenotazione 
		@Test		
		void testflag3succes() throws ServletException, IOException, SQLException, JSONException {
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
		//Accettazione richiesta da parte del tutor
		@Test
		void testflag4succes() throws ServletException, IOException, SQLException, JSONException {
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
		//Studente assente
		@Test
		void testflag5succes() throws ServletException, IOException, SQLException, JSONException {
			RequestDAO requestDAO = new RequestDAO();
			RequestBean bean = requestDAO.doRetrieveById(4);
			request.getSession().setAttribute("request", bean);
			request.addParameter("flag", "5");
							
			servlet.doPost(request, response);
					
			String content = response.getContentAsString();
			JSONObject jsonObj = new JSONObject(content);
			int result = (int) jsonObj.get("result");
					
			assertEquals(result, 1);
			}				
}
