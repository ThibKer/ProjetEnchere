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


/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/")
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
		Object loggin = request.getServletContext().getAttribute("User");
		if(loggin != null) {
			System.out.println("quelqu'un est logger");
		}else {
			System.out.println("Personne n'est logger");
		}
		System.out.println("ca filtre !!");
		// pass the request along the filter chain
		
		
		 HttpServletRequest req = (HttpServletRequest) request;
		    HttpServletResponse resp = (HttpServletResponse) response;
		    String url = req.getRequestURI();

		    if(loggin != null) {
		        
		        resp.sendRedirect(req.getServletContext().getContextPath() + "/HomeServlet");          
		           
		        
		    } else {
		    	resp.sendRedirect(req.getServletContext().getContextPath() + "/HomeServlet");
		                
		   }
	      chain.doFilter(request, response);
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
