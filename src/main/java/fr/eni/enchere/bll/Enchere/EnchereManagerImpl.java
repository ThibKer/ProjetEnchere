package fr.eni.enchere.bll.Enchere;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.Enchere.EnchereDAO;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAO;

public class EnchereManagerImpl implements EnchereManager {
	private static EnchereDAO dao =  FactoryDAO.getEnchereDAO();
	private static UtilisateurDAO daoU =  FactoryDAO.getUtilisateurDAO();
	
	@Override
	public void addEnchere(Enchere enchere) {
		dao.insert(enchere);
	}

	@Override
	public List<Enchere> getAllEncheres() {
		return dao.getAll();
	}

	@Override
	public List<Enchere> getEncheresByUtilisateur(Integer noUtilisateur) {
		return dao.getByUserId(noUtilisateur);
	}

	@Override
	public boolean valideEnchere(ArticleVendu model, Enchere enchere) {
		if( !model.getEncheres().isEmpty() ) {
			if(model.getEncheres().get(0).getMontant_Enchere() > enchere.getMontant_Enchere()){
				return false;
			}
		}
		Utilisateur user = daoU.getById(enchere.getUtilisateur().getNoUtilisateur());
		if(user.getCredit() < enchere.getMontant_Enchere()) {
			return false;
		}
		return true;
	}

	@Override
	public void updateEnchere(Enchere enchere) {
		dao.update(enchere);	
	}

	@Override
	public boolean checkIfExist(Enchere enchere) {
		return dao.checkIfExist(enchere);
	}


}
