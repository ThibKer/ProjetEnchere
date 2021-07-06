package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;

public interface ArticleVenduDAO extends DAO<ArticleVendu> {
	
	public void insert(ArticleVendu articleVendu);
	public List<ArticleVendu>getAll();
	public void update(ArticleVendu articleVendu);
	public void delete(ArticleVendu articleVendu);

}
