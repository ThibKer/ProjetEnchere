package fr.eni.enchere.dal.Categorie;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.DAO;

public interface CategorieDAO extends DAO<Categorie> {
	
	public void insert(Categorie categorie);
	public List<Categorie>getAll();
	public void update(Categorie categorie);
	public void delete(Categorie categorie);
	
	public Categorie getById(Integer integer);

}
