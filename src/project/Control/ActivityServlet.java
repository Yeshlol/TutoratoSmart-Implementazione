package project.Control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import project.Model.ActivityTutorBean;
import project.Model.ActivityTutorDAO;
import project.Model.AppointmentBean;
import project.Model.AppointmentDAO;
import project.Model.ContainedInBean;
import project.Model.ContainedInDAO;
import project.Model.RequestBean;
import project.Model.RequestDAO;
import project.Model.StudentBean;
import project.Model.StudentDAO;
import project.Model.TutorBean;
import project.Model.TutorDAO;
import project.Model.UserBean;
import project.Model.ValidatesBean;
import project.Model.ValidatesDAO;
import project.Utils.Utils;

/**
 * 
 *  Servlet implementation class ActivityServlet
 *
 */
@WebServlet("/Activity")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Constructor
	 * 
     * @see HttpServlet#HttpServlet()
     */
    public ActivityServlet() {
        super();
    }

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
		boolean ajax = Boolean.parseBoolean(request.getParameter("ajax"));  // Richieste ajax per elenco appuntamenti effettuati dal tutor
		
		UserBean user = (UserBean) request.getSession(false).getAttribute("user");
		
		TutorDAO tutorDAO = new TutorDAO();
		ActivityTutorDAO activityDAO = new ActivityTutorDAO();
		RequestDAO requestDAO = new RequestDAO();
		StudentDAO studentDAO = new StudentDAO();
		ContainedInDAO containedInDAO = new ContainedInDAO();
		
		JSONObject obj = new JSONObject();
		
		if(ajax) {															
			String date = request.getParameter("date");
			String startTime = request.getParameter("startTime");
			String finishTime = request.getParameter("finishTime");
			
			if (date != null && date != "" && startTime != null && startTime != "" && finishTime != null && finishTime != "") {
				JSONArray ja = new JSONArray();
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				Date activityDate = Date.valueOf(date);
				int start = Utils.getTimeAsInt(startTime);
				int finish = Utils.getTimeAsInt(finishTime);
				
				boolean check = Boolean.parseBoolean(request.getParameter("check"));  // Richieste ajax per cercare se esiste già un'attività registrata in quella giornata
				if(check) {					
					boolean available;
					
					boolean modify = Boolean.parseBoolean(request.getParameter("modify"));
					
					if(modify) {														// Controllo altre attività registrate, diverse da quella in modifica
						int activityId = Integer.parseInt(request.getParameter("activityId"));
						
						try {
							available = activityDAO.differentActivityRegistered(user.getEmail(), activityId, activityDate, start, finish);
							obj.put("available", available);
						} catch (JSONException e) {
							e.printStackTrace();
							return;
						} catch (SQLException e) {
							e.printStackTrace();
							return;
						}
						
						response.getWriter().write(obj.toString());
						return;
					}
					else {																// Controllo altre attività registrate
						try {
							available = activityDAO.anyActivityRegistered(user.getEmail(), activityDate, start, finish);
							obj.put("available", available);
						} catch (SQLException e) {
							e.printStackTrace();
							return;
						} catch (JSONException e) {
							e.printStackTrace();
							return;
						}
						
						response.getWriter().write(obj.toString());
						return;
					}				
				}				
				else {																// Ricerca appuntamenti
					try {
						AppointmentDAO appointmentDAO = new AppointmentDAO();
						
						Collection <AppointmentBean> appointmentsList = appointmentDAO.doRetrieveAllByDate(null, user.getEmail(), activityDate, start, finish);
						if(appointmentsList!=null && !appointmentsList.isEmpty() ) {
							Iterator<?> it = appointmentsList.iterator();
														
							while(it.hasNext()) {
								AppointmentBean bean = (AppointmentBean)it.next();
								RequestBean req = requestDAO.doRetrieveById(bean.getRequestId());
								StudentBean student = studentDAO.doRetrieveByMail(req.getStudent());							
								
								obj = new JSONObject();
								obj.put("details", bean.getDetails());
								obj.put("firstName", student.getFirstName());
								obj.put("lastName", student.getLastName());
								ja.put(obj);
							}					
						}
						request.getSession().removeAttribute("appointmentsList");
						request.getSession().setAttribute("appointmentsList", appointmentsList);
					} catch (SQLException e) {							// Errore query
						response.sendRedirect("home.jsp");						
						return;
					} catch (JSONException e) {							// Errore parser json
						response.sendRedirect("home.jsp");						
						return;
					}
					response.getWriter().write(ja.toString());
					return;
				}
			}
			else
				return;
		}
				
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato nello script: 
																		// 1 = registrazione nuova attività
																		// 2 = modifica/cancellazione attività da parte di un tutor
																		// 3 = convalida/cancellazione attività da parte di un membro della Commissione
					
		if (flag == 1) {												// Registrazione nuova attività
			String category = request.getParameter("category");			// Dati Attività
			String dateString = request.getParameter("date");
			String startTime = request.getParameter("startTime");
			String finishTime = request.getParameter("finishTime");
			String description = request.getParameter("description");
			String tutorMail = user.getEmail();
			
			if(category.length() == 0) {
				throw new IllegalArgumentException("Selezionare la categoria");
			}
			
			if(dateString.length() == 0) {
				throw new IllegalArgumentException("Selezionare la data");
			}
			
			Date date = Date.valueOf(dateString);
			
			if(startTime.length() == 0) {
				throw new IllegalArgumentException("Inserire l’orario di inizio attivita");
			}
			
			int start = Utils.getTimeAsInt(startTime);
			
			if(start < 450 || start > 1320) {
				throw new IllegalArgumentException("Orario di inizio attivita non valido");
			}
			
			if(finishTime.length() == 0) {
				throw new IllegalArgumentException("Inserire l’orario di fine attivita");
			}
			
			int finish = Utils.getTimeAsInt(finishTime);
			
			if(finish < 450 || finish > 1320) {
				throw new IllegalArgumentException("Orario di fine attivita non valido");
			}
			
			if(finish < start) {
				throw new IllegalArgumentException("Orari inseriti non validi");
			}
			
			if(description.length() == 0 || description.length() > 240) {
				throw new IllegalArgumentException("Lunghezza commento non valida");
			}			
		
			float hours = (finish - start) / 60.f;
									
			ActivityTutorBean activityBean = new ActivityTutorBean();
			
			activityBean.setCategory(category);
			activityBean.setActivityDate(date);
			activityBean.setStartTime(start);
			activityBean.setFinishTime(finish);
			activityBean.setHours(hours);
			activityBean.setDetails(description);
			activityBean.setTutor(tutorMail);
			
			try {
				TutorBean tutor = tutorDAO.doRetrieveByMail(tutorMail);
				
				int registerId = tutor.getRegisterId();
				activityBean.setRegisterId(registerId);
				
				int activityId = activityDAO.doSave(activityBean);
							
				@SuppressWarnings("unchecked")
				Collection <AppointmentBean> appointmentsList = (Collection<AppointmentBean>) request.getSession(false).getAttribute("appointmentsList");
				
				if (appointmentsList != null && !appointmentsList.isEmpty()) {
					Iterator<?> it = appointmentsList.iterator();
					
					while(it.hasNext()) {
						AppointmentBean appointment = (AppointmentBean) it.next();
						ContainedInBean containedInBean = new ContainedInBean();
												
						containedInBean.setActivityId(activityId);
						containedInBean.setAppointmentId(appointment.getIdAppointment());
						
						containedInDAO.doSave(containedInBean);
					}				
				}
				
				request.removeAttribute("activitiesCollection");
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				obj.put("result", 1);
			} catch (SQLException e) {								// Errore nella convalida dell'attivita'.
				System.out.println(e.getMessage());
				try {
					obj.put("result", 2);			
				} catch (JSONException jsonexp) {					// Errore parser json					
				}
			} catch (JSONException jsonexp) {						// Errore parser json
			}
			finally {
				response.getWriter().write(obj.toString());
			}
			request.getSession().removeAttribute("appointmentsList");
		}
		
		else if (flag == 2) {											// Modifica/Cancellazione di un'attività da parte di un tutor
			String delete = request.getParameter("delete");
			
			if (delete.equals("false")) {								// Modifica attività
				String category = request.getParameter("category");		// Dati Attività
				String dateString = request.getParameter("date");
				String startTime = request.getParameter("startTime");
				String finishTime = request.getParameter("finishTime");
				String description = request.getParameter("description");
				int id = Integer.parseInt(request.getParameter("id"));
				String tutorMail = user.getEmail();
				
				if(category.length() == 0) {
					throw new IllegalArgumentException("Selezionare la categoria");
				}
				
				if(dateString.length() == 0) {
					throw new IllegalArgumentException("Selezionare la data");
				}
				
				Date date = Date.valueOf(dateString);
				
				if(startTime.length() == 0) {
					throw new IllegalArgumentException("Inserire l’orario di inizio attivita");
				}
				
				int start = Utils.getTimeAsInt(startTime);
				
				if(start < 450 || start > 1320) {
					throw new IllegalArgumentException("Orario di inizio attivita non valido");
				}
				
				if(finishTime.length() == 0) {
					throw new IllegalArgumentException("Inserire l’orario di fine attivita");
				}
				
				int finish = Utils.getTimeAsInt(finishTime);
				
				if(finish < 450 || finish > 1320) {
					throw new IllegalArgumentException("Orario di fine attivita non valido");
				}
				
				if(finish < start) {
					throw new IllegalArgumentException("Orari inseriti non validi");
				}
				
				if(description.length() == 0 || description.length() > 240) {
					throw new IllegalArgumentException("Lunghezza commento non valida");
				}
										
				float hours = (finish - start) / 60.f;
							
				ActivityTutorBean activityBean = new ActivityTutorBean();
				
				activityBean.setIdActivity(id);
				activityBean.setCategory(category);
				activityBean.setActivityDate(date);
				activityBean.setStartTime(start);
				activityBean.setFinishTime(finish);
				activityBean.setHours(hours);
				activityBean.setDetails(description);
				activityBean.setTutor(tutorMail);
				
				try {
					TutorBean tutor = tutorDAO.doRetrieveByMail(tutorMail);
					
					int registerId = tutor.getRegisterId();
					activityBean.setRegisterId(registerId);
					
					@SuppressWarnings("unchecked")
					Collection <AppointmentBean> appointmentsList = (Collection<AppointmentBean>) request.getSession(false).getAttribute("appointmentsList");
					
					if (appointmentsList != null && !appointmentsList.isEmpty()) {
						Iterator<?> it = appointmentsList.iterator();
						
						while(it.hasNext()) {
							AppointmentBean appointment = (AppointmentBean) it.next();
							ContainedInBean containedInBean = containedInDAO.doRetrieveByAppointmentId(appointment.getIdAppointment());
							
							//System.out.println("Ho trovato un appuntamento");
							
							if(containedInBean.getActivityId() == -1) {
								System.out.println("Ho trovato un containedInBean vuoto, appuntamento non ancora registrato.");
								containedInBean.setActivityId(id);
								containedInBean.setAppointmentId(appointment.getIdAppointment());
								
								containedInDAO.doSave(containedInBean);
							}					
						}				
					}
					
					activityDAO.doModify(activityBean);
					
					request.removeAttribute("activitiesCollection");
					
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					
					obj.put("result", 1);
				} catch (SQLException e) {								// Errore nella convalida dell'attivita'.
					System.out.println(e.getMessage());
					try {
						obj.put("result", 2);			
					} catch (JSONException jsonexp) {					// Errore parser json					
					}
				} catch (JSONException jsonexp) {						// Errore parser json
				}
				finally {
					response.getWriter().write(obj.toString());
				}
				request.getSession().removeAttribute("appointmentsList");
			}
			
			else {														// Cancellazione attività
				try {
					ActivityTutorBean activity = (ActivityTutorBean) request.getSession().getAttribute("activity");
					
					activityDAO.doDelete(activity);
					request.removeAttribute("activitiesCollection");
					request.getSession(false).removeAttribute("activity");
					
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					
					obj.put("result", 1);
				} catch (SQLException e) {								// Errore nella rimozione dell'attivita'.
					try {
						obj.put("result", 2);			
					} catch (JSONException jsonexp) {					// Errore parser json					
					}
				} catch (JSONException jsonexp) {						// Errore parser json
				}
				finally {
					response.getWriter().write(obj.toString());
				}				
				return;
			}				
		}
		
		else if (flag == 3) {											// Validazione/Rimozione di un'attività da parte di un membro della Commissione
			ValidatesDAO validatesDAO = new ValidatesDAO();
			
			String activityId = request.getParameter("id");
			int id = Integer.parseInt(activityId);
									
			String validate = request.getParameter("validate");
			
			if (validate != null && validate.equals("true")) {			// Validazione attivita'.
				try {					
					ValidatesBean bean = new ValidatesBean();
					bean.setActivityId(id);
					bean.setCommissionMember(user.getEmail());
					validatesDAO.doSave(bean);
					
					request.removeAttribute("activitiesCollection");
					request.getSession(false).setAttribute("Email", activityDAO.doRetrieveById(id).getTutor());
					
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					
					obj.put("result", 1);
				} catch (SQLException e) {								// Errore nella convalida dell'attivita'.
					try {
						obj.put("result", 2);			
					} catch (JSONException jsonexp) {					// Errore parser json					
					}
				} catch (JSONException jsonexp) {						// Errore parser json
				}
				finally {
					response.getWriter().write(obj.toString());
				}
				
				return;
			}
			if (validate != null && validate.equals("false")) {			// Rimozione attivita'.
				try {
					ActivityTutorBean activity = activityDAO.doRetrieveById(id);
					request.getSession(false).setAttribute("Email", activityDAO.doRetrieveById(id).getTutor());
					
					validatesDAO.doDelete(id);
					
					activityDAO.doDelete(activity);
					request.removeAttribute("activitiesCollection");
					
					request.getSession(false).removeAttribute("activity");
					request.getSession(false).setAttribute("delete", "true");
					
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					
					obj.put("result", 1);
				} catch (SQLException e) {								// Errore nella rimozione dell'attivita'.
					try {
						obj.put("result", 2);			
					} catch (JSONException jsonexp) {					// Errore parser json					
					}
				} catch (JSONException jsonexp) {						// Errore parser json
				}
				finally {
					response.getWriter().write(obj.toString());
				}
				
				return;
			}			
		}
		
		else {															// flag non valido.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
			return;
		}
     }
 }	
