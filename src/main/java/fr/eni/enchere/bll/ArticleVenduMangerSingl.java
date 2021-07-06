package fr.eni.enchere.bll;

public class ArticleVenduMangerSingl {

	private static ArticleVenduManager instance;
	
	public static ArticleVenduManager getInstance() {
		if(instance == null) {
			instance = new ArticleVenduManagerImpl();
		}
		return instance; 
		

	}
}
