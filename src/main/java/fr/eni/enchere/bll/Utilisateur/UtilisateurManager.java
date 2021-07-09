package fr.eni.enchere.bll.Utilisateur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurManager {
	
	public void addUtilisateur(Utilisateur utilisateur);
	public List<Utilisateur>getAllUtilisateurs();
	
	public Utilisateur getUtilisateurByFields(String identifiant, String mdp);
	public Utilisateur createUtilisateur(Utilisateur utilisateur);
	public boolean checkIfValid(HttpServletRequest request);
	
}
