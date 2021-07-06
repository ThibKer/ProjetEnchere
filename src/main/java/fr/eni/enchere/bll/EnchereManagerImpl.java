package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.EnchereDAO;
import fr.eni.enchere.dal.FactoryDAO;

public class EnchereManagerImpl implements EnchereManager {

	private static EnchereDAO dao =  FactoryDAO.getEnchereDAO();
	@Override
	public void addEnchere(Enchere enchere) {
		dao.insert(enchere);
	}

	@Override
	public List<Enchere> getAllEncheres() {
		return dao.getAll();
	}
}
