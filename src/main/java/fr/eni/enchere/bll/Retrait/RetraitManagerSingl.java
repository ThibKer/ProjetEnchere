package fr.eni.enchere.bll.Retrait;

public class RetraitManagerSingl {

	private static RetraitManager instance;
	
	public static RetraitManager getInstance() {
		if(instance == null) {
			instance = new RetraitManagerImpl();
		}
		return instance; 
		

	}
}
