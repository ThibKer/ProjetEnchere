package fr.eni.enchere.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu {
	/*
	 * class Article vendu : 3 constructeur 
	 * noArticle 
	 * nomArticle 
	 * description
	 * dateDebutEncheres 
	 * dateFinEncheres 
	 * miseAPrix 
	 * prixVente 
	 * etatVente
	 * 
	 * avec un objet utilisateur,catégorie et retrait et une liste enchere
	 * 
	 */
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private Integer miseAPrix;
	private Integer prixVente;
	private String etatVente;
	private Utilisateur utilisateur;
	private Categorie categorie;
	private Retrait retrait;
	private List<Enchere> Encheres = new ArrayList<>();

	public ArticleVendu() {
		super();
	}

	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFineEncheres, Integer miseAPrix, Integer prixVente, String etatVente,
			Utilisateur utilisateur, Categorie categorie, Retrait retrait, List<Enchere> encheres) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFineEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
		Encheres = encheres;
	}

	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFineEncheres, Integer miseAPrix, Integer prixVente, String etatVente,
			Utilisateur utilisateur, Categorie categorie, Retrait retrait, List<Enchere> encheres) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFineEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
		Encheres = encheres;
	}

	public Integer getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDateTime dateFineEncheres) {
		this.dateFinEncheres = dateFineEncheres;
	}

	public Integer getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public Integer getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public List<Enchere> getEncheres() {
		return Encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		Encheres = encheres;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleVendu [noArticle=");
		builder.append(noArticle);
		builder.append(", nomArticle=");
		builder.append(nomArticle);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateDebutEncheres=");
		builder.append(dateDebutEncheres);
		builder.append(", dateFineEncheres=");
		builder.append(dateFinEncheres);
		builder.append(", miseAPrix=");
		builder.append(miseAPrix);
		builder.append(", prixVente=");
		builder.append(prixVente);
		builder.append(", etatVente=");
		builder.append(etatVente);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append(", categorie=");
		builder.append(categorie);
		builder.append(", retrait=");
		builder.append(retrait);
		builder.append(", Encheres=");
		builder.append(Encheres);
		builder.append("]");
		return builder.toString();
	}

}
