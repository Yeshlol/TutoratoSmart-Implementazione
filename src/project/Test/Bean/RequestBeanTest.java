package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import project.Model.RequestBean;

class RequestBeanTest {

	private Date date = Date.valueOf("2020-01-15");
	
	@Test
	void testRequestBean() {
		RequestBean request = new RequestBean();
		assertNotNull(request);
	}

	@Test
	void testGetIdRequest() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		assertEquals(1,request.getIdRequest());
	}

	@Test
	void testSetIdRequest() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		request.setIdRequest(2);
		assertEquals(2,request.getIdRequest());
	}

	@Test
	void testGetRequestTime() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasiono@studenti.unicampania.it",date);
		assertEquals(800,request.getRequestTime());
	}

	@Test
	void testSetRequestTime() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		request.setRequestTime(900);
		assertEquals(900,request.getRequestTime());
	}

	@Test
	void testGetDuration() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		assertEquals(60,request.getDuration());
	}

	@Test
	void testSetDuration() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		request.setDuration(90);
		assertEquals(90,request.getDuration());
	}

	@Test
	void testGetState() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		assertEquals("Accettata",request.getState());
	}

	@Test
	void testSetState() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		request.setState("In valutazione");
		assertEquals("In valutazione",request.getState());
	}

	@Test
	void testGetStudentComment() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		assertEquals("abc",request.getStudentComment());
	}

	@Test
	void testSetStudentComment() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		request.setStudentComment("abcdef");
		assertEquals("abcdef",request.getStudentComment());
	}

	@Test
	void testGetStudent() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		assertEquals("a.tommasino@studenti.unicampania.it",request.getStudent());
	}

	@Test
	void testSetStudent() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		request.setStudent("e.merola@studenti.unicampania.it");
		assertEquals("e.merola@studenti.unicampania.it",request.getStudent());
	}

	@Test
	void testGetRequestDate() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		assertEquals(date,request.getRequestDate());
	}

	@Test
	void testSetRequestDate() {
		RequestBean request = new RequestBean(1,800,60,"Accettata","abc","a.tommasino@studenti.unicampania.it",date);
		String data1 = "2020-01-16";
		Date date1 = Date.valueOf(data1);
		request.setRequestDate(date1);
		assertEquals(date1,request.getRequestDate());
		
	}
}
