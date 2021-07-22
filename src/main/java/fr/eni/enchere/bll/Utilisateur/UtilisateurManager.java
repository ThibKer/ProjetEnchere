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
	public boolean checkIfAvalableAndOk(HttpServletRequest request);
	public void updateUtilisateur(Utilisateur utilisateur);
	public void deleteUtilisateur(Integer noUtilisateur);
	public Utilisateur getUtilisateurById(Integer noUtilisateur);
	public void swapArticleBet(Integer noArticle, Integer montant_Enchere, Integer noUtilisateur,
			Integer montant_Enchere2, Integer noUtilisateur2);
	void newArticleBet(Integer noArticle, Integer new_montant, Integer new_user);
	
}
