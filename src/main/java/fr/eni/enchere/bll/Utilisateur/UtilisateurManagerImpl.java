package fr.eni.enchere.bll.Utilisateur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAO;

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

	@Override
	public Utilisateur getUtilisateurByFields(String identifiant, String mdp) {
		Utilisateur utilisateur = new Utilisateur();
		
		System.out.println("mana impl");

//			utilisateur = dao.getByEmailPassword(identifiant, mdp);
		
		if(identifiant.contains(".")) {
			utilisateur = dao.getByEmailPassword(identifiant, mdp);
		}else {
			utilisateur = dao.getByPseudoPassword(identifiant, mdp);
		}
		return utilisateur;
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur utilisateur) {
		addUtilisateur(utilisateur);
		return getUtilisateurByFields(utilisateur.getPseudo(),utilisateur.getMotDePasse());
	}

	@Override
	public boolean checkIfValid(HttpServletRequest request) {
		if(!dao.existPseudo(request.getParameter("pseudo"))) {
			return false;
		}
		if(!request.getParameter("mdp").equals(request.getParameter("mdp2"))) {
			return false;
		}
		return true;
	}
}
