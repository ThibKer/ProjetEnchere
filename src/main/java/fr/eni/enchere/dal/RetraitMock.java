package fr.eni.enchere.dal;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Retrait;

public class RetraitMock implements RetraitDAO {
	private static List<Retrait> lstRetrait = new ArrayList<>();

	@Override
	public void insert(Retrait retrait) {
		lstRetrait.add(retrait);
	}

	@Override
	public List<Retrait> getAll() {
		return lstRetrait;
	}

	@Override
	public void update(Retrait retrait) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Retrait retrait) {
		lstRetrait.remove(retrait);
	}

}
