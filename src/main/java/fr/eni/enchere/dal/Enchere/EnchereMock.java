package fr.eni.enchere.dal.Enchere;

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
		// inactif
	}

	@Override
	public void delete(Enchere enchere) {
		lstEnch.remove(enchere);
		
	}

	@Override
	public List<Enchere> getByUserId(Integer userId) {
		// inactif
		return null;
	}

	@Override
	public boolean checkIfExist(Enchere enchere) {
		// inactif
		return false;
	}



}
