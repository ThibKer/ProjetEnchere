package fr.eni.enchere.dal.ArticleVendu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ConnectionProvider;



public class ArticleVenduDAOImpl implements ArticleVenduDAO {

	public String table = "ARTICLES_VENDUS";
	public String t_cate = "CATEGORIES";
	public String t_user = "UTILISATEURS";
	public String t_ench = "ENCHERES";	
	private String INSERT = "INSERT INTO " + table + "(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?)";
	private String SELECT = "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente" 
			+ ",A.no_utilisateur,U.administrateur,U.pseudo,U.prenom,U.nom,U.email,U.credit,U.code_postal,U.rue,U.ville,U.telephone,A.no_categorie,C.libelle"
		    + " FROM " + table + " as A JOIN " + t_cate + " as C ON C.no_categorie = A.no_categorie JOIN "+ t_user + " as U ON U.no_utilisateur = A.no_utilisateur;";
	private String UPDATE_PRICE = "UPDATE " + table + " SET prix_vente=? WHERE no_article=?"; 
	private String UPDATE = "UPDATE " + table + " SET nom_article=?,description=?,date_debut_encheres=?,date_fin_encheres=?"
			+ ",prix_initial=?,no_categorie=? WHERE no_article=?";
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
				article.setDateDebutEncheres(Timestamp.valueOf(rs.getString("date_debut_encheres")).toLocalDateTime());
				article.setDateFinEncheres(Timestamp.valueOf(rs.getString("date_fin_encheres")).toLocalDateTime());
				if(article.getDateDebutEncheres().isAfter(LocalDateTime.now())) {
					article.setEtatVente("ND");
				}
				if(article.getDateFinEncheres().isBefore(LocalDateTime.now())) {
					article.setEtatVente("CLOSE");
				}
				if(article.getDateDebutEncheres().isBefore(LocalDateTime.now()) && article.getDateFinEncheres().isAfter(LocalDateTime.now())) {
					article.setEtatVente("EC");
				}
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
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(UPDATE);
			stmt.setString(1, articleVendu.getNomArticle());
			stmt.setString(2, articleVendu.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(articleVendu.getDateDebutEncheres()));
			stmt.setTimestamp(4, Timestamp.valueOf(articleVendu.getDateFinEncheres()));
			stmt.setInt(5, articleVendu.getMiseAPrix());;
			stmt.setInt(6, articleVendu.getCategorie().getNoCategorie());
			stmt.setInt(7, articleVendu.getNoArticle());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(ArticleVendu articleVendu) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(DELETE);
			stmt.setInt(1, articleVendu.getNoArticle());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArticleVendu getById(Integer integer) {
		Utilisateur vendeur = new Utilisateur();
		Utilisateur acheteur = new Utilisateur();
		Categorie categorie = new Categorie();	
		ArticleVendu article = new ArticleVendu();
		Enchere enchere = new Enchere();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("SELECT A.no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente" 
					+ ",A.no_utilisateur,U.administrateur,U.pseudo,U.prenom,U.nom,U.email,U.credit,U.code_postal,U.rue,U.ville,U.telephone,A.no_categorie,C.libelle"
					+ ",E.no_utilisateur as ench_no_utilisateur ,Ubis.pseudo as ench_pseudo ,no_enchere, date_enchere, montant_enchere"
				    + " FROM " + table + " as A FULL OUTER JOIN " + t_cate + " as C ON C.no_categorie = A.no_categorie FULL OUTER JOIN "+ t_user + " as U ON U.no_utilisateur = A.no_utilisateur"
				    + " FULL OUTER JOIN " + t_ench + " as E ON E.no_article = A.no_article FULL OUTER JOIN " + t_user + " as Ubis ON E.no_utilisateur = Ubis.no_utilisateur"
				    + " WHERE A.no_article=? ORDER BY montant_enchere ASC"
			);
			stmt.setInt(1, integer);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				vendeur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				vendeur.setPseudo(rs.getString("pseudo"));
				vendeur.setNom(rs.getString("nom"));
				vendeur.setPrenom(rs.getString("prenom"));
				vendeur.setEmail(rs.getString("email"));
				vendeur.setCredit(rs.getInt("credit"));
				vendeur.setTelephone(rs.getString("telephone"));
				vendeur.setRue(rs.getString("rue"));
				vendeur.setCodePostal(rs.getString("code_postal"));
				vendeur.setVille(rs.getString("ville"));
				vendeur.setAdministrateur(rs.getString("administrateur"));
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));	
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEncheres(Timestamp.valueOf(rs.getString("date_debut_encheres")).toLocalDateTime());
				article.setDateFinEncheres(Timestamp.valueOf(rs.getString("date_fin_encheres")).toLocalDateTime());
				if(article.getDateDebutEncheres().isAfter(LocalDateTime.now())) {
					article.setEtatVente("ND");
				}
				if(article.getDateFinEncheres().isBefore(LocalDateTime.now())) {
					article.setEtatVente("CLOSE");
				}
				if(article.getDateDebutEncheres().isBefore(LocalDateTime.now()) && article.getDateFinEncheres().isAfter(LocalDateTime.now())) {
					article.setEtatVente("EC");
				}
				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));			
				article.setUtilisateur(vendeur);
				article.setCategorie(categorie);	
				if (rs.getInt("ench_no_utilisateur") != 0) {
					acheteur.setNoUtilisateur(rs.getInt("ench_no_utilisateur"));// works for Java 1.5+
					acheteur.setNoUtilisateur(rs.getInt("ench_no_utilisateur"));
					acheteur.setPseudo(rs.getString("ench_pseudo"));		
					enchere.setNoEnchere(rs.getInt("no_enchere"));
					enchere.setDateEnchere(Timestamp.valueOf(rs.getString("date_enchere")).toLocalDateTime());
					enchere.setMontant_Enchere(rs.getInt("montant_enchere"));
					enchere.setUtilisateur(acheteur);
					article.addEnchere(enchere);
				}
			}
			while (rs.next()) {
				if (rs.getInt("ench_no_utilisateur") != 0) {
					enchere.setNoEnchere(rs.getInt("no_enchere"));
					enchere.setDateEnchere(Timestamp.valueOf(rs.getString("date_enchere")).toLocalDateTime());
					enchere.setMontant_Enchere(rs.getInt("montant_enchere"));
					enchere.setUtilisateur(acheteur);
					article.addEnchere(enchere);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	@Override
	public List<ArticleVendu> getByKey(String filtre) {	
		String query = "'%"+filtre+"%'";
		List<ArticleVendu> resultat = new ArrayList<ArticleVendu>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente" 
					+ ",A.no_utilisateur,U.administrateur,U.pseudo,U.prenom,U.nom,U.email,U.credit,U.code_postal,U.rue,U.ville,U.telephone,A.no_categorie,C.libelle"
				    + " FROM " + table + " as A JOIN " + t_cate + " as C ON C.no_categorie = A.no_categorie JOIN "+ t_user + " as U ON U.no_utilisateur = A.no_utilisateur"
				    + " WHERE ((nom_article LIKE "+query+") OR (description LIKE "+query+"))"
			);
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
				article.setDateDebutEncheres(Timestamp.valueOf(rs.getString("date_debut_encheres")).toLocalDateTime());
				article.setDateFinEncheres(Timestamp.valueOf(rs.getString("date_fin_encheres")).toLocalDateTime());
				if(article.getDateDebutEncheres().isAfter(LocalDateTime.now())) {
					article.setEtatVente("ND");
				}
				if(article.getDateFinEncheres().isBefore(LocalDateTime.now())) {
					article.setEtatVente("CLOSE");
				}
				if(article.getDateDebutEncheres().isBefore(LocalDateTime.now()) && article.getDateFinEncheres().isAfter(LocalDateTime.now())) {
					article.setEtatVente("EC");
				}
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
	public void updatePrice(Integer noArticle, Integer new_montant) {	
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(UPDATE_PRICE);
			stmt.setInt(1, new_montant);
			stmt.setInt(2, noArticle);	
			stmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ArticleVendu> getAllAchatsByUserId(Integer noUtilisateur) {
		// TODO Auto-generated method stub
		List<ArticleVendu> res = new ArrayList<ArticleVendu>();
		return res;
	}

	@Override
	public List<ArticleVendu> getAllVentessByUserId(Integer noUtilisateur) {
		// TODO Auto-generated method stub
		List<ArticleVendu> res = new ArrayList<ArticleVendu>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente" 
					+ ",A.no_utilisateur,U.administrateur,U.pseudo,U.prenom,U.nom,U.email,U.credit,U.code_postal,U.rue,U.ville,U.telephone,A.no_categorie,C.libelle"
				    + " FROM " + table + " as A JOIN " + t_cate + " as C ON C.no_categorie = A.no_categorie JOIN "+ t_user + " as U ON U.no_utilisateur = A.no_utilisateur"
				    + " WHERE A.no_utilisateur=?"
			);
			stmt.setInt(1, noUtilisateur);
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
				article.setDateDebutEncheres(Timestamp.valueOf(rs.getString("date_debut_encheres")).toLocalDateTime());
				article.setDateFinEncheres(Timestamp.valueOf(rs.getString("date_fin_encheres")).toLocalDateTime());
				if(article.getDateDebutEncheres().isAfter(LocalDateTime.now())) {
					article.setEtatVente("ND");
				}
				if(article.getDateFinEncheres().isBefore(LocalDateTime.now())) {
					article.setEtatVente("CLOSE");
				}
				if(article.getDateDebutEncheres().isBefore(LocalDateTime.now()) && article.getDateFinEncheres().isAfter(LocalDateTime.now())) {
					article.setEtatVente("EC");
				}
				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));			
				article.setUtilisateur(user);
				article.setCategorie(cate);
				res.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}