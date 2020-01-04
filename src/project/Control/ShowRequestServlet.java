package project.Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.RequestDAO;

@WebServlet("/ShowRequest")
public class ShowRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDAO requestDAO = new RequestDAO();
		
		String requestId = request.getParameter("id");
		
		int codice = -1;
		
		if(requestId!=null && !requestId.equals(""))
			codice = Integer.parseInt(requestId);
		
		try {
			request.removeAttribute("request");
			request.setAttribute("request", requestDAO.doRetrieveById(codice));
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/tutor/requestInfo.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}