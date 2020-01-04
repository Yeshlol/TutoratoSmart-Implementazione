package project.Control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.ActivityTutorBean;
import project.Model.ActivityTutorDAO;


@WebServlet("/ModifyActivity")
public class ModifyActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ModifyActivityServlet() {
        super();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ActivityTutorBean activityBean=new ActivityTutorBean();
		ActivityTutorDAO activityDAO=new ActivityTutorDAO();
		 
		String categoria=request.getParameter("categoria");
		String data=request.getParameter("data");
		String oraInizio=request.getParameter("oraInizio");
		String oraFine=request.getParameter("oraFine");
		String oreSvolte=request.getParameter("oreAttivit�Svolte");
		String dettagli=request.getParameter("dettagli");
		String id=request.getParameter("idActivity");
	     
	    Date date=Date.valueOf(data);
	    int orainizio=Integer.parseInt(oraInizio);
	    int orafine=Integer.parseInt(oraFine);
	    float oresvolte=Float.parseFloat(oreSvolte);
	    int idattivita=Integer.parseInt(id);
	    
	    activityBean.setIdActivity(idattivita);
	    activityBean.setCategory(categoria);
	    activityBean.setActivityDate(date);
	    activityBean.setStartTime(orainizio);
	    activityBean.setFinishTime(orafine);
	    activityBean.setHours(oresvolte);
	    activityBean.setDetails(dettagli);
	     
	   
	    try {
	    	activityDAO.doModify(activityBean);
			request.getSession(false).removeAttribute("activity");
	    	request.getSession(false).setAttribute("activity", activityDAO.doRetrieveById(idattivita));
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("modifyActivity.jsp");
		}
	    request.getRequestDispatcher("activity.jsp").forward(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
