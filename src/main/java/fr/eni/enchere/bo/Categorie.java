package fr.eni.enchere.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	/*
	 * class Categorie: 3 constructeur 
	 * noCategorie 
	 * libelle
	 * 
	 * avec une list d'article
	 */
private Integer noCategorie;
private String libelle;
private List<ArticleVendu> articles = new ArrayList<>();

public Categorie() {
	super();
}

public Categorie(String libelle, List<ArticleVendu> articles) {
	super();
	this.libelle = libelle;
	this.articles = articles;
}

public Categorie(Integer noCategorie, String libelle, List<ArticleVendu> articles) {
	super();
	this.noCategorie = noCategorie;
	this.libelle = libelle;
	this.articles = articles;
}

public Integer getNoCategorie() {
	return noCategorie;
}

public void setNoCategorie(Integer noCategorie) {
	this.noCategorie = noCategorie;
}

public String getLibelle() {
	return libelle;
}

public void setLibelle(String libelle) {
	this.libelle = libelle;
}

public List<ArticleVendu> getArticles() {
	return articles;
}

public void setArticles(List<ArticleVendu> articles) {
	this.articles = articles;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Categorie [noCategorie=");
	builder.append(noCategorie);
	builder.append(", libelle=");
	builder.append(libelle);
	builder.append(", articles=");
	builder.append(articles);
	builder.append("]");
	return builder.toString();
}

}
