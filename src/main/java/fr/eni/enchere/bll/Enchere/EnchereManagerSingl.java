package fr.eni.enchere.bll.Enchere;

public class EnchereManagerSingl {

	private static EnchereManager instance;
	
	public static EnchereManager getInstance() {
		if(instance == null) {
			instance = new EnchereManagerImpl();
		}
		return instance; 
		

	}
}
