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
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/ProfilServlet")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager managerUtilisateur = UtilisateurManagerSingl.getInstance();
	ModelLogged loggedUser;   
	Utilisateur utilisateur = new Utilisateur(); 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loggedUser = (ModelLogged) request.getSession().getAttribute("User");
		String target = request.getParameter("target");
		Object logged = request.getSession().getAttribute("User");
		String back = request.getParameter("back");
		String upload = request.getParameter("upload");
		String save = request.getParameter("save");
		String delete = request.getParameter("delete");
		RequestDispatcher rd = null;
		if (upload != null) {
			rd = request.getRequestDispatcher("WEB-INF/user_modification.jsp");
		}
		if (save != null) {
			loggedUser = (ModelLogged) request.getSession().getAttribute("User");
			String current_mdp =  request.getParameter("current_mdp") ;
			String new_mdp =  request.getParameter("new_mdp") == null ? " " : request.getParameter("mdp2") ;
			String mdp2 =  request.getParameter("mdp2") ;
			if ( loggedUser.getMotDePasse().equals(current_mdp) && new_mdp.equals(mdp2) ) {
				utilisateur.setPseudo( request.getParameter("pseudo") );
				utilisateur.setNom( request.getParameter("nom") );
				utilisateur.setPrenom( request.getParameter("prenom") );
				utilisateur.setEmail( request.getParameter("email") );
				utilisateur.setTelephone( request.getParameter("tel") );
				utilisateur.setRue( request.getParameter("rue") );
				utilisateur.setCodePostal( request.getParameter("postal") );
				utilisateur.setVille( request.getParameter("ville") );
				utilisateur.setMotDePasse( request.getParameter("new_mdp") );
				utilisateur.setCredit( Integer.valueOf(request.getParameter("credit")) );
				utilisateur.setAdministrateur( loggedUser.getAdministrateur() );
				utilisateur.setNoUtilisateur( loggedUser.getNoUtilisateur() );

				managerUtilisateur.updateUtilisateur( utilisateur );	
				
				loggedUser.setPseudo( utilisateur.getPseudo() );
				loggedUser.setNom( utilisateur.getNom() );
				loggedUser.setPrenom( utilisateur.getPrenom() );
				loggedUser.setEmail( utilisateur.getEmail() );
				loggedUser.setTelephone( utilisateur.getTelephone() );
				loggedUser.setRue( utilisateur.getRue() );
				loggedUser.setCodePostal( utilisateur.getCodePostal() );
				loggedUser.setVille( utilisateur.getVille() );
				loggedUser.setMotDePasse( utilisateur.getMotDePasse() );		
	
				request.getSession().setAttribute("User", loggedUser);
				rd = request.getRequestDispatcher("/HomeServlet");
			}	
			else {
				// TODO err de saisi dans le formulaire
				rd = request.getRequestDispatcher("WEB-INF/user_modification.jsp");
			}	
		}
		if (delete != null) {
			//TODO supprimer le compte
			loggedUser = (ModelLogged) request.getSession().getAttribute("User");
			managerUtilisateur.deleteUtilisateur( loggedUser.getNoUtilisateur() );
			rd = request.getRequestDispatcher("/DeconnectionServlet");
		}
		if ("me".equals(target)) {
			utilisateur = managerUtilisateur.getUtilisateurByFields(loggedUser.getPseudo(), loggedUser.getMotDePasse());
			request.getSession().setAttribute("user", utilisateur);
			request.setAttribute("full", "yes");
			rd = request.getRequestDispatcher("WEB-INF/user_profil.jsp");
		}
		if (!"me".equals(target) && target != null) {
			utilisateur = managerUtilisateur.getUtilisateurById(Integer.parseInt(target));
			request.getSession().setAttribute("user", utilisateur);
			request.setAttribute("full", null);
		}
		if (request.getParameter("user") == null && rd == null) {
			// TODO recherche du profil correspondant a la target
			request.setAttribute("user", logged);
			rd = request.getRequestDispatcher("WEB-INF/user_profil.jsp");	
		}
		if (back != null) {
			String referer = request.getHeader("referer");	
			response.sendRedirect( referer );
		}
		else {
			rd.forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
