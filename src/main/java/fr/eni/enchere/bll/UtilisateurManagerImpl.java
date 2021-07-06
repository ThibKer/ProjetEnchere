package fr.eni.enchere.bll;

import java.util.Collections;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.dal.FactoryDAO;

public class UtilisateurManagerImpl implements UtilisateurManager {

	private static UtilisateurDAO dao =  FactoryDAO.getUtilisateurDAO();
	@Override
	public void addUtilisateur(Utilisateur utilisateur) {
		dao.insert(utilisateur);
	}

	@Override
	public List<Utilisateur> getAllUtilisateurs() {
		return dao.getAll();
	}
}
