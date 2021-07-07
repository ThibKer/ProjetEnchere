package fr.eni.enchere.bo;

import java.time.LocalDateTime;

public class Enchere {
	/*
	 * Class enchere :
	 * dateEnchere
	 * montant_Enchere
	 */
	
private LocalDateTime dateEnchere;
private Integer montant_Enchere;
private Integer noEnchere;
private Utilisateur utilisateur;
private ArticleVendu ArticleVendu;

public Enchere() {
	super();
}

public Enchere(LocalDateTime dateEnchere, Integer montant_Enchere, Utilisateur utilisateur,
		fr.eni.enchere.bo.ArticleVendu articleVendu) {
	super();
	this.dateEnchere = dateEnchere;
	this.montant_Enchere = montant_Enchere;
	this.utilisateur = utilisateur;
	ArticleVendu = articleVendu;
}

public Enchere(LocalDateTime dateEnchere, Integer montant_Enchere, Integer noEnchere, Utilisateur utilisateur,
		fr.eni.enchere.bo.ArticleVendu articleVendu) {
	super();
	this.dateEnchere = dateEnchere;
	this.montant_Enchere = montant_Enchere;
	this.noEnchere = noEnchere;
	this.utilisateur = utilisateur;
	ArticleVendu = articleVendu;
}

public LocalDateTime getDateEnchere() {
	return dateEnchere;
}

public void setDateEnchere(LocalDateTime dateEnchere) {
	this.dateEnchere = dateEnchere;
}

public Integer getMontant_Enchere() {
	return montant_Enchere;
}

public void setMontant_Enchere(Integer montant_Enchere) {
	this.montant_Enchere = montant_Enchere;
}

public Integer getNoEnchere() {
	return noEnchere;
}

public void setNoEnchere(Integer noEnchere) {
	this.noEnchere = noEnchere;
}

public Utilisateur getUtilisateur() {
	return utilisateur;
}

public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
}

public ArticleVendu getArticleVendu() {
	return ArticleVendu;
}

public void setArticleVendu(ArticleVendu articleVendu) {
	ArticleVendu = articleVendu;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Enchere [dateEnchere=");
	builder.append(dateEnchere);
	builder.append(", montant_Enchere=");
	builder.append(montant_Enchere);
	builder.append(", noEnchere=");
	builder.append(noEnchere);
	builder.append(", utilisateur=");
	builder.append(utilisateur);
	builder.append(", ArticleVendu=");
	builder.append(ArticleVendu);
	builder.append("]");
	return builder.toString();
}

}