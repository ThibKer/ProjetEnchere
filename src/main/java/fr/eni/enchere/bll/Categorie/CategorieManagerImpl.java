package fr.eni.enchere.bll.Categorie;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.Categorie.CategorieDAO;

public class CategorieManagerImpl implements CategorieManager {

	private static CategorieDAO dao =  FactoryDAO.getCategorieDAO();
	@Override
	public void addCategorie(Categorie categorie) {
		dao.insert(categorie);
	}

	@Override
	public List<Categorie> getAllCategories() {
		return dao.getAll();
	}

	@Override
	public Categorie getCategorieByLibelle(String categorie) {
		
		return dao.getByLibelle(categorie);
	}
}
