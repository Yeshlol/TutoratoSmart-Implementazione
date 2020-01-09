package project.Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.RequestBean;
import project.Model.RequestDAO;

@WebServlet("/Request")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RequestServlet() {
        super();														
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato come parametro nell'url:
																		// 1 = cancellazione prenotazione da parte dello studente.
																		// 2 = accettazione prenotazione da parte di un tutor.
		RequestDAO requestDAO = new RequestDAO();
		
		if (flag == 1) {												// 1 = cancellazione prenotazione da parte dello studente.
			String requestId = request.getParameter("id");
			int id;
		
			if (requestId != null && requestId != "") {
				id = Integer.parseInt(requestId);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
				return;
			}
		
			String delete = request.getParameter("delete");
			
			if(delete != null && delete.equals("true")) {				// cancellazione prenotazione da parte dello studente.
				RequestBean bean;
				try {
					bean = requestDAO.doRetrieveById(id);
					requestDAO.doDelete(bean);
					request.getSession(false).removeAttribute("request");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				
			RequestDispatcher dispatcher = request.getRequestDispatcher("/student/requestsList.jsp");
			dispatcher.forward(request, response);
			return;	
		}
		else if (flag == 2) {
		
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
			return;
		}
    }
    	    	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato come hidden nel form: 
																		// 1 = registrazione nuova prenotazione.
																		// 2 = cancellazione prenotazione da parte dello studente.
		
		if (flag == 1) {
			
		}
		else if (flag == 2) {
		
		}
		else {
			
		}
	}
}