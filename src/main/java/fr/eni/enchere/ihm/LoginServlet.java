package fr.eni.enchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.Utilisateur.UtilisateurManager;
import fr.eni.enchere.bll.Utilisateur.UtilisateurManagerSingl;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.ihm.model.ModelLogged;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager managerUtilisateur = UtilisateurManagerSingl.getInstance();
    ModelLogged loggedUser = new ModelLogged(); 
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
//    	this.getServletContext().setAttribute("locale", Locale.ENGLISH);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object loggin = request.getSession().getAttribute("User");
		String go = request.getParameter("go");	
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		RequestDispatcher rd;
		if(loggin != null && go != null) {
			rd = request.getRequestDispatcher("WEB-INF/home.jsp");	
		}
		if(identifiant != null && mdp != null) {
			Utilisateur userTest = managerUtilisateur.getUtilisateurByFields(identifiant, mdp);
			if(userTest.getPseudo() != null) {
				System.out.println("iciiiiiiiiii");
				loggedUser.setNoUtilisateur( userTest.getNoUtilisateur() );
				loggedUser.setPseudo( userTest.getPseudo() );
				loggedUser.setNom( userTest.getNom() );
				loggedUser.setPrenom( userTest.getPrenom( ));
				loggedUser.setEmail( userTest.getEmail() );
				loggedUser.setTelephone( userTest.getTelephone() );
				loggedUser.setRue( userTest.getRue() );
				loggedUser.setCodePostal( userTest.getCodePostal() );
				loggedUser.setVille( userTest.getVille() );
				loggedUser.setMotDePasse( userTest.getMotDePasse() );
				loggedUser.setCredit( userTest.getCredit() );
				loggedUser.setAdministrateur( userTest.getAdministrateur() );
				request.getSession().setAttribute("User", loggedUser);
				rd = request.getRequestDispatcher("WEB-INF/home.jsp");
			}
			else {
				// TODO err pas d'utilisateur correspondant
				rd = request.getRequestDispatcher("WEB-INF/login.jsp");
			}
		}
		else {
			// TODO probleme de saisi
			rd = request.getRequestDispatcher("WEB-INF/login.jsp");
		}		
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
