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

import project.Control.AppointmentServlet;
import project.Control.DBConnection;
import project.Model.*;


class AppointmentServletModificaTest {
	private AppointmentServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	

	@BeforeEach
	void setUp() throws Exception {
		servlet = new AppointmentServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	//TC_7.0_1 Lunghezza commento > 240
	@Test
	public void testCase_7_0_1() throws ServletException, IOException {
		request.addParameter("flag", "2");
		request.addParameter("commento", "Pppppppppppppppppppppppppppppppppppppppppppppppppppppppppp" + 
				"ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp" + 
				"ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp" + 
				"ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp" + 
				"ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp" + 
				"ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
		
		final String message = "Commento troppo lungo";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_7.0_2 Successo
	@Test
	public void testCase_7_0_2() throws ServletException, IOException, JSONException, SQLException{
		request.addParameter("flag", "2");
		request.addParameter("commento", "pppp");
		
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
}