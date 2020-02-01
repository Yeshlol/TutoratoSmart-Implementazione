package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import project.Model.*;

class StudentBeanTest {

	@Test
	void testStudentBeanEmpty() {
		StudentBean student = new StudentBean();
		assertNotNull(student);
	}

	@Test
	void testGetAcademicYear() {
		StudentBean student = new StudentBean("a.tommasino@studenti.unicampania.it", "M12345678", 3, "Antonio", "Tommasino", "3343355638", "M", "B512102498", 2019);
		assertEquals(2019,student.getAcademicYear());
	}

	@Test
	void testSetAcademicYear() {
		StudentBean student = new StudentBean("a.tommasino@studenti.unicampania.it", "M12345678", 3, "Antonio", "Tommasino", "3343355638", "M", "B512102498", 2019);
		student.setAcademicYear(2020);
		assertEquals(2020 , student.getAcademicYear());
	}
}
