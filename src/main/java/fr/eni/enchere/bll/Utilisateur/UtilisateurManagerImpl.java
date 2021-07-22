package fr.eni.enchere.bll.Utilisateur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.FactoryDAO;
import fr.eni.enchere.dal.ArticleVendu.ArticleVenduDAO;
import fr.eni.enchere.dal.Utilisateur.UtilisateurDAO;

public class UtilisateurManagerImpl implements UtilisateurManager {
	private static UtilisateurDAO dao =  FactoryDAO.getUtilisateurDAO();
	private static ArticleVenduDAO daoA =  FactoryDAO.getArticleVenduDAO();

	@Override
	public void addUtilisateur(Utilisateur utilisateur) {
		dao.insert(utilisateur);
	}

	@Override
	public List<Utilisateur> getAllUtilisateurs() {
		return dao.getAll();
	}

	@Override
	public Utilisateur getUtilisateurByFields(String identifiant, String mdp) {
		Utilisateur utilisateur = new Utilisateur();
		
		System.out.println("mana impl");

//			utilisateur = dao.getByEmailPassword(identifiant, mdp);
		
		if(identifiant.contains(".")) {
			utilisateur = dao.getByEmailPassword(identifiant, mdp);
		}else {
			utilisateur = dao.getByPseudoPassword(identifiant, mdp);
		}
		return utilisateur;
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur utilisateur) {
		addUtilisateur(utilisateur);
		return getUtilisateurByFields(utilisateur.getPseudo(),utilisateur.getMotDePasse());
	}

	@Override
	public boolean checkIfValid(HttpServletRequest request) {
		if(!dao.existPseudo(request.getParameter("pseudo"))) {
			return false;
		}
		if(!request.getParameter("mdp").equals(request.getParameter("mdp2"))) {
			return false;
		}
		return true;
	}

	@Override
	public boolean checkIfAvalableAndOk(HttpServletRequest request) {
		System.out.println("gdshjgfsjhgdsjhf ___> / " +request.getParameter("email"));
		if(!request.getParameter("pseudo").matches("[a-zA-Z0-9]+")) {
			return false;
		}
		if(dao.existPseudo(request.getParameter("pseudo"))) {
			return false;
		}
		if(!request.getParameter("mdp").equals(request.getParameter("mdp2"))) {
			return false;
		}
		
		if(dao.existEmail(request.getParameter("email"))) {
			return false;
		}
		
//			String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
//	        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
//	        java.util.regex.Matcher m = p.matcher(request.getParameter("email"));
//	        return m.matches();
		return true;
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) {
		dao.update(utilisateur);
	}

	@Override
	public void deleteUtilisateur(Integer noUtilisateur) {	
		dao.delete( dao.getById(noUtilisateur) );		
	}

	@Override
	public Utilisateur getUtilisateurById(Integer noUtilisateur) {
		return dao.getById(noUtilisateur);
	}




	@Override
	public void swapArticleBet(Integer noArticle, Integer old_montant, Integer old_user,
			Integer new_montant, Integer new_user) {
		// TODO Auto-generated method stub
		Utilisateur old = dao.getById(old_user);
		Utilisateur current = dao.getById(new_user);
		old.setCredit( old.getCredit() + old_montant );
		current.setCredit( current.getCredit() - new_montant );
			
		dao.update(old);
		dao.update(current);
		daoA.updatePrice(noArticle, new_montant);
	}
	@Override
	public void newArticleBet(Integer noArticle,Integer new_montant, Integer new_user) {
		// TODO Auto-generated method stub
		Utilisateur current = dao.getById(new_user);
		current.setCredit( current.getCredit() - new_montant );
			
		dao.update(current);
		daoA.updatePrice(noArticle, new_montant);
	}}
