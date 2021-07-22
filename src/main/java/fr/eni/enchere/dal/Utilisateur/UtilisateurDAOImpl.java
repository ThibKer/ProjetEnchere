package fr.eni.enchere.dal.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ConnectionProvider;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	public String table = "UTILISATEURS";
	private String INSERT = "INSERT INTO " + table + "(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private String SELECT = "SELECT * FROM " + table;
	private String UPDATE = "UPDATE " + table + " SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur=? ";
	private String DELETE = "DELETE FROM " + table + " WHERE no_utilisateur=?";
	private String SELECT_ID = "SELECT * FROM " + table + " WHERE no_utilisateur=?";
	private String SELECT_EMAIL_MDP = "SELECT * FROM " + table + " WHERE email=? AND mot_de_passe=?";
	private String SELECT_PSEUDO_MDP = "SELECT * FROM " + table + " WHERE pseudo=? AND mot_de_passe=?";
	private String SELECT_PSEUDO = "SELECT * FROM " + table + " WHERE pseudo=?";
	private String SELECT_EMAIL = "SELECT * FROM " + table + " WHERE email=?";

	@Override
	public void insert(Utilisateur utilisateur) {
		System.out.println("->>>>INSERT");
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setString(11, utilisateur.getAdministrateur());
			int nb = stmt.executeUpdate();
			if (nb > 0) {
				ResultSet rsk = stmt.getGeneratedKeys();
				if (rsk.next()) {
					utilisateur.setNoUtilisateur(rsk.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Utilisateur> getAll() {
		List<Utilisateur> resultat = new ArrayList<Utilisateur>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getString("administrateur"));		
				resultat.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

    @Override
    public void update(Utilisateur utilisateur) {  
        try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(UPDATE);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setString(11, utilisateur.getAdministrateur());
			stmt.setInt(12, utilisateur.getNoUtilisateur());   
			stmt.executeUpdate();	
//            System.out.println("User with id " + utilisateur.getNoUtilisateur() + " was updated in DB with following details: " + utilisateur.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

	@Override
	public void delete(Utilisateur utilisateur) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(DELETE);
			stmt.setInt(1, utilisateur.getNoUtilisateur());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Utilisateur getById(Integer integer) {
		Utilisateur res = new Utilisateur();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(SELECT_ID);
			stmt.setString(1, integer.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {	
				res.setPseudo(rs.getString("pseudo"));
				res.setNom(rs.getString("nom"));
				res.setPrenom(rs.getString("prenom"));
				res.setEmail(rs.getString("email"));
				res.setTelephone(rs.getString("telephone"));
				res.setRue(rs.getString("rue"));
				res.setCodePostal(rs.getString("code_postal"));
				res.setVille(rs.getString("ville"));
				res.setMotDePasse(rs.getString("mot_de_passe"));
				res.setCredit(rs.getInt("credit"));
				res.setAdministrateur(rs.getString("administrateur"));
				res.setNoUtilisateur(rs.getInt("no_utilisateur"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Utilisateur getByEmailPassword(String identifiant, String mdp) {
		Utilisateur res = new Utilisateur();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(SELECT_EMAIL_MDP);
			stmt.setString(1, identifiant);
			stmt.setString(2, mdp);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {	
				res.setPseudo(rs.getString("pseudo"));
				res.setNom(rs.getString("nom"));
				res.setPrenom(rs.getString("prenom"));
				res.setEmail(rs.getString("email"));
				res.setTelephone(rs.getString("telephone"));
				res.setRue(rs.getString("rue"));
				res.setCodePostal(rs.getString("code_postal"));
				res.setVille(rs.getString("ville"));
				res.setMotDePasse(rs.getString("mot_de_passe"));
				res.setCredit(rs.getInt("credit"));
				res.setAdministrateur(rs.getString("administrateur"));
				res.setNoUtilisateur(rs.getInt("no_utilisateur"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Utilisateur getByPseudoPassword(String identifiant, String mdp) {
		Utilisateur res = new Utilisateur();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(SELECT_PSEUDO_MDP);
			stmt.setString(1, identifiant);
			stmt.setString(2, mdp);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {	
				res.setPseudo(rs.getString("pseudo"));
				res.setNom(rs.getString("nom"));
				res.setPrenom(rs.getString("prenom"));
				res.setEmail(rs.getString("email"));
				res.setTelephone(rs.getString("telephone"));
				res.setRue(rs.getString("rue"));
				res.setCodePostal(rs.getString("code_postal"));
				res.setVille(rs.getString("ville"));
				res.setMotDePasse(rs.getString("mot_de_passe"));
				res.setCredit(rs.getInt("credit"));
				res.setAdministrateur(rs.getString("administrateur"));
				res.setNoUtilisateur(rs.getInt("no_utilisateur"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean existPseudo(String pseudo) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(SELECT_PSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {	
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean existEmail(String email) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(SELECT_EMAIL);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {	
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
