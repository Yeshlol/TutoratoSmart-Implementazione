package project.Control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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
    	String idActivity=request.getParameter("cod");
    	ActivityTutorDAO activityDAO=new ActivityTutorDAO();
    	RequestDispatcher dispatcher=null;
    	int id=0;
    	try {
			if (idActivity != null && !idActivity.equals("")) {
					id = Integer.parseInt(idActivity);
					ActivityTutorBean activity = activityDAO.doRetrieveById(id);
					request.removeAttribute("activity");
					request.setAttribute("activity",activity);
					dispatcher = request.getRequestDispatcher("/tutor/activityModify.jsp");
			}else dispatcher = request.getRequestDispatcher("/tutor/activityAdd.jsp");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	dispatcher.forward(request, response);
}
    	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idActivity = request.getParameter("cod");
		int id=0;
		ActivityTutorBean activityBean=new ActivityTutorBean();
		ActivityTutorDAO activityDAO=new ActivityTutorDAO();
		String fl=request.getParameter("flag");
		int flag=Integer.parseInt(fl);
		if ( (idActivity != null && !idActivity.equals("")) || flag==1 ) {
			if(flag!=1)
				id = Integer.parseInt(idActivity);
			String categoria=request.getParameter("categoria");
			Date data=Date.valueOf(request.getParameter("data"));
			int oraInizio=Integer.parseInt(request.getParameter("oraInizio"));
			int oraFine=Integer.parseInt(request.getParameter("oraFine"));
			String dettagli=request.getParameter("dettagli");
			
			float oreSvolte=oraFine-oraInizio;
			
			activityBean.setIdActivity(id);
			activityBean.setCategory(categoria);
			activityBean.setActivityDate(data);
			activityBean.setStartTime(oraInizio);
			activityBean.setFinishTime(oraFine);
			activityBean.setHours(oreSvolte);
			activityBean.setDetails(dettagli);
			
			try {
				if(flag==1) {
					activityDAO.doSave(activityBean);
				}else
					activityDAO.doModify(activityBean);
			}catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		response.sendRedirect(request.getContextPath()+ "/tutor/register.jsp");
     }
 }	
