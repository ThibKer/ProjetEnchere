package fr.eni.enchere.dal;

import fr.eni.enchere.dal.ArticleVendu.ArticleVenduDAO;
import fr.eni.enchere.dal.ArticleVendu.ArticleVenduMock;
import fr.eni.enchere.dal.Categorie.CategorieDAO;
import fr.eni.enchere.dal.Categorie.CategorieMock;
import fr.eni.enchere.dal.Enchere.EnchereDAO;
import fr.eni.enchere.dal.Enchere.EnchereMock;
import fr.eni.enchere.dal.Retrait.RetraitDAO;
import fr.eni.enchere.dal.Retrait.RetraitMock;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAO;
import fr.eni.enchere.dal.Utilisateur.UtilisateurMock;

public class FactoryDAO {
	public static CategorieDAO getCategorieDAO() {
		return new CategorieMock();
	}

	public static RetraitDAO getRetraitDAO() {
		return new RetraitMock();
	}

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurMock();
	}

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduMock();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereMock();
	}

}
