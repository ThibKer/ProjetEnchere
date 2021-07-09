package fr.eni.enchere.dal.Utilisateur;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public class UtilisateurMock implements UtilisateurDAO {
	private static List<Utilisateur> lstUtilisateur = new ArrayList<>();

	@Override
	public void insert(Utilisateur utilisateur) {
		System.out.println("insert mock");
		lstUtilisateur.add(utilisateur);
	}

	@Override
	public List<Utilisateur> getAll() {
		return lstUtilisateur;
	}

	@Override
	public void update(Utilisateur utilisateur) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Utilisateur utilisateur) {
		lstUtilisateur.remove(utilisateur);
	}

	@Override
	public Utilisateur getById(Integer integer) {
		// TODO getById
		return null;
	}

	@Override
	public Utilisateur getByEmailPassword(String identifiant, String mdp) {
		for (Utilisateur u : lstUtilisateur) {
			if(u.getEmail().equals(identifiant) && u.getMotDePasse().equals(mdp)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public Utilisateur getByPseudoPassword(String identifiant, String mdp) {
		System.out.println("getPseudo mock");
		for (Utilisateur u : lstUtilisateur) {
			if(u.getPseudo().equals(identifiant) && u.getMotDePasse().equals(mdp)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public boolean existPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return false;
	}

}
