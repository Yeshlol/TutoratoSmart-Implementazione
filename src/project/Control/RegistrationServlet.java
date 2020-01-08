package project.Control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import project.Model.StudentBean;
import project.Model.StudentDAO;
import project.Model.TutorBean;
import project.Model.TutorDAO;
import project.Model.UserBean;
import project.Model.UserDAO;


@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
        super();
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("registerStudent.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean ajax = Boolean.parseBoolean(request.getParameter("ajax"));  // Richieste ajax per validazione email
				
		if(ajax) {
			try {
				UserDAO userDAO = new UserDAO();
				UserBean beanAjax = null;
							
				JSONObject obj = new JSONObject();
				
				String email = request.getParameter("mail");
				
				if(email!=null) 
					beanAjax = userDAO.doRetrieveByMail(email);			
								
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				obj.put("disponibile", beanAjax.getEmail().equals(""));
				
				response.getWriter().write(obj.toString());
				return;
			} catch(SQLException e) { 
				response.sendRedirect("registration.jsp");			// Errore query
				return;
			} catch(JSONException e) {
				response.sendRedirect("registration.jsp"); 			// Errore parser json
				return;
			}
		}
				
		String email = request.getParameter("Email");				// Dati Utente
		String pwd = request.getParameter("Password");			
		String firstname = request.getParameter("FirstName");
		String lastname = request.getParameter("LastName");
		String telephoneNumber = request.getParameter("TelephoneNumber");
		String sex = request.getParameter("Sex");
		String registrationNumber = request.getParameter("RegistrationNumber");
		
		int flag = Integer.parseInt(request.getParameter("flag"));	// Flag passato nello script ajax: 1 = registrazione studente, 2 = registrazione tutor.
		JSONObject obj = new JSONObject();
		
		if (flag == 1) { 											// Registrazione nuovo Studente
			StudentDAO studentDAO = new StudentDAO();
			
			String academicYear = request.getParameter("AcademicYear");	// Dati Studente
			
			StudentBean student = new StudentBean();				
			
			student.setEmail(email);
			student.setPwd(pwd);
			student.setFirstName(firstname);
			student.setLastName(lastname);
			student.setTelephoneNumber(telephoneNumber);
			student.setSex(sex);
			student.setRegistrationNumber(registrationNumber);
			student.setAcademicYear(Integer.parseInt(academicYear));
				
			try {
				studentDAO.doSave(student);
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				obj.put("result", 1);				
			} catch (SQLException e) {				// Errore nel salvare il nuovo studente
				try {
					obj.put("result", 2);			
				} catch (JSONException jsonexp) {	// Errore parser json					
				}
			} catch (JSONException jsonexp) {		// Errore parser json
			}
			finally {
				response.getWriter().write(obj.toString());
			}
			
			return;			
		}
		
		else if (flag == 2) {														// Registrazione nuovo Tutor
			TutorDAO tutorDAO = new TutorDAO();
							
			String startDate = request.getParameter("StartDate");	// Dati Tutor
			int totalHours = Integer.parseInt(request.getParameter("TotalHours"));
			
			UserBean commissionMember = (UserBean) request.getSession().getAttribute("user");
			
			TutorBean tutor = new TutorBean();
			
			tutor.setEmail(email);
			tutor.setPwd(pwd);
			tutor.setFirstName(firstname);
			tutor.setLastName(lastname);
			tutor.setTelephoneNumber(telephoneNumber);
			tutor.setSex(sex);
			tutor.setRegistrationNumber(registrationNumber);
			tutor.setStartDate(Date.valueOf(startDate));
			tutor.setCommissionMember(commissionMember.getEmail());
					
			try {
				tutorDAO.doSave(tutor, totalHours);
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				obj.put("result", 1);				
			} catch (SQLException e) {				// Errore nel salvare il nuovo tutor
				try {
					obj.put("result", 2);			
				} catch (JSONException jsonexp) {	// Errore parser json					
				}
			} catch (JSONException jsonexp) {		// Errore parser json
			}
			finally {
				response.getWriter().write(obj.toString());
			}
			
			return;
		}
	}
}