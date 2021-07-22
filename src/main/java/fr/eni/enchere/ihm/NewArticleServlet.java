package fr.eni.enchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleVendu.ArticleVenduManager;
import fr.eni.enchere.bll.ArticleVendu.ArticleVenduManagerSingl;
import fr.eni.enchere.bll.Categorie.CategorieManager;
import fr.eni.enchere.bll.Categorie.CategorieManagerSingl;
import fr.eni.enchere.bll.Retrait.RetraitManager;
import fr.eni.enchere.bll.Retrait.RetraitManagerSingl;
import fr.eni.enchere.bll.Utilisateur.UtilisateurManager;
import fr.eni.enchere.bll.Utilisateur.UtilisateurManagerSingl;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.ihm.model.ModelLogged;

/**
 * Servlet implementation class NewArticleServlet
 */
@WebServlet("/NewArticleServlet")
public class NewArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;     
	UtilisateurManager utilisateurManager = UtilisateurManagerSingl.getInstance();
	CategorieManager categorieManager = CategorieManagerSingl.getInstance();
	ArticleVenduManager articleManager = ArticleVenduManagerSingl.getInstance();
	RetraitManager retraitManager = RetraitManagerSingl.getInstance();
	ModelLogged loggedUser;   

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
		loggedUser = (ModelLogged) request.getSession().getAttribute("User");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/new_article.jsp");;
		ArticleVendu articleVendu = new ArticleVendu();
		Retrait retrait = new Retrait();	
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
//		String photo = request.getParameter("photo");
		String prix = request.getParameter("prix");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String rue = request.getParameter("rue");
		String postal = request.getParameter("postal");
		String ville = request.getParameter("ville");
		String save = request.getParameter("save");
		String cancel = request.getParameter("cancel");
		if(cancel != null) {
			response.sendRedirect(request.getHeader("Referer"));
		}
		if(save != null) {
			if(articleManager.checkIfValid(request)) {
				articleVendu.setNomArticle(article);
				articleVendu.setDescription(description);
				articleVendu.setDateDebutEncheres(LocalDate.parse(start).atTime(LocalTime.now()));
				articleVendu.setDateFinEncheres(LocalDate.parse(end).atTime(LocalTime.now()));
				articleVendu.setMiseAPrix(Integer.parseInt(prix));
				articleVendu.setPrixVente(0);
				articleVendu.setUtilisateur(
						utilisateurManager.getUtilisateurById(loggedUser.getNoUtilisateur())
				);
				articleVendu.setCategorie(
						categorieManager.getCategorieByLibelle(categorie)
				);
				articleManager.addArticleVendu(articleVendu);	
				retrait.setArticle(articleVendu);
				retrait.setRue(rue);
				retrait.setCode_postal(postal);
				retrait.setVille(ville);
				retraitManager.addRetrait(retrait);
				rd = request.getRequestDispatcher("HomeServlet");	
			}
			else {
				// TODO erreur saisi dans le formulair
			}
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
