package fr.eni.enchere.ihm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleVendu.ArticleVenduManager;
import fr.eni.enchere.bll.ArticleVendu.ArticleVenduManagerSingl;
import fr.eni.enchere.bll.Categorie.CategorieManager;
import fr.eni.enchere.bll.Categorie.CategorieManagerSingl;
import fr.eni.enchere.bll.Utilisateur.UtilisateurManager;
import fr.eni.enchere.bll.Utilisateur.UtilisateurManagerSingl;
import fr.eni.enchere.bo.ArticleVendu;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UtilisateurManager utilisateurManager = UtilisateurManagerSingl.getInstance();
	CategorieManager categorieManager = CategorieManagerSingl.getInstance();
	ArticleVenduManager articleVenduManager = ArticleVenduManagerSingl.getInstance();
//	CategorieManager categorieManager = CategorieManagerSingl.getInstance();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
    }

//    @Override
//    public void init() throws ServletException {
//    	this.getServletContext().setAttribute("locale", Locale.FRENCH);
//    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().setAttribute("categories", categorieManager.getAllCategories());
		List<ArticleVendu> displayList;
		String test = request.getParameter("test");
		String search = request.getParameter("search");
		String filtre = request.getParameter("filtre");
		System.out.println(test);
		System.out.println(search);		
		System.out.println(filtre);		
		
		
		if(search != null) {
			displayList = articleVenduManager.findByKey(filtre);
			request.getSession().setAttribute("liste", displayList);
			System.out.println(displayList);
		}else {
			displayList = articleVenduManager.getAllArticlesVendus();
			request.getSession().setAttribute("liste", displayList);
			System.out.println(displayList);
		}
		
		if(test != null) {
			request.getRequestDispatcher("WEB-INF/user_creation.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);	

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
