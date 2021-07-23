package fr.eni.enchere.bll.ArticleVendu;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.ArticleVendu.ArticleVenduDAO;

public class ArticleVenduManagerImpl implements ArticleVenduManager {

	private static ArticleVenduDAO dao =  FactoryDAO.getArticleVenduDAO();
	@Override
	public void addArticleVendu(ArticleVendu articleVendu) {
		dao.insert(articleVendu);
	}

	@Override
	public List<ArticleVendu> getAllArticlesVendus() {
		return dao.getAll();
	}
	
	@Override
	public boolean checkIfValid(HttpServletRequest request) {
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
//		String photo = request.getParameter("photo");
		String prix = request.getParameter("prix");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if("".equals(article) || article == null) {
			return false;
		}
		if("".equals(description) || description == null) {
			return false;
		}
		if("".equals(categorie) || categorie == null) {
			return false;
		} 
		if("".equals(prix) || prix == null) {
			return false;
		}
		if("".equals(start) || start == null) {
			return false;
		}
		if("".equals(end) || end == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<ArticleVendu> findByKey(String filtre) {
		return dao.getByKey(filtre);
	}

	@Override
	public ArticleVendu getArticleById(Integer articleId) {
		return dao.getById(articleId);
	}

	@Override
	public List<ArticleVendu> getAllAchatsForUserId(Integer noUtilisateur) {
		return dao.getAllAchatsByUserId(noUtilisateur);
	}

	@Override
	public List<ArticleVendu> getAllVentesForUserId(Integer noUtilisateur) {
		return dao.getAllVentessByUserId(noUtilisateur);
	}

	@Override
	public List<ArticleVendu> getVentesByState(List<ArticleVendu> achatsList, String test) {
		List<ArticleVendu> tmp = new ArrayList<ArticleVendu>();
		for (ArticleVendu article : achatsList) {
			if(test.equals(article.getEtatVente())) {
				tmp.add(article);
			}
		}
		return tmp;
	}
}
