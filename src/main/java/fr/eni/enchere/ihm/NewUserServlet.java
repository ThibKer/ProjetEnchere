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
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager managerUtilisateur = UtilisateurManagerSingl.getInstance();
	ModelLogged loggedUser;   
	Utilisateur utilisateur = new Utilisateur();   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("tel");
		String rue = request.getParameter("rue");
		String codepostal = request.getParameter("postal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");	
		String btn_creation = request.getParameter("create");
		String btn_annuler = request.getParameter("cancel");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/user_creation.jsp");;
		if(btn_creation != null) {		
			System.out.println("btn_creation");
			if(managerUtilisateur.checkIfAvalableAndOk(request)) {
				System.out.println("valide");
				utilisateur.setPseudo(pseudo);
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);
				utilisateur.setEmail(email);
				utilisateur.setTelephone(telephone);
				utilisateur.setRue(rue);
				utilisateur.setCodePostal(codepostal);
				utilisateur.setVille(ville);
				utilisateur.setMotDePasse(mdp);
				utilisateur.setCredit(100);
				loggedUser = new ModelLogged( managerUtilisateur.createUtilisateur( utilisateur ));
				request.getSession().setAttribute("User", loggedUser);
				rd = request.getRequestDispatcher("WEB-INF/home.jsp");
			}
			else {
				rd = request.getRequestDispatcher("WEB-INF/user_creation.jsp");
			}
		}
		else if(btn_annuler != null) {
			String referer = request.getHeader("referer");
			response.sendRedirect(referer);
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
