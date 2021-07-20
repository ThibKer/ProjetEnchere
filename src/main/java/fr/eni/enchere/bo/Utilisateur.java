package fr.eni.enchere.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	/*
	 * class utilisateur : 3 constructeur 
	 * noUtilisateur 
	 * pseudo 
	 * nom 
	 * prenom 
	 * email
	 * telephone 
	 * rue 
	 * codePostal 
	 * ville 
	 * motDePasse 
	 * credit 
	 * administrateur
	 * 
	 * avec une liste d'article et enchere
	 * 
	 */
	private Integer noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private Integer credit;
	private String administrateur = "";
	private List<ArticleVendu> articlesVendus;
	private List<Enchere> encheres;

	public Utilisateur() {
		this.credit = 100;
		this.articlesVendus = new ArrayList<>();
		this.encheres = new ArrayList<>();
	}

	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, Integer credit, String administrateur) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.articlesVendus = new ArrayList<>();
		this.encheres = new ArrayList<>();
	}

	public Utilisateur(Integer noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, Integer credit, String administrateur) {
		this( pseudo,  nom,  prenom,  email,  telephone,
				 rue,  codePostal,  ville, motDePasse, credit, administrateur);
		this.noUtilisateur = noUtilisateur;
		
	}

	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(String administrateur) {
		this.administrateur = administrateur;
	}

	public List<ArticleVendu> getArticlesVendus() {
		return articlesVendus;
	}

	public void setArticlesVendus(List<ArticleVendu> articlesVendus) {
		this.articlesVendus = articlesVendus;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Utilisateur [noUtilisateur=");
		builder.append(noUtilisateur);
		builder.append(", pseudo=");
		builder.append(pseudo);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", rue=");
		builder.append(rue);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append(", motDePasse=");
		builder.append(motDePasse);
		builder.append(", credit=");
		builder.append(credit);
		builder.append(", administrateur=");
		builder.append(administrateur);
		builder.append(", articlesVendus=");
		builder.append(articlesVendus);
		builder.append(", encheres=");
		builder.append(encheres);
		builder.append("]");
		return builder.toString();
	}

}