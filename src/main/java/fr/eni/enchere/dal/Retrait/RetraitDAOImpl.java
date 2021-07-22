package fr.eni.enchere.dal.Retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.ArticleVendu.ArticleVenduDAO;

public class RetraitDAOImpl implements RetraitDAO {
	private static ArticleVenduDAO daoA = FactoryDAO.getArticleVenduDAO();
	
	public String table = "RETRAITS";
	private String INSERT = "INSERT INTO " + table + "(no_article, rue, code_postal, ville) VALUES (?,?,?,?)";
	private String SELECT = "SELECT * FROM " + table;
	private String DELETE = "DELETE * FROM " + table + " where no_article=?";

	@Override
	public void insert(Retrait retrait) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, retrait.getArticle().getNoArticle());
			stmt.setString(2, retrait.getRue());
			stmt.setString(3, retrait.getCode_postal());
			stmt.setString(4, retrait.getVille());
			stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Retrait> getAll() {
		List<Retrait> resultat = new ArrayList<Retrait>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Retrait retrait = new Retrait();		
				retrait.setArticle(daoA.getById(Integer.parseInt(rs.getString("no_article"))));				
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				resultat.add(retrait);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

	@Override
	public void update(Retrait retrait) {
		// UPDATE WAS NOT IMPLEMENTED FOR 'RETRAIT'
	}

	@Override
	public void delete(Retrait retrait) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(DELETE);
			stmt.setInt(1, retrait.getArticle().getNoArticle());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


