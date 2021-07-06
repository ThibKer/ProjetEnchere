package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {
	
	public void insert(Utilisateur utilisateur);
	public List<Utilisateur>getAll();
	public void update(Utilisateur utilisateur);
	public void delete(Utilisateur utilisateur);

}
