package fr.eni.enchere.dal;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Enchere;

public class EnchereMock implements EnchereDAO {
	private static List<Enchere> lstEnch = new ArrayList<>();

	@Override
	public void insert(Enchere enchere) {
		lstEnch.add(enchere);
	}

	@Override
	public List<Enchere> getAll() {
		return lstEnch;
	}

	@Override
	public void update(Enchere enchere) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Enchere enchere) {
		lstEnch.remove(enchere);
		
	}

}
