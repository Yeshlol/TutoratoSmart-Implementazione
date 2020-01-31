package project.Control.Tutoring_Request;

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


/**
 * 
 *  Servlet implementation class ShowRequestServlet
 *  
 */

@WebServlet("/ShowRequestTutoringRequest")
public class ShowRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	/**
     * Method doGet()
     * 
   	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("request");
		
		RequestDAO requestDAO = new RequestDAO();
				
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato nello script: 
																		// 1 = visualizzazione storico richieste di appuntamento.
																		// 2 = visualizzazione dettagli di una richiesta di appuntamento.
																		
		if (flag == 1) {												// Storico prenotazioni
			Collection<RequestBean> requestsCollection = null;
			
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			String email = user.getEmail();
			
		try {
			requestsCollection = requestDAO.doRetrieveAllByMail("YEAR(RequestDate) DESC, MONTH(RequestDate) DESC, DAY(RequestDate) DESC", email);
			request.removeAttribute("requestsCollection");
			request.setAttribute("requestsCollection", requestsCollection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View/student/requestsList.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if(flag == 2) {											// Visualizzazione dettagli di una richiesta di appuntamento
			String requestId = request.getParameter("Id");
			
			int id = -1;
			
			if(requestId != null && !requestId.equals(""))
				id = Integer.parseInt(requestId);
			
			try {
				request.getSession(false).removeAttribute("request");
				request.getSession(false).setAttribute("request",requestDAO.doRetrieveById(id));
			} catch(SQLException e) {
				e.printStackTrace();
			}		
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View/student/requestInfo.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

	/**
     * Method doPost()
     * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}
}