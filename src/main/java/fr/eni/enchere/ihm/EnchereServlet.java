package fr.eni.enchere.ihm;

import java.io.IOException;

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
import fr.eni.enchere.bll.Enchere.EnchereManager;
import fr.eni.enchere.bll.Enchere.EnchereManagerSingl;
import fr.eni.enchere.bll.Retrait.RetraitManager;
import fr.eni.enchere.bll.Retrait.RetraitManagerSingl;
import fr.eni.enchere.bll.Utilisateur.UtilisateurManager;
import fr.eni.enchere.bll.Utilisateur.UtilisateurManagerSingl;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.ihm.model.ModelLogged;

/**
 * Servlet implementation class EnchereServlet
 */
@WebServlet("/EnchereServlet")
public class EnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager = UtilisateurManagerSingl.getInstance();
	CategorieManager categorieManager = CategorieManagerSingl.getInstance();
	ArticleVenduManager articleManager = ArticleVenduManagerSingl.getInstance();
	RetraitManager retraitManager = RetraitManagerSingl.getInstance(); 
	EnchereManager enchereManager = EnchereManagerSingl.getInstance();  
	ModelLogged loggedUser = new ModelLogged();
	ArticleVendu model ;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnchereServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loggedUser = (ModelLogged) request.getSession().getAttribute("User");
		String proposition = request.getParameter("proposition");
		String target = request.getParameter("target");
		String bet = request.getParameter("bet");
		RequestDispatcher rd = null;
		if(target != null) {
			model = articleManager.getArticleById(Integer.parseInt(target));
			request.getSession().setAttribute("vente", model);
			rd = request.getRequestDispatcher("WEB-INF/detail_enchere.jsp");
		}
		if(bet != null) {
			if(proposition != null && proposition != "" && loggedUser != null) {
				model = (ArticleVendu) request.getSession().getAttribute("vente");	
				Utilisateur acheteur = new Utilisateur();
				acheteur.setNoUtilisateur(loggedUser.getNoUtilisateur());	
				Enchere enchere = new Enchere();
				enchere.setMontant_Enchere(Integer.parseInt(proposition));
				enchere.setUtilisateur(acheteur);
				enchere.setArticleVendu(model);
				if(enchereManager.valideEnchere(model, enchere) == true) {
					System.out.println(">> EnchValide");
					if(!enchereManager.checkIfExist(enchere)) {
						System.out.println(">> not exist");
						enchereManager.addEnchere(enchere);
						if(model.getEncheres().isEmpty()) {
							utilisateurManager.newArticleBet(
									model.getNoArticle(), 
									enchere.getMontant_Enchere(),
									acheteur.getNoUtilisateur()
							);
						}else {
							utilisateurManager.swapArticleBet(
								model.getNoArticle(),
								model.getEncheres().get(0).getMontant_Enchere(),
								model.getEncheres().get(0).getUtilisateur().getNoUtilisateur(),
								enchere.getMontant_Enchere(),
								acheteur.getNoUtilisateur()
							);
						}
					}else{
						utilisateurManager.updateArticleBet(
								model.getNoArticle(),
								model.getEncheres().get(0).getMontant_Enchere(),
								enchere.getMontant_Enchere(),
								acheteur.getNoUtilisateur()
						);
						enchereManager.updateEnchere(enchere);
					}		
				}
				model = articleManager.getArticleById(Integer.parseInt(target));
				request.getSession().setAttribute("vente", model);
				rd = request.getRequestDispatcher("HomeServlet");
			}else {
				//TODO error montant non renseignée
				rd = request.getRequestDispatcher("HomeServlet");
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
