package fr.eni.enchere.dal.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.ConnectionProvider;

public class CategorieDAOImpl implements CategorieDAO {
	
	public String table = "CATEGORIES";
	private String INSERT = "INSERT INTO " + table + "(libelle) VALUES (?)";
	private String SELECT = "SELECT * FROM " + table;
	// private String UPDATE = "UPDATE " + table
	// + " SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?,
	// code_postal=?, ville=?, mot_de_passe=?, credit=? WHERE administrateur=? ";
	private String DELETE = "DELETE * FROM " + table + " where no_categorie=?";

	@Override
	public void insert(Categorie categorie) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, categorie.getLibelle());
			

			int nb = stmt.executeUpdate();
			if (nb > 0) {
				ResultSet rsk = stmt.getGeneratedKeys();
				if (rsk.next()) {
					categorie.setNoCategorie(rsk.getInt(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Categorie> getAll() {
		List<Categorie> resultat = new ArrayList<Categorie>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setLibelle(rs.getString("libelle"));
				
				resultat.add(categorie);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultat;
	}

	@Override
	public void update(Categorie categorie) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Categorie categorie) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(DELETE);
			stmt.setInt(1, categorie.getNoCategorie());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Probleme");
		}

	}

	

	@Override
	public Categorie getById(Integer integer) {
		// TODO getById
		return null;
	}

}
