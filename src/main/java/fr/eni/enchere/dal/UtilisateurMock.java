package fr.eni.enchere.dal;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public class UtilisateurMock implements UtilisateurDAO {
	private static List<Utilisateur> lstUtilisateur = new ArrayList<>();

	@Override
	public void insert(Utilisateur utilisateur) {
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

}
