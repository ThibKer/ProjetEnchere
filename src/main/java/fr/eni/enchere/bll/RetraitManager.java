package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Retrait;

public interface RetraitManager {
	
	public void addRetrait(Retrait retrait);
	public List<Retrait>getAllRetraits();
	
}
