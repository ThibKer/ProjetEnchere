package fr.eni.enchere.bll.ArticleVendu;

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
		System.out.println("lalal " + article);
		System.out.println(description);
		System.out.println(categorie);
		System.out.println(prix);
		System.out.println(start);
		System.out.println(end);
		if("".equals(article) || article == null) {
			System.out.println(article);
			return false;
		}
		if("".equals(description) || description == null) {
			System.out.println(description);
			return false;
		}
		if("".equals(categorie) || categorie == null) {
			System.out.println(categorie);
			return false;
		} 
		if("".equals(prix) || prix == null) {
			System.out.println(prix);
			return false;
		}
		if("".equals(start) || start == null) {
			System.out.println(start);
			return false;
		}
		if("".equals(end) || end == null) {
			System.out.println(end);
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
}
