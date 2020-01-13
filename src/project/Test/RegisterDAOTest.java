package project.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Control.DBConnection;
import project.Model.RegisterBean;
import project.Model.RegisterDAO;

class RegisterDAOTest {
	RegisterDAO registerDAO = new RegisterDAO();

	@BeforeEach
	void setUp() throws Exception {
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DBConnection.setTest(false);
	}

	@Test
	void testDoRetrieveById() throws SQLException {
		RegisterBean register = registerDAO.doRetrieveById(1);
		assertNotNull(register);
	}

	@Test
	void testDoSave() throws SQLException {
		int id=0;
		id=registerDAO.doSave(10);
		assertEquals(4,4);
		
	
	}

	@Test
	void testDoUpdate() throws SQLException {
		RegisterBean register1= registerDAO.doRetrieveById(3);
		RegisterBean register = new RegisterBean(2,"Approvato",4,40);
		registerDAO.doUpdate(register);
		assertNotEquals(register1,register);
		
	}

}
