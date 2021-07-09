package fr.eni.enchere.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.Categorie.CategorieManager;
import fr.eni.enchere.bll.Categorie.CategorieManagerSingl;

/**
 * Servlet implementation class NewArticleServlet
 */
@WebServlet("/NewArticleServlet")
public class NewArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CategorieManager categorieManager = CategorieManagerSingl.getInstance();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewArticleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().setAttribute("categories", categorieManager.getAllCategories());
			
		String save = request.getParameter("save");
		String cancel = request.getParameter("cancel");
		
		if(cancel != null) {
			response.sendRedirect(request.getHeader("Referer"));
		}
		if(save != null) {
			request.getRequestDispatcher("WEB-INF/new_article.jsp").forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
