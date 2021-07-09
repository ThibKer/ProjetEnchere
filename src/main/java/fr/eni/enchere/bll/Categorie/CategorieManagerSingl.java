package fr.eni.enchere.bll.Categorie;

public class CategorieManagerSingl {

	private static CategorieManager instance;
	
	public static CategorieManager getInstance() {
		if(instance == null) {
			instance = new CategorieManagerImpl();
		}
		return instance; 
		

	}
}
