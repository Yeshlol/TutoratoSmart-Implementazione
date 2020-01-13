package project.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Control.DBConnection;
import project.Model.ManagesBean;
import project.Model.ManagesDAO;

class ManagesDAOTest {
	ManagesDAO managesDAO = new ManagesDAO();

	@BeforeEach
	void setUp() throws Exception {
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DBConnection.setTest(false);
	}

	@Test
	void testDoSave() throws SQLException {
		ManagesBean bean = new ManagesBean("m.pisciotta@studenti.unicampania.it",4);
		ArrayList<ManagesBean> list = managesDAO.doRetrieveAll();
		assertEquals(3,list.size());
		managesDAO.doSave(bean);
		list = managesDAO.doRetrieveAll();
		assertEquals(4,list.size());
		
	}

}
