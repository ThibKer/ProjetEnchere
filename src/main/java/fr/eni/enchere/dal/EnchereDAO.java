package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Enchere;

public interface EnchereDAO extends DAO<Enchere> {
	
	public void insert(Enchere enchere);
	public List<Enchere>getAll();
	public void update(Enchere enchere);
	public void delete(Enchere enchere);

}
