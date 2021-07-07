package fr.eni.enchere.dal.ArticleVendu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.Categorie.CategorieDAO;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAO;



public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	private static UtilisateurDAO daoU = FactoryDAO.getUtilisateurDAO();
	private static CategorieDAO daoC = FactoryDAO.getCategorieDAO();	
	

	public String table = "ARTICLES_VENDUS";
	private String INSERT = "INSERT INTO " + table
			+ "(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?)";
	private String SELECT = "SELECT * FROM " + table;
	// private String UPDATE = "UPDATE " + table
	// + " SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?,
	// code_postal=?, ville=?, mot_de_passe=?, credit=? WHERE administrateur=? ";
	private String DELETE = "DELETE * FROM " + table + " where no_article=?";

	@Override
	public void insert(ArticleVendu articleVendu) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, articleVendu.getNomArticle());
			stmt.setString(2, articleVendu.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(articleVendu.getDateDebutEncheres()));
			stmt.setTimestamp(4, Timestamp.valueOf(articleVendu.getDateFineEncheres()));
			stmt.setInt(5, articleVendu.getMiseAPrix());
			stmt.setInt(6, articleVendu.getPrixVente());
			stmt.setInt(7, articleVendu.getUtilisateur().getNoUtilisateur());
			stmt.setInt(8, articleVendu.getCategorie().getNoCategorie());

			int nb = stmt.executeUpdate();
			if (nb > 0) {
				ResultSet rsk = stmt.getGeneratedKeys();
				if (rsk.next()) {
					articleVendu.setNoArticle(rsk.getInt(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<ArticleVendu> getAll() {
		List<ArticleVendu> resultat = new ArrayList<ArticleVendu>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEncheres(Timestamp.valueOf(rs.getString("date_debut_encheres")).toLocalDateTime());
				article.setDateFineEncheres(Timestamp.valueOf(rs.getString("date_fin_encheres")).toLocalDateTime());
				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				
				article.setUtilisateur(daoU.getById(Integer.parseInt(rs.getString("no_utilisateur"))));

				article.setCategorie(daoC.getById(Integer.parseInt(rs.getString("no_categorie"))));
				resultat.add(article);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultat;
	}

	@Override
	public void update(ArticleVendu articleVendu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ArticleVendu articleVendu) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(DELETE);
			stmt.setInt(1, articleVendu.getNoArticle());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Probleme");
		}

	}
}