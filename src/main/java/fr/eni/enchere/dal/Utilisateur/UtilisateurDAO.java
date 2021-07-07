package fr.eni.enchere.dal.Utilisateur;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAO;

public interface UtilisateurDAO extends DAO<Utilisateur> {
	
	public void insert(Utilisateur utilisateur);
	public List<Utilisateur>getAll();
	public void update(Utilisateur utilisateur);
	public void delete(Utilisateur utilisateur);
	
	public Utilisateur getById(Integer integer);

}
