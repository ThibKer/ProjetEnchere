package fr.eni.enchere.dal.Enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.ArticleVendu.ArticleVenduDAO;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAO;

public class EnchereDAOImpl implements EnchereDAO {
	private static UtilisateurDAO daoU = FactoryDAO.getUtilisateurDAO();
	private static ArticleVenduDAO daoA = FactoryDAO.getArticleVenduDAO();
	
	public String table = "ENCHERES";
	private String INSERT = "INSERT INTO " + table + "(date_enchere,montant_enchere,no_article,no_utilisateur) VALUES (?,?,?,?)";
	private String SELECT = "SELECT * FROM " + table;
	private String UPDATE = "UPDATE " + table + " SET date_enchere=?,montant_enchere=? WHERE no_article=? AND no_utilisateur=?";
	private String EXIST = "SELECT * FROM " + table + " WHERE no_article=? AND no_utilisateur=?";
	private String DELETE = "DELETE * FROM " + table + " where no_enchere=?";

	@Override
	public void insert(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setInt(2, enchere.getMontant_Enchere());
			stmt.setInt(3, enchere.getArticleVendu().getNoArticle());
			stmt.setInt(4, enchere.getUtilisateur().getNoUtilisateur());		
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
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(UPDATE);
			stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setInt(2, enchere.getMontant_Enchere());
			stmt.setInt(3, enchere.getArticleVendu().getNoArticle());	
			stmt.setInt(4, enchere.getUtilisateur().getNoUtilisateur());	
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(DELETE);
			stmt.setInt(1, enchere.getNoEnchere());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Enchere> getByUserId(Integer id) {
		List<Enchere> resultat = new ArrayList<Enchere>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(
					"SELECT no_enchere, date_enchere, montant_enchere, A.no_article, nom_article, description, "
					        + "date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, U.no_utilisateur "
					        + "FROM " + table + " as E JOIN ARTICLES_VENDUS as A ON A.no_article = E.no_article " 
					        + "JOIN UTILISATEURS as U ON U.no_utilisateur = E.no_utilisateur WHERE E.no_utilisateur=" +id + ";"
					);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Utilisateur user = new Utilisateur();
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				ArticleVendu article = new ArticleVendu();	
				article.setNoArticle(rs.getInt("no_article"));				
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(Timestamp.valueOf(rs.getString("date_enchere")).toLocalDateTime());
				enchere.setMontant_Enchere(rs.getInt("montant_enchere"));
				enchere.setUtilisateur(user);
				enchere.setArticleVendu(article);
				resultat.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

	@Override
	public boolean checkIfExist(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(EXIST);
			stmt.setInt(1, enchere.getArticleVendu().getNoArticle());
			stmt.setInt(2, enchere.getUtilisateur().getNoUtilisateur());		
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}