package fr.eni.enchere.ihm;

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
 * Servlet Filter implementation class UserFilter
 */
@WebFilter(servletNames = {"NewArticleServlet", "DeconnectionServlet", "ProfilServlet"})
public class UserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserFilter() {
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
		// TODO Auto-generated method stub
		// place your code here
	
		// pass the request along the filter chain
		
//		
//		 HttpServletRequest req = (HttpServletRequest) request;
//		    HttpServletResponse res = (HttpServletResponse) response;
//		    String url = req.getRequestURI();
//
//		    if(loggin != null) {
//		        
//		        res.sendRedirect(req.getServletContext().getContextPath() + "/HomeServlet");          
//		           
//		        
//		    } else {
//		    	res.sendRedirect(req.getServletContext().getContextPath() + "/HomeServlet");
//		                
//		   }
//	      chain.doFilter(request, response);
//	      
//	      
	      HttpServletRequest requ = (HttpServletRequest) request;
	        HttpServletResponse resp = (HttpServletResponse) response;
	        HttpSession session = requ.getSession(false);
	        Object loggin = request.getServletContext().getAttribute("User");

	        if (session == null || loggin == null) {
				System.out.println("Personne n'est logger");
	            resp.sendRedirect(requ.getContextPath() + "/HomeServlet"); // No logged-in user found, so redirect to login page.
	        } else {
	        	System.out.println("quelqu'un est logger");
	            chain.doFilter(requ, resp); // Logged-in user found, so just continue request.
	        }
	      
	      // call next filter in the chain : let j_security_check authenticate 
	      // user
	     
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
