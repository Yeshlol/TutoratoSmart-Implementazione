package project.Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import project.Model.Calendar;
import project.Model.CalendarEvent;
import project.Model.RequestBean;
import project.Model.StudentBean;
import project.Model.StudentDAO;
import project.Utils.Utils;

@WebServlet("/Calendar")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Calendar calendar = null;
		try {
			calendar = Calendar.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int flag = Integer.parseInt(request.getParameter("flag"));	// Flag passato nello script calendarScript: 
																	// 1 = caricamento richieste di appuntamento per calendario (lato tutor)

		if (flag == 1) {				
			Collection<RequestBean> requests = calendar.getRequests();
			List<CalendarEvent> events = new ArrayList<CalendarEvent>();
			
			if(requests != null && !requests.isEmpty()) {		// Richieste di appuntamento
				Iterator<?> it = requests.iterator();
				StudentDAO studentDAO = new StudentDAO();
				
				while(it.hasNext()) {
					RequestBean bean = (RequestBean)it.next();
					StudentBean student = null;
					try {
						student = studentDAO.doRetrieveByMail(bean.getStudent());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					String name = student.getLastName() + " " + student.getFirstName();
					
					String startTime = "T" + Utils.getTimeAsString(bean.getRequestTime());
					String finishTime = "T" + Utils.getTimeAsString(bean.getRequestTime() + bean.getDuration());
															
					CalendarEvent ce = new CalendarEvent();
	                ce.setTitle("Appuntamento");
	                ce.setDescription("Appuntamento con " + name);
	                ce.setStart(bean.getRequestDate() + startTime);
	                ce.setEnd(bean.getRequestDate() + finishTime);
	                ce.setUrl("/TutoratoSmart/ShowRequest?flag=3&id=" + bean.getIdRequest());
	                
	                if(bean.getState().equals("In valutazione"))
	                	ce.setColor("#FFFF00");
	                else if(bean.getState().equals("Accettata"))
	                	ce.setColor("green");
	                else if(bean.getState().equals("Appuntamento effettuato"))
	                	ce.setColor("#232F3E");
	                else
	                	ce.setColor("red");
	                events.add(ce);
				}
				
				response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        PrintWriter out = response.getWriter();
		        out.write(new Gson().toJson(events));
		        calendar.reset();
		        return;
			}
		}
	}
}