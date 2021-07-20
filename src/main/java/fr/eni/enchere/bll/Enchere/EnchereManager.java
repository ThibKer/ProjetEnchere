package fr.eni.enchere.bll.Enchere;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.Enchere;

public interface EnchereManager {
	
	public void addEnchere(Enchere enchere);
	public List<Enchere>getAllEncheres();
	
}
