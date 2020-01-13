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

import project.Model.AppointmentBean;
import project.Model.AppointmentDAO;
import project.Model.RequestBean;
import project.Model.RequestDAO;
import project.Model.StudentDAO;
import project.Model.UserBean;

/**
 * 
 *  Servlet implementation class ShowRequestServlet
 *  
 */

@WebServlet("/ShowRequest")
public class ShowRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	/**
     * Method doGet()
     * 
   	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(false).removeAttribute("request");
		
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato nello script: 
																		// 1 = visualizzazione storico richieste di appuntamento (di studente).
																		// 2 = visualizzazione dettagli di una richiesta di appuntamento (lato studente).
																		// 3 = visualizzazione dettagli di una richiesta (lato tutor).
																		// 4 = visualizzazione richieste da valutare (lato tutor).
																		// 5 = visualizzazione richieste da validare (conferma appuntamento/studente assente).
		
		RequestDAO requestDAO = new RequestDAO();
				
		if (flag == 1) {												// Ricerca richieste
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
				request.getSession(false).removeAttribute("request");
				request.getSession(false).setAttribute("request",requestDAO.doRetrieveById(id));
			} catch(SQLException e) {
				e.printStackTrace();
			}		
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/student/requestInfo.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if(flag == 3) {
			String requestId = request.getParameter("id");
			
			int id = -1;
			
			if(requestId != null && !requestId.equals(""))
				id = Integer.parseInt(requestId);
			
			try {
				StudentDAO studentDAO = new StudentDAO();
				RequestBean req = requestDAO.doRetrieveById(id);
				
				if(req.getState().equals("Appuntamento effettuato")) {
					AppointmentDAO appointmentDAO = new AppointmentDAO();
					AppointmentBean appointment = appointmentDAO.doRetrieveByRequestId(req.getIdRequest());
					
					request.getSession(false).removeAttribute("appointment");		// Cancella la richiesta memorizzata in sessione, se presente
					request.getSession(false).setAttribute("appointment", appointment);		// Salva in sessione la richiesta da visualizzare
				}
				
				request.getSession(false).removeAttribute("request");		// Cancella la richiesta memorizzata in sessione, se presente
				request.getSession(false).setAttribute("request", req);		// Salva in sessione la richiesta da visualizzare
				request.getSession(false).removeAttribute("student");		// Cancella lo studente memorizzato in sessione, se presente
				request.getSession(false).setAttribute("student", studentDAO.doRetrieveByMail(req.getStudent()));	// Salva in sessione lo studente che ha fatto la richiesta
			} catch(SQLException e) {
				e.printStackTrace();
			}		
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/tutor/requestInfo.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if (flag == 4) {
			Collection<RequestBean> requestsCollection = null;
						
			try {
				requestsCollection = requestDAO.doRetrieveAllPending("YEAR(RequestDate) ASC, MONTH(RequestDate) ASC, DAY(RequestDate) ASC");
				request.removeAttribute("requestsCollection");
				request.setAttribute("requestsCollection", requestsCollection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/requestsList.jsp");
			dispatcher.forward(request, response);    
			return;
		}
		else if (flag == 5) {
			Collection<RequestBean> requestsCollection = null;
						
			try {
				requestsCollection = requestDAO.doRetrieveAllAccepted("YEAR(RequestDate) ASC, MONTH(RequestDate) ASC, DAY(RequestDate) ASC");
				request.removeAttribute("requestsCollection");
				request.setAttribute("requestsCollection", requestsCollection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/requestsList.jsp");
			dispatcher.forward(request, response);    
			return;
		}
		else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
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