package fr.eni.enchere.dal;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Categorie;

public class CategorieMock implements CategorieDAO {
	private static List<Categorie> lstCat = new ArrayList<>();

	@Override
	public void insert(Categorie categorie) {
		lstCat.add(categorie);
	}

	@Override
	public List<Categorie> getAll() {
		return lstCat;
	}

	@Override
	public void update(Categorie categorie) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Categorie categorie) {
		lstCat.remove(categorie);
	}

}
