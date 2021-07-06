package fr.eni.enchere.bll;

public class RetraitMangerSingl {

	private static RetraitManager instance;
	
	public static RetraitManager getInstance() {
		if(instance == null) {
			instance = new RetraitManagerImpl();
		}
		return instance; 
		

	}
}
