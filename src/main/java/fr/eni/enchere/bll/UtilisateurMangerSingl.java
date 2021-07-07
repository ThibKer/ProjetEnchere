package fr.eni.enchere.bll;

public class UtilisateurMangerSingl {

	private static UtilisateurManager instance;
	
	public static UtilisateurManager getInstance() {
		if(instance == null) {
			instance = new UtilisateurManagerImpl();
		}
		return instance; 
	}
}
