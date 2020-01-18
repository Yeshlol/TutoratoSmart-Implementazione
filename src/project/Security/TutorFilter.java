package project.Security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.Model.UserBean;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/AdminFilter")
public class TutorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TutorFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest requestTmp = (HttpServletRequest) request;
		HttpServletResponse responseTmp = (HttpServletResponse) response;
		HttpSession session = requestTmp.getSession(false);
		UserBean user = (UserBean) session.getAttribute("user");
		if(user.getRole() == 2)
			chain.doFilter(request, response);
		else {
			responseTmp.sendRedirect(requestTmp.getContextPath()+"/refuseAccess.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}
