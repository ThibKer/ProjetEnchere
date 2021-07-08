package fr.eni.enchere.bll.Utilisateur;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurManager {
	
	public void addUtilisateur(Utilisateur utilisateur);
	public List<Utilisateur>getAllUtilisateurs();
	
}
