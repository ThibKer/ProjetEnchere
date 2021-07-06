package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Categorie;

public interface CategorieDAO extends DAO<Categorie> {
	
	public void insert(Categorie categorie);
	public List<Categorie>getAll();
	public void update(Categorie categorie);
	public void delete(Categorie categorie);

}
