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

/**
 * Servlet implementation class ServletTutor
 */
@WebServlet("/ServletTutor")
public class ServletTutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTutor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		TutorDAO tutor=new TutorDAO();
		Collection<TutorBean> tutorCollection=null;
		String startDate=request.getParameter("startActivity");
		if(startDate==null || startDate=="") {
			startDate="2019-01-01";
		}
		Date dataIniziale= Date.valueOf(startDate);
		String finishDate=request.getParameter("finishActivity");
		if(finishDate==null || finishDate=="") {
			finishDate="2019-12-28";
		}
		Date dataFinale= Date.valueOf(finishDate);
		
		
		
		try {
			tutorCollection=tutor.doRetrieveAllByDates(null, dataIniziale, dataFinale);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if (tutorCollection != null && !tutorCollection.isEmpty()) {
		request.removeAttribute("tutorCollection");
		request.setAttribute("tutorCollection", tutorCollection);
		}
	RequestDispatcher dispatcher = request.getRequestDispatcher("/commission/listaTutor.jsp");
	dispatcher.forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
