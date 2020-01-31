package project.Control.Security;

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

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {
    public AuthFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest requestTmp = (HttpServletRequest) request;
		HttpServletResponse responseTmp = (HttpServletResponse) response;
		HttpSession session = requestTmp.getSession();
		
		if(session!=null && session.getAttribute("user")!=null) {
			chain.doFilter(request, response);
		}else {			
			session.setAttribute("msg", "Effettua il login per accedere a questa pagina.");
			
			responseTmp.sendRedirect(requestTmp.getContextPath()+"/View/login.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}