package fr.eni.enchere.bll.Enchere;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.Enchere.EnchereDAO;

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
