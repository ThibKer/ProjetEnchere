package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Retrait;

public interface RetraitDAO extends DAO<Retrait> {
	
	public void insert(Retrait retrait);
	public List<Retrait>getAll();
	public void update(Retrait retrait);
	public void delete(Retrait retrait);

}
