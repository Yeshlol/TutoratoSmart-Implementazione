package project.Control.Tutoring_Request;

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
import project.Utils.Utils;

/**
 * 
 *  Servlet implementation class CalendarServlet
 *
 */

@WebServlet("/CalendarTutoringRequest")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	 /**
     * Method doGet()
     * 
   	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}
    
	/**
     * Method doPost()
     * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Calendar calendar = null;
		try {
			calendar = Calendar.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
										
		Collection<RequestBean> requests = calendar.getRequests();
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();
		
		if(requests != null && !requests.isEmpty()) {		// Caricamento richieste di appuntamento per il calendario sportello di tutorato (lato studente).
			Iterator<?> it = requests.iterator();
			
			while(it.hasNext()) {
				RequestBean bean = (RequestBean)it.next();
									
				String startTime = "T" + Utils.getTimeAsString(bean.getRequestTime());
				String finishTime = "T" + Utils.getTimeAsString(bean.getRequestTime() + bean.getDuration());
														
				CalendarEvent ce = new CalendarEvent();
                ce.setTitle("Appuntamento");
                ce.setStart(bean.getRequestDate() + startTime);
                ce.setEnd(bean.getRequestDate() + finishTime);
                ce.setColor("#547094");
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
