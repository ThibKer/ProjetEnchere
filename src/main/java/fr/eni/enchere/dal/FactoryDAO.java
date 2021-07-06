package fr.eni.enchere.dal;



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
