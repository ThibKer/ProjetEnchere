package fr.eni.enchere.bll.ArticleVendu;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;

public interface ArticleVenduManager {
	
	public void addArticleVendu(ArticleVendu articleVendu);
	public List<ArticleVendu>getAllArticlesVendus();
	
}
