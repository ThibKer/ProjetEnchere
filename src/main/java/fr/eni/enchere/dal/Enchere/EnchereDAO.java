package fr.eni.enchere.dal.Enchere;

import java.util.List;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.DAO;

public interface EnchereDAO extends DAO<Enchere> {
	
	public void insert(Enchere enchere);
	public List<Enchere>getAll();
	public void update(Enchere enchere);
	public void delete(Enchere enchere);
	public List<Enchere> getByUserId(Integer userId);
	public boolean checkIfExist(Enchere enchere);

}
