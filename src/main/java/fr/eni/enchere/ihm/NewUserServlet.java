package fr.eni.enchere.ihm;

import java.io.IOException;

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
		String mdpconfirm = request.getParameter("mdp2");
		
		String btn_creation = request.getParameter("create");
		String btn_annuler = request.getParameter("cancel");
		if(btn_creation != null) {
			
			
			System.out.println("btn_creation");
			
//			managerUtilisateur.checkIfDispo(pseudo);
			
//			managerUtilisateur.checkIfValid(request);
			
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
				
//						System.out.println("user : "+utilisateur);
//				try {
//					managerUtilisateur.addUtilisateur( utilisateur );
//
//				}finally {
//					request.getRequestDispatcher("LoginServlet?"
//							+"identifiant="+utilisateur.getPseudo()
//							+",mdp="+utilisateur.getMotDePasse()
//							).forward(request, response);
//				}
				managerUtilisateur.addUtilisateur( utilisateur );

				
				loggedUser = new ModelLogged( managerUtilisateur.getUtilisateurByFields(pseudo, mdp) );
				loggedUser = new ModelLogged(managerUtilisateur.createUtilisateur( utilisateur ));
						System.out.println("Logged : "+loggedUser);	
						
				request.getSession().setAttribute("User", loggedUser);
				request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("WEB-INF/user_creation.jsp").forward(request, response);
			}
			
			
		}
		else if(btn_annuler != null) {
			System.out.println("btn_annuler");
			String referer = request.getHeader("referer");
			
			response.sendRedirect(request.getHeader("referer"));
//			request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("WEB-INF/user_creation.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
