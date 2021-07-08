package fr.eni.enchere.bll.Retrait;

import java.util.List;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.Retrait.RetraitDAO;

public class RetraitManagerImpl implements RetraitManager {

	private static RetraitDAO dao =  FactoryDAO.getRetraitDAO();
	@Override
	public void addRetrait(Retrait retrait) {
		dao.insert(retrait);
	}

	@Override
	public List<Retrait> getAllRetraits() {
		return dao.getAll();
	}
}
