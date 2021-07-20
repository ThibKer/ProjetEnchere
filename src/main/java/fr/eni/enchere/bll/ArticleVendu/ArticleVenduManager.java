package fr.eni.enchere.bll.ArticleVendu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.ArticleVendu;

public interface ArticleVenduManager {
	
	public void addArticleVendu(ArticleVendu articleVendu);
	public List<ArticleVendu>getAllArticlesVendus();
	public boolean checkIfValid(HttpServletRequest request);
	public List<ArticleVendu> findByKey(String filtre);
	
}
