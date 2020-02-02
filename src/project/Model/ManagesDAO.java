package project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.Control.DBConnection;

/**
 * Questa classe e' un manager che si occupa di interagire con il database. Gestisce le query riguardanti Gestione.
 */
public class ManagesDAO {
	public ManagesDAO() {
		super();
	}
	
	/** 
	 * @return un ArrayList di tutte le gestioni registrate nel database
	 * @throws SQLException lancia un'eccezione SQL
	 */
	public synchronized ArrayList<ManagesBean> doRetrieveAll() throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		ArrayList<ManagesBean>managesList = new ArrayList<ManagesBean>();
		String sql = "select * from MANAGES";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
		    ManagesBean bean = null;
        	String tutor=rs.getString("Tutor");
			int requestId=rs.getInt("RequestId");

			
			bean = new ManagesBean(tutor,requestId);
			managesList.add(bean);
		}
		return managesList;
	}
	
	/** 
	 * @param bean bean
	 * @throws SQLException lancia un'eccezione SQL
	 */
	public synchronized void doSave(ManagesBean bean) throws SQLException {
		Connection connection = DBConnection.getInstance().getConn();
		PreparedStatement preparedStatement=null;
		
		String insertSql = "INSERT INTO MANAGES(Tutor,RequestId) VALUES (?,?)";
		try {			
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSql);
			
			preparedStatement.setString(1,bean.getTutor());
			preparedStatement.setInt(2,bean.getRequestId());
						
			//System.out.println("Manages doSave: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		}		
	}	
}
