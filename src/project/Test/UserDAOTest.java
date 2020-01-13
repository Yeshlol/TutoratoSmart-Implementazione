package project.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Control.*;
import project.Model.*;

class UserDAOTest {
	UserDAO userDAO = new UserDAO();
	
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
	void testDoRetrieveByMail() throws SQLException{
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		assertNotNull(user);
	}

	@Test
	void testDoSave() throws SQLException{
		UserBean user = new UserBean("f.pagano@studenti.unicampania.it", "M12345678", 3, "Francesco", "Pagano", "3334568765", "M", "B512102436");
		ArrayList<UserBean> userlist = userDAO.doRetrieveAll();
		assertEquals(7, userlist.size());		
		userDAO.doSave(user);
		userlist = userDAO.doRetrieveAll();
		assertEquals(8,userlist.size());
	}
}
