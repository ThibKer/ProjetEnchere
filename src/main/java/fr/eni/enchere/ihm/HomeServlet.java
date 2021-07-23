package fr.eni.enchere.ihm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import fr.eni.enchere.bll.Enchere.EnchereManager;
import fr.eni.enchere.bll.Enchere.EnchereManagerSingl;
import fr.eni.enchere.bll.Utilisateur.UtilisateurManager;
import fr.eni.enchere.bll.Utilisateur.UtilisateurManagerSingl;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.ihm.model.ModelLogged;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      
	UtilisateurManager utilisateurManager = UtilisateurManagerSingl.getInstance();
	CategorieManager categorieManager = CategorieManagerSingl.getInstance();
	ArticleVenduManager articleVenduManager = ArticleVenduManagerSingl.getInstance();
	EnchereManager enchereManager = EnchereManagerSingl.getInstance();	
	ModelLogged loggedUser = new ModelLogged();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
    }

//    @Override
//    public void init() throws ServletException {
//    	this.getServletContext().setAttribute("locale", Locale.ENGLISH);
//    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().setAttribute("categories", categorieManager.getAllCategories());
		loggedUser = (ModelLogged) request.getSession().getAttribute("User");
		List<ArticleVendu> displayList = articleVenduManager.getAllArticlesVendus();	
		String search = request.getParameter("search");
		String filtre = request.getParameter("filtre");
		String categorie = request.getParameter("categorie");
		// radio button
		String achats = request.getParameter("achats");
		String ventes = request.getParameter("ventes");
		// check button
		String achatsOpen = request.getParameter("achats-open");
		String achatsMy = request.getParameter("achats-my");
		String achatsWin = request.getParameter("achats-win");
		String ventesEc = request.getParameter("ventes-ec");
		String ventesNd = request.getParameter("ventes-nd");
		String ventesEnd = request.getParameter("ventes-end");
System.out.println("->>>>>>> " + search);						
		if(search != null) {
			displayList = articleVenduManager.getAllArticlesVendus();
			if(filtre != null) {
				displayList = articleVenduManager.findByKey(filtre);
			}else {
				displayList = articleVenduManager.findByKey(filtre);
			}
			if(categorie != null && categorie != "") {
				List<ArticleVendu> cateList = new ArrayList<ArticleVendu>();
				for (ArticleVendu articleVendu : displayList) {
					if(articleVendu.getCategorie().getLibelle().equals(categorie)) {
						System.out.println("-> la");		
						cateList.add(articleVendu);	
					}	
				}displayList = cateList;
			}if(achats != null) {
System.out.println(achatsOpen + " -> naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ");
				List<ArticleVendu> achatsList = new ArrayList<ArticleVendu>();
				achatsList = articleVenduManager.getAllAchatsForUserId(loggedUser.getNoUtilisateur());
				List<Enchere> encheres = enchereManager.getEncheresByUtilisateur(loggedUser.getNoUtilisateur());
				if(achatsOpen != null) {
//					achatsList = articleVenduManager.getAchatsByState(achatsList, "EC");
					for (ArticleVendu item : displayList) {
						// TODO gerer depuis la liste des achat de 'l'USER
						if(item.getDateDebutEncheres().isBefore(LocalDateTime.now()) 
							&& item.getDateFinEncheres().isAfter(LocalDateTime.now())
							&& item.getUtilisateur().getNoUtilisateur() != loggedUser.getNoUtilisateur()) {
							if(encheres.isEmpty()) {	
									achatsList = displayList;
							}
							else {
								for (Enchere enchere : encheres) {
									if(enchere.getArticleVendu().getNoArticle() != item.getNoArticle()) {
										achatsList.add(item);
									}
								}	
							}
							
						}
					}
				}
				if(achatsMy != null) {
//					achatsList = articleVenduManager.getAchatsByState(achatsList, "EC");
					for (ArticleVendu item : displayList) {
						// TODO gerer depuis la liste des achat de 'l'USER
						if(item.getDateDebutEncheres().isBefore(LocalDateTime.now()) 
							&& item.getDateFinEncheres().isAfter(LocalDateTime.now())
							&& item.getUtilisateur().getNoUtilisateur() != loggedUser.getNoUtilisateur()) {
							for (Enchere enchere : encheres) {
								if(enchere.getArticleVendu().getNoArticle() == item.getNoArticle()) {
									achatsList.add(item);
								}
							}	
						}
					}
				}
				if(achatsWin != null) {
					for (ArticleVendu item : displayList) {
						// TODO gerer depuis la liste des achat de 'l'USER
						if(item.getDateDebutEncheres().isBefore(LocalDateTime.now())
							&& item.getUtilisateur().getNoUtilisateur() != loggedUser.getNoUtilisateur()) {
							for (Enchere enchere : encheres) {
								if(enchere.getArticleVendu().getNoArticle() == item.getNoArticle()) {
									achatsList.add(item);
								}
							}	
						}
					}
				}
				if( !achatsList.isEmpty() ) {
					displayList = achatsList;
				}else {
					displayList = articleVenduManager.getAllArticlesVendus();
				}
			}
			if(ventes != null) {
				List<ArticleVendu> ventesList = new ArrayList<ArticleVendu>();
				ventesList = articleVenduManager.getAllVentesForUserId(loggedUser.getNoUtilisateur());
				if(ventesEc != null) {
					ventesList = articleVenduManager.getAchatsByState(ventesList, "EC");
				}
				if(ventesNd != null) {
					ventesList = articleVenduManager.getAchatsByState(ventesList, "ND");
				}
				if(ventesEnd != null) {
					ventesList = articleVenduManager.getAchatsByState(ventesList, "CLOSE");
				}				
				displayList = ventesList;
			}
			request.getSession().setAttribute("liste", displayList);
			System.out.println(displayList);
		}else{
			displayList = articleVenduManager.getAllArticlesVendus();
			request.getSession().setAttribute("liste", displayList);
			System.out.println(displayList);
		}
		request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
		
		
//		if(test != null) {
//			request.getRequestDispatcher("WEB-INF/user_creation.jsp").forward(request, response);
//		}else {
//			request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
