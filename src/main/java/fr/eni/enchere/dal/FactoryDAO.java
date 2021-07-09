package fr.eni.enchere.dal;

import fr.eni.enchere.dal.ArticleVendu.ArticleVenduDAO;
import fr.eni.enchere.dal.ArticleVendu.ArticleVenduMock;
import fr.eni.enchere.dal.Categorie.CategorieDAO;
import fr.eni.enchere.dal.Categorie.CategorieDAOImpl;
import fr.eni.enchere.dal.Enchere.EnchereDAO;
import fr.eni.enchere.dal.Enchere.EnchereMock;
import fr.eni.enchere.dal.Retrait.RetraitDAO;
import fr.eni.enchere.dal.Retrait.RetraitMock;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAO;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAOImpl;

public class FactoryDAO {
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOImpl();
	}

	public static RetraitDAO getRetraitDAO() {
		return new RetraitMock();
	}

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduMock();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereMock();
	}

}
