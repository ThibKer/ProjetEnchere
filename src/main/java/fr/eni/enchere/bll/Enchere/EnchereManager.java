package fr.eni.enchere.bll.Enchere;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

public interface EnchereManager {
	
	public void addEnchere(Enchere enchere);
	public List<Enchere> getAllEncheres();
	public List<Enchere> getEncheresByUtilisateur(Integer noUtilisateur);
	public boolean valideEnchere(ArticleVendu model, Enchere enchere);
	public void updateEnchere(Enchere enchere);
	public boolean checkIfExist(Enchere enchere);
	
}
