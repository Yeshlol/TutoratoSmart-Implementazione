package project.Control;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.ActivityTutorBean;
import project.Model.ActivityTutorDAO;

/**
 * Servlet implementation class AddActivityServlet
 */
@WebServlet("/AddActivityServlet")
public class AddActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  ActivityTutorDAO activityDAO=new ActivityTutorDAO();
	      ActivityTutorBean activityBean;
	      
	      
	      String categoria=request.getParameter("categoria");
	      Date data=Date.valueOf("data");
	      int oraInizio=Integer.parseInt("oraInizio");
	      int oraFine=Integer.parseInt("oraFine");
	      float oreSvolte=Float.parseFloat("oreSvolte");
	      String dettagli=request.getParameter("dettagli");
	      
	       
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
