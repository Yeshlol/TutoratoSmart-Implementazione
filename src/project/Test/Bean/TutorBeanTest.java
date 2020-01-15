package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import project.Model.*;


class TutorBeanTest {



	@Test
	void testTutorBeanEmpty() {
		TutorBean tutor = new TutorBean();
		assertNotNull(tutor);
	}

	@Test
	void testGetState() {
		String startData="2019-09-21";
		Date startDate = Date.valueOf(startData);
		String finishData = "2019-12-28";
		Date finishDate = Date.valueOf(finishData);
		
		TutorBean tutor = new TutorBean("m.lombardo@studenti.unicampania.it", "M12345678", 2, "Marta", "Lombardo", "3343355634", "F", "A212102495", startDate, finishDate, "d.molinaro@commissione.unicampania.it", "Attivo", 3);
		assertEquals("Attivo",tutor.getState());
	}

	@Test
	void testSetState() {
		String startData="2019-09-21";
		Date startDate = Date.valueOf(startData);
		String finishData = "2019-12-28";
		Date finishDate = Date.valueOf(finishData);
		
		TutorBean tutor = new TutorBean("m.lombardo@studenti.unicampania.it", "M12345678", 2, "Marta", "Lombardo", "3343355634", "F", "A212102495", startDate, finishDate, "d.molinaro@commissione.unicampania.it", "Attivo", 3);
		tutor.setState("Non Attivo");
		assertEquals("Non Attivo", tutor.getState());
	}

	@Test
	void testGetStartDate() {
		String startData="2019-09-21";
		Date startDate = Date.valueOf(startData);
		String finishData = "2019-12-28";
		Date finishDate = Date.valueOf(finishData);
		
		TutorBean tutor = new TutorBean("m.lombardo@studenti.unicampania.it", "M12345678", 2, "Marta", "Lombardo", "3343355634", "F", "A212102495", startDate, finishDate, "d.molinaro@commissione.unicampania.it", "Attivo", 3);
		assertEquals(startDate,tutor.getStartDate());
	}

	@Test
	void testSetStartDate() {
		String startData="2019-09-21";
		Date startDate = Date.valueOf(startData);
		String finishData = "2019-12-28";
		Date finishDate = Date.valueOf(finishData);
		
		String newData="2019-10-20";
		Date newDate = Date.valueOf(newData);
		
		TutorBean tutor = new TutorBean("m.lombardo@studenti.unicampania.it", "M12345678", 2, "Marta", "Lombardo", "3343355634", "F", "A212102495", startDate, finishDate, "d.molinaro@commissione.unicampania.it", "Attivo", 3);
		tutor.setStartDate(newDate);
		assertEquals(newDate, tutor.getStartDate());
	}

	@Test
	void testGetFinishDate() {
		String startData="2019-09-21";
		Date startDate = Date.valueOf(startData);
		String finishData = "2019-12-28";
		Date finishDate = Date.valueOf(finishData);
		
		TutorBean tutor = new TutorBean("m.lombardo@studenti.unicampania.it", "M12345678", 2, "Marta", "Lombardo", "3343355634", "F", "A212102495", startDate, finishDate, "d.molinaro@commissione.unicampania.it", "Attivo", 3);
		assertEquals(finishDate,tutor.getFinishDate());
	}

	@Test
	void testSetFinishDate() {
		String startData="2019-09-21";
		Date startDate = Date.valueOf(startData);
		String finishData = "2019-12-28";
		Date finishDate = Date.valueOf(finishData);
		
		String newData="2019-10-20";
		Date newDate = Date.valueOf(newData);
		
		TutorBean tutor = new TutorBean("m.lombardo@studenti.unicampania.it", "M12345678", 2, "Marta", "Lombardo", "3343355634", "F", "A212102495", startDate, finishDate, "d.molinaro@commissione.unicampania.it", "Attivo", 3);
		tutor.setFinishDate(newDate);
		assertEquals(newDate, tutor.getFinishDate());
	}

	@Test
	void testGetCommissionMember() {
		String startData="2019-09-21";
		Date startDate = Date.valueOf(startData);
		String finishData = "2019-12-28";
		Date finishDate = Date.valueOf(finishData);
		
		TutorBean tutor = new TutorBean("m.lombardo@studenti.unicampania.it", "M12345678", 2, "Marta", "Lombardo", "3343355634", "F", "A212102495", startDate, finishDate, "d.molinaro@commissione.unicampania.it", "Attivo", 3);
		assertEquals("d.molinaro@commissione.unicampania.it", tutor.getCommissionMember());
	}

	@Test
	void testSetCommissionMember() {
		String startData="2019-09-21";
		Date startDate = Date.valueOf(startData);
		String finishData = "2019-12-28";
		Date finishDate = Date.valueOf(finishData);
		
		TutorBean tutor = new TutorBean("m.lombardo@studenti.unicampania.it", "M12345678", 2, "Marta", "Lombardo", "3343355634", "F", "A212102495", startDate, finishDate, "d.molinaro@commissione.unicampania.it", "Attivo", 3);
		tutor.setCommissionMember("p.pallino@commissione.unicampania.it");
		assertEquals("p.pallino@commissione.unicampania.it", tutor.getCommissionMember());
	}

	@Test
	void testGetRegisterId() {
		String startData="2019-09-21";
		Date startDate = Date.valueOf(startData);
		String finishData = "2019-12-28";
		Date finishDate = Date.valueOf(finishData);
		
		TutorBean tutor = new TutorBean("m.lombardo@studenti.unicampania.it", "M12345678", 2, "Marta", "Lombardo", "3343355634", "F", "A212102495", startDate, finishDate, "d.molinaro@commissione.unicampania.it", "Attivo", 3);
		assertEquals(3 , tutor.getRegisterId());
	}

	@Test
	void testSetRegisterId() {
		String startData="2019-09-21";
		Date startDate = Date.valueOf(startData);
		String finishData = "2019-12-28";
		Date finishDate = Date.valueOf(finishData);
		
		TutorBean tutor = new TutorBean("m.lombardo@studenti.unicampania.it", "M12345678", 2, "Marta", "Lombardo", "3343355634", "F", "A212102495", startDate, finishDate, "d.molinaro@commissione.unicampania.it", "Attivo", 3);
		tutor.setRegisterId(4);
		assertEquals(4 , tutor.getRegisterId());
	}

}
