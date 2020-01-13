package project.Test;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import project.Control.DBConnection;

import org.apache.ibatis.jdbc.ScriptRunner;

public class DatabaseHelper {
	public static void initializeDatabase() throws SQLException, FileNotFoundException {
		DBConnection.setTest(true);
		Connection conn = DBConnection.getInstance().getConn();
		ScriptRunner sr = new ScriptRunner(conn);
		java.io.Reader reader = new BufferedReader(new FileReader("inserimentoDatiTest.sql"));
		sr.runScript(reader);	
	}
	
	public static void resetDatabase() throws SQLException, FileNotFoundException {
		DBConnection.setTest(true);
		Connection conn = DBConnection.getInstance().getConn();
		ScriptRunner sr = new ScriptRunner(conn);
		java.io.Reader reader = new BufferedReader(new FileReader("resetDatiTest.sql"));
		sr.runScript(reader);
	}
}