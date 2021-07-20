package fr.eni.enchere.bll.Categorie;

import java.util.List;

import fr.eni.enchere.bo.Categorie;

public interface CategorieManager {
	
	public void addCategorie(Categorie categorie);
	public List<Categorie>getAllCategories();
	public Categorie getCategorieByLibelle(String categorie);
	
}
