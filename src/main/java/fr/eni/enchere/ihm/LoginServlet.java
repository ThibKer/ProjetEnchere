package fr.eni.enchere.ihm;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.Utilisateur.UtilisateurManager;
import fr.eni.enchere.bll.Utilisateur.UtilisateurMangerSingl;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.ihm.model.ModelLogged;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UtilisateurManager managerUtilisateur = UtilisateurMangerSingl.getInstance();
    ModelLogged loggedUser = new ModelLogged(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	this.getServletContext().setAttribute("locale", Locale.ENGLISH);
    	this.getServletContext().setAttribute("User", "some");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object loggin = this.getServletContext().getAttribute("User");
		String go = request.getParameter("go");
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		
		
		System.out.println(identifiant);
		System.out.println(mdp);
		System.out.println(go);
		System.out.println(loggin);
		if(loggin != null && go != null) {
			System.out.println("laaaaaa");
			request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);	
		}
		
		Utilisateur userTest = managerUtilisateur.getUtilisateurByFields(identifiant, mdp);
		if(userTest != null) {
			System.out.println("iciiiiiiiiii");
			loggedUser.setNoUtilisateur( userTest.getNoUtilisateur() );
			loggedUser.setPseudo( userTest.getPseudo() );
			loggedUser.setNom( userTest.getNom() );
			loggedUser.setPrenom( userTest.getPrenom() );
			loggedUser.setEmail( userTest.getEmail() );
			loggedUser.setTelephone( userTest.getTelephone() );
			loggedUser.setRue( userTest.getRue() );
			loggedUser.setCodePostal( userTest.getCodePostal() );
			loggedUser.setVille( userTest.getVille() );
			loggedUser.setMotDePasse( userTest.getMotDePasse() );
			loggedUser.setCredit( userTest.getCredit() );
			loggedUser.setAdministrateur( userTest.getAdministrateur() );
			this.getServletContext().setAttribute("User", loggedUser);
			request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
		}
		
		else {
			System.out.println("nulllllllllllll");
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
