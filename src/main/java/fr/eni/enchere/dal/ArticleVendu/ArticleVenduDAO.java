package fr.eni.enchere.dal.ArticleVendu;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.dal.DAO;

public interface ArticleVenduDAO extends DAO<ArticleVendu> {
	
	public void insert(ArticleVendu articleVendu);
	public List<ArticleVendu>getAll();
	public void update(ArticleVendu articleVendu);
	public void delete(ArticleVendu articleVendu);
	public ArticleVendu getById(Integer integer);
	public List<ArticleVendu> getByKey(String filtre);
	public void updatePrice(Integer noArticle, Integer new_montant);

}
