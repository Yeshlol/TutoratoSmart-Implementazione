package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Model.AppointmentBean;

class AppointmentBeanTest {

	@Test
	void testAppointmentBean() {
		AppointmentBean appointment = new AppointmentBean();
		assertNotNull(appointment);
	}

	@Test
	void testGetIdAppointment() {
		AppointmentBean appointment = new AppointmentBean(1,"abc",1,"m.pisciotta@studenti.unicampania.it");
		assertEquals(1,appointment.getIdAppointment());
	}

	@Test
	void testSetIdAppointment() {
		AppointmentBean appointment = new AppointmentBean(1,"abc",1,"m.pisciotta@studenti.unicampania.it");
		appointment.setIdAppointment(2);
		assertEquals(2,appointment.getIdAppointment());
	}

	@Test
	void testGetRequestId() {
		AppointmentBean appointment = new AppointmentBean(1,"abc",1,"m.pisciotta@studenti.unicampania.it");
		assertEquals(1,appointment.getRequestId());
	}

	@Test
	void testSetRequestId() {
		AppointmentBean appointment = new AppointmentBean(1,"abc",1,"m.pisciotta@studenti.unicampania.it");
		appointment.setRequestId(2);
		assertEquals(2,appointment.getRequestId());
	}

	@Test
	void testGetDetails() {
		AppointmentBean appointment = new AppointmentBean(1,"abc",1,"m.pisciotta@studenti.unicampania.it");
		assertEquals("abc",appointment.getDetails());
	}

	@Test
	void testSetDetails() {
		AppointmentBean appointment = new AppointmentBean(1,"abc",1,"m.pisciotta@studenti.unicampania.it");
		appointment.setDetails("abcdef");
		assertEquals("abcdef",appointment.getDetails());
	}

	@Test
	void testGetTutor() {
		AppointmentBean appointment = new AppointmentBean(1,"abc",1,"m.pisciotta@studenti.unicampania.it");
		assertEquals("m.pisciotta@studenti.unicampania.it",appointment.getTutor());
	}

	@Test
	void testSetTutor() {
		AppointmentBean appointment = new AppointmentBean(1,"abc",1,"m.pisciotta@studenti.unicampania.it");
		appointment.setTutor("m.lombardo@studenti.unicampania.it");
		assertEquals("m.lombardo@studenti.unicampania.it",appointment.getTutor());
	}
}
