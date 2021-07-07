package fr.eni.enchere.dal.Enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.ArticleVendu.ArticleVenduDAO;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAO;

public class EnchereDAOImpl implements EnchereDAO {
private static UtilisateurDAO daoU = FactoryDAO.getUtilisateurDAO();
private static ArticleVenduDAO daoA = FactoryDAO.getArticleVenduDAO();

	public String table = "CATEGORIES";
	private String INSERT = "INSERT INTO " + table + "(date_enchere,motant_enchere,no_article,no_utilisateur) VALUES (?,?,?,?)";
	private String SELECT = "SELECT * FROM " + table;
	// private String UPDATE = "UPDATE " + table
	// + " SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?,
	// code_postal=?, ville=?, mot_de_passe=?, credit=? WHERE administrateur=? ";
	private String DELETE = "DELETE * FROM " + table + " where no_enchere=?";

	@Override
	public void insert(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
			stmt.setInt(2, enchere.getMontant_Enchere());
			stmt.setInt(3, enchere.getUtilisateur().getNoUtilisateur());
			stmt.setInt(4, enchere.getArticleVendu().getNoArticle());
			
			
			int nb = stmt.executeUpdate();
			if (nb > 0) {
				ResultSet rsk = stmt.getGeneratedKeys();
				if (rsk.next()) {
					enchere.setNoEnchere(rsk.getInt(1));
				}
			}

			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	

	@Override
	public List<Enchere> getAll() {
		List<Enchere> resultat = new ArrayList<Enchere>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Enchere enchere = new Enchere();
				
				enchere.setDateEnchere(Timestamp.valueOf(rs.getString("date_enchere")).toLocalDateTime());
				enchere.setMontant_Enchere(rs.getInt("montant_enchere"));
				
				enchere.setUtilisateur(daoU.getById(Integer.parseInt(rs.getString("no_utilisateur"))));
				enchere.setArticleVendu(daoA.getById(Integer.parseInt(rs.getString("no_article"))));
				
				
				resultat.add(enchere);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultat;
	}

	@Override
	public void update(Enchere enchere) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(DELETE);
			stmt.setInt(1, enchere.getNoEnchere());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Probleme");
		}

	}
}