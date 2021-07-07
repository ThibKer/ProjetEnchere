package fr.eni.enchere.dal.Retrait;

import java.util.List;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.dal.DAO;

public interface RetraitDAO extends DAO<Retrait> {
	
	public void insert(Retrait retrait);
	public List<Retrait>getAll();
	public void update(Retrait retrait);
	public void delete(Retrait retrait);

}
