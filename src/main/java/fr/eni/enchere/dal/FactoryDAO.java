package fr.eni.enchere.dal;

import fr.eni.enchere.dal.ArticleVendu.ArticleVenduDAO;
import fr.eni.enchere.dal.ArticleVendu.ArticleVenduDAOImpl;
import fr.eni.enchere.dal.Categorie.CategorieDAO;
import fr.eni.enchere.dal.Categorie.CategorieDAOImpl;
import fr.eni.enchere.dal.Enchere.EnchereDAO;
import fr.eni.enchere.dal.Enchere.EnchereDAOImpl;
import fr.eni.enchere.dal.Retrait.RetraitDAO;
import fr.eni.enchere.dal.Retrait.RetraitDAOImpl;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAO;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAOImpl;

public class FactoryDAO {
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOImpl();
//		return new CategorieMock();
	}

	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOImpl();
//		return new RetraitMock();
	}

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
//		return new UtilisateurMock();
	}

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
//		return new ArticleVenduMock();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOImpl();
//		return new EnchereMock();
	}

}
