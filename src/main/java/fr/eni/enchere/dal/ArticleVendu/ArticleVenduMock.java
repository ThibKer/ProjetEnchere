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

}
