package fr.eni.enchere.dal.ArticleVendu;

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
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ConnectionProvider;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.Categorie.CategorieDAO;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAO;



public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	private static UtilisateurDAO daoU = FactoryDAO.getUtilisateurDAO();
	private static CategorieDAO daoC = FactoryDAO.getCategorieDAO();	
	

	public String table = "ARTICLES_VENDUS";
	public String t_cate = "CATEGORIES";
	public String t_user = "UTILISATEURS";
	private String INSERT = "INSERT INTO " + table + "(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?)";
//	private String SELECT = "SELECT * FROM " + table;
	private String SELECT = "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente" 
			+ ",A.no_utilisateur,U.administrateur,U.pseudo,U.prenom,U.nom,U.email,U.credit,U.code_postal,U.rue,U.ville,U.telephone,A.no_categorie,C.libelle"
		    + " FROM " + table + " as A JOIN " + t_cate + " as C ON C.no_categorie = A.no_categorie JOIN "+ t_user + " as U ON U.no_utilisateur = A.no_utilisateur;";
			
	private String SELECT_BY_KEY = "SELECT * FROM " + table + " WHERE ((nom_article LIKE ?) OR (description LIKE ?))";
	// private String UPDATE = "UPDATE " + table
	// + " SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?,
	// code_postal=?, ville=?, mot_de_passe=?, credit=? WHERE administrateur=? ";
	private String DELETE = "DELETE * FROM " + table + " WHERE no_article=?";

	@Override
	public void insert(ArticleVendu articleVendu) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, articleVendu.getNomArticle());
			stmt.setString(2, articleVendu.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(articleVendu.getDateDebutEncheres()));
			stmt.setTimestamp(4, Timestamp.valueOf(articleVendu.getDateFinEncheres()));
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
				Utilisateur user = new Utilisateur();
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setCredit(rs.getInt("credit"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setAdministrateur(rs.getString("administrateur"));
				Categorie cate = new Categorie();	
				cate.setNoCategorie(rs.getInt("no_categorie"));
				cate.setLibelle(rs.getString("libelle"));
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEncheres(LocalDateTime.of(LocalDate.parse(rs.getString("date_debut_encheres")), LocalTime.now()));
				article.setDateFinEncheres(LocalDateTime.of(LocalDate.parse(rs.getString("date_fin_encheres")), LocalTime.now()));
				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));			
				article.setUtilisateur(user);
				article.setCategorie(cate);
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

	@Override
	public ArticleVendu getById(Integer integer) {
		// TODO getById
		return null;
	}

	@Override
	public List<ArticleVendu> getByKey(String filtre) {	
		String query = "'%"+filtre+"%'";
		System.out.println(query);
		List<ArticleVendu> resultat = new ArrayList<ArticleVendu>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente" 
					+ ",A.no_utilisateur,U.administrateur,U.pseudo,U.prenom,U.nom,U.email,U.credit,U.code_postal,U.rue,U.ville,U.telephone,A.no_categorie,C.libelle"
				    + " FROM " + table + " as A JOIN " + t_cate + " as C ON C.no_categorie = A.no_categorie JOIN "+ t_user + " as U ON U.no_utilisateur = A.no_utilisateur"
				    + " WHERE ((nom_article LIKE "+query+") OR (description LIKE "+query+"))"
			);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("RES FOUND");
				Utilisateur user = new Utilisateur();
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setCredit(rs.getInt("credit"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setAdministrateur(rs.getString("administrateur"));
				Categorie cate = new Categorie();	
				cate.setNoCategorie(rs.getInt("no_categorie"));
				cate.setLibelle(rs.getString("libelle"));
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEncheres(LocalDateTime.of(LocalDate.parse(rs.getString("date_debut_encheres")), LocalTime.now()));
				article.setDateFinEncheres(LocalDateTime.of(LocalDate.parse(rs.getString("date_fin_encheres")), LocalTime.now()));
				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));			
				article.setUtilisateur(user);
				article.setCategorie(cate);
				resultat.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}
}