package project.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Control.DBConnection;
import project.Model.StudentBean;
import project.Model.StudentDAO;


class StudentDAOTest {
	StudentDAO studentDAO = new StudentDAO();

	@BeforeEach
	void setUp() throws Exception {
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}

	@Test
	void testDoRetrieveByMail() throws SQLException {
		StudentBean student = studentDAO.doRetrieveByMail("a.tommasino@studenti.unicampania.it");
		assertNotNull(student);
	}

	@Test
	void testDoSave() throws SQLException {
		StudentBean user = new StudentBean("m.dellecave@studenti.unicampania.it", "M12345678", 3, "Marco", "Delle Cave", "3334568768", "M", "B512102121",2019);
		ArrayList<StudentBean> tutorlist = studentDAO.doRetrieveAll();
		assertEquals(3, tutorlist.size());
		studentDAO.doSave(user);
		tutorlist = studentDAO.doRetrieveAll();
		assertEquals(4,tutorlist.size());
	}
	
	@Test
	void testDoRetrieveAllByDates() throws SQLException {
		String date = "2019-11-24";
		Date startDate = Date.valueOf(date);
		String date2 = "2019-11-25";
		Date finishDate = Date.valueOf(date2);
		
		Collection<StudentBean> studentsCollection = studentDAO.doRetrieveAllByDates(null, startDate, finishDate);
		assertEquals(2,studentsCollection.size());
	}
}
