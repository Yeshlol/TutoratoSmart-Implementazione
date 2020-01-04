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

@WebServlet("/Calendar")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
					
					String startTime;
					int min = bean.getRequestTime() % 60;
					String minutes = "" + min;
					
					if (min == 0) {
						minutes = "00";
					}
					else if (bean.getRequestTime() % 60 < 10)
						minutes = "0" + bean.getRequestTime()%60;
					
					if (bean.getRequestTime()/60 < 10)
						startTime = "0" + bean.getRequestTime()/60 + ":" + minutes;
					else
						startTime = bean.getRequestTime()/60 + ":" + minutes;
					
					minutes = "" + bean.getRequestTime()%60;
					
					if (bean.getRequestTime()%60 < 10)
						minutes = "0" + bean.getRequestTime()%60;
					
					if (bean.getRequestTime()/60 < 10)
						startTime = "T0" + bean.getRequestTime()/60 + ":" + minutes + ":00";
					else
						startTime = "T" + bean.getRequestTime()/60 + ":" + minutes + ":00";
					
					int finish = bean.getRequestTime() + bean.getDuration();
					
					min = finish % 60;
					minutes = "" + min;
					
					String finishTime;
					
					if (min == 0) {
						minutes = "00";
					}
					
					if (min < 10)
						minutes = "0" + min;
					
					if (finish / 60 < 10)
						finishTime = "0" + finish / 60 + ":" + minutes;
					else
						finishTime = finish / 60 + ":" + minutes;
															
					CalendarEvent ce = new CalendarEvent();
	                ce.setTitle("Appuntamento");
	                ce.setDescription("Appuntamento con " + name);
	                ce.setStart(bean.getRequestDate() + startTime);
	                ce.setEnd(bean.getRequestDate() + finishTime);
					ce.setUrl("/TutoratoSmart/ShowRequest?id=" + bean.getIdRequest());
	                events.add(ce);
				}
				
				response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        PrintWriter out = response.getWriter();
		        out.write(new Gson().toJson(events));
		        return;
			}
		}
	}
}