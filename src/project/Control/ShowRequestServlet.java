package project.Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.RequestBean;
import project.Model.RequestDAO;
import project.Model.UserBean;


@WebServlet("/ShowRequest")
public class ShowRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato nell'url: 
																		// 1 = visualizzazione elenco richieste di appuntamento.
																		// 2 = visualizzazione dettagli di una richiesta di appuntamento.

		RequestDAO requestDAO = new RequestDAO();
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		String email = user.getEmail();
		
		if (flag == 1) {												// Ricerca richieste
			Collection<RequestBean> requestsCollection = null;		
			try {
				requestsCollection = requestDAO.doRetrieveAllByMail(null, email);
				request.removeAttribute("requestsCollection");
				request.setAttribute("requestsCollection", requestsCollection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/student/requestsList.jsp");
			dispatcher.forward(request, response);    
			return;
		}
		else if(flag == 2) {
			String requestId = request.getParameter("Id");
			
			int id = -1;
			
			if(requestId != null && !requestId.equals(""))
				id = Integer.parseInt(requestId);
			
			try {
				request.removeAttribute("request");
				request.setAttribute("request", requestDAO.doRetrieveById(id));
				request.getSession(false).setAttribute("request",requestDAO.doRetrieveById(id));
			} catch(SQLException e) {
				e.printStackTrace();
			}		
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/student/requestInfo.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}
}