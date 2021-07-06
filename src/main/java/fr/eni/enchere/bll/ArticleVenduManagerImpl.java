package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.dal.ArticleVenduDAO;
import fr.eni.enchere.dal.FactoryDAO;

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
}
