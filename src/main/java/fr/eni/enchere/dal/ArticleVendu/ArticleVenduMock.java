package fr.eni.enchere.dal.ArticleVendu;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;

public class ArticleVenduMock implements ArticleVenduDAO {
	private static List<ArticleVendu> lstArt = new ArrayList<>();
	

	@Override
	public void insert(ArticleVendu articleVendu) {
		lstArt.add(articleVendu);
	}

	@Override
	public List<ArticleVendu> getAll() {
		return lstArt;
	}

	@Override
	public void update(ArticleVendu articleVendu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ArticleVendu articleVendu) {
		lstArt.remove(articleVendu);
	}

	@Override
	public ArticleVendu getById(Integer integer) {
		// TODO getByID
		return null;
	}

	@Override
	public List<ArticleVendu> getByKey(String filtre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePrice(Integer noArticle, Integer new_montant) {
		// TODO Auto-generated method stub
		
	}

}
