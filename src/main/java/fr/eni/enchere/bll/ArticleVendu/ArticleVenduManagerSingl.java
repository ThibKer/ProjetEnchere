package fr.eni.enchere.bll.ArticleVendu;

public class ArticleVenduManagerSingl {

	private static ArticleVenduManager instance;
	
	public static ArticleVenduManager getInstance() {
		if(instance == null) {
			instance = new ArticleVenduManagerImpl();
		}
		return instance; 
		

	}
}
