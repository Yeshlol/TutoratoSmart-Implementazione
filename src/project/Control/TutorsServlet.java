package project.Control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.TutorBean;
import project.Model.TutorDAO;


@WebServlet("/Tutors")
public class TutorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TutorsServlet() {
        super();
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}
	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TutorDAO tutorDAO = new TutorDAO();
		Collection<TutorBean> tutorsCollection = null;
		
		String startDate = request.getParameter("startDate");
		if (startDate == null || startDate == "") {
			startDate = "2019-01-01";
		}
		Date dataIniziale = Date.valueOf(startDate);
		
		String finishDate = request.getParameter("finishDate");
		if(finishDate == null || finishDate == "") {
			finishDate = "2019-12-31";
		}
		Date dataFinale= Date.valueOf(finishDate);
				
		try {
			tutorsCollection = tutorDAO.doRetrieveAllByDates(null, dataIniziale, dataFinale);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		if (tutorsCollection != null) {
			request.removeAttribute("tutorsCollection");
			request.setAttribute("tutorsCollection", tutorsCollection);
		}
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/commission/tutorsList.jsp");
		dispatcher.forward(request, response);
	}
}
