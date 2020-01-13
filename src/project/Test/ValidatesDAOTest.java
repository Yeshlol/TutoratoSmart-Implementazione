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
import project.Model.*;

class ValidatesDAOTest {
	ActivityTutorDAO activityTutorDAO = new ActivityTutorDAO();
	ValidatesDAO validatesDAO = new ValidatesDAO();

	@BeforeEach
	void setUp() throws Exception {
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DBConnection.setTest(false);
	}

	@Test
	void testDoRetrieveByCommissionMember() throws SQLException {
		Collection<ActivityTutorBean> validateList = (Collection<ActivityTutorBean>) validatesDAO.doRetrieveByCommissionMember("d.molinaro@commissione.unicampania.it");
		assertNotNull(validateList);
	}

	
	/* DA CONTROLLARE
	
	@Test 
	void testDoSave() throws SQLException{	
		ValidatesBean validates = new ValidatesBean("d.molinaro@commissione.unicampania.it", 4);
		ArrayList<ActivityTutorBean> activityTutorlist = validatesDAO.doRetrieveAll();
		assertEquals(3, activityTutorlist.size());
		validatesDAO.doSave(validates);
		activityTutorlist = validatesDAO.doRetrieveAll();
		assertEquals(4,activityTutorlist.size());
	}*/
}
