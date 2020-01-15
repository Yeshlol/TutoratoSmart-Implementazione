package project.Test.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Control.DBConnection;
import project.Model.*;
import project.Test.DatabaseHelper;

class ValidatesDAOTest {
	ActivityTutorDAO activityTutorDAO = new ActivityTutorDAO();
	ValidatesDAO validatesDAO = new ValidatesDAO();

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
	void testDoRetrieveAll() throws SQLException {
		ArrayList<ValidatesBean> validatesList = (ArrayList<ValidatesBean>) validatesDAO.doRetrieveAll();
		assertNotNull(validatesList);
	}
	
	@Test
	void testDoRetrieveByCommissionMember() throws SQLException {
		Collection<ActivityTutorBean> validateList = (Collection<ActivityTutorBean>) validatesDAO.doRetrieveByCommissionMember("d.molinaro@commissione.unicampania.it");
		assertNotNull(validateList);
	}
		
	@Test 
	void testDoSaveValidates() throws SQLException {	
		ValidatesBean validates = new ValidatesBean("d.molinaro@commissione.unicampania.it", 3);
		ArrayList<ValidatesBean> validatesList = validatesDAO.doRetrieveAll();
		
		assertEquals(2, validatesList.size());
		validatesDAO.doSave(validates);
		validatesList = validatesDAO.doRetrieveAll();
		assertEquals(3,validatesList.size());
	}
}
