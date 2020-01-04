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

/**
 * Servlet implementation class ModifyActivityServlet
 */
@WebServlet("/ModifyActivityServlet")
public class ModifyActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		             ActivityTutorBean activityBean=new ActivityTutorBean();
		             ActivityTutorDAO activityDAO=new ActivityTutorDAO();
		             
		             String categoria=request.getParameter("categoria");
		             String data=request.getParameter("data");
		             String oraInizio=request.getParameter("oraInizio");
		             String oraFine=request.getParameter("oraFine");
		             String oreSvolte=request.getParameter("oreAttivit‡Svolte");
		             String dettagli=request.getParameter("dettagli");
		             String id=request.getParameter("idActivity");
		             
		            Date date=Date.valueOf(data);
		            int orainizio=Integer.parseInt(oraInizio);
		            int orafine=Integer.parseInt(oraFine);
		            float oresvolte=Float.parseFloat(oreSvolte);
		            int idattivit‡=Integer.parseInt(id);
		            
		             activityBean.setIdActivity(idattivit‡);
		             activityBean.setCategory(categoria);
		             activityBean.setActivityDate(date);
		             activityBean.setStartTime(orainizio);
		             activityBean.setFinishTime(orafine);
		             activityBean.setHours(oresvolte);
		             activityBean.setDetails(dettagli);
		             
		           
		            	 try {
							activityDAO.doModify(activityBean);
							 request.getSession(false).removeAttribute("activity");
			            	 request.getSession(false).setAttribute("activity", activityDAO.doRetrieveById(idattivit‡));
						} catch (SQLException e) {
							e.printStackTrace();
							response.sendRedirect("modifyActivity.jsp");
						}
		             request.getRequestDispatcher("activity.jsp").forward(request, response);	
		             
		             		
		             
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
