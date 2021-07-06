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

public Enchere() {
	super();
}

public Enchere(LocalDateTime dateEnchere, Integer montant_Enchere) {
	super();
	this.dateEnchere = dateEnchere;
	this.montant_Enchere = montant_Enchere;
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

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Enchere [dateEnchere=");
	builder.append(dateEnchere);
	builder.append(", montant_Enchere=");
	builder.append(montant_Enchere);
	builder.append("]");
	return builder.toString();
}


}
