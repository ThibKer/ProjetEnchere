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
	public Utilisateur getByEmailPassword(String identifiant, String mdp);
	public Utilisateur getByPseudoPassword(String identifiant, String mdp);
	
	public boolean existPseudo(String pseudo);
	public boolean existEmail(String parameter);

}
