package fr.eni.enchere.dal;

import java.util.List;

public interface DAO<T> {
	public void insert(T t);
	public List<T>getAll();
	public void update(T t);
	public void delete(T t);
	

}
