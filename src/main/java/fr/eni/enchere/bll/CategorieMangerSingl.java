package fr.eni.enchere.bll;

public class CategorieMangerSingl {

	private static CategorieManager instance;
	
	public static CategorieManager getInstance() {
		if(instance == null) {
			instance = new CategorieManagerImpl();
		}
		return instance; 
		

	}
}
