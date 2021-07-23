<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
	<c:if test="${!empty locale}">
		<fmt:setLocale value="${locale}"/>
	</c:if>
	<fmt:setBundle basename="fr.eni.enchere.ihm.label.label" var="r"/>

<head>
<meta charset="ISO-8859-1">
<title>
	<fmt:message key="t_user_profil" bundle="${r}"></fmt:message>
</title>
	<link rel="stylesheet" href="toto.css" type="text/css"/>
</head>
<body>
	<label for="st" class="mp-st">
	   <fmt:message key="st_user_profil" bundle="${r}"></fmt:message>
	</label>

	<form action="ProfilServlet" method="POST" class="f-create-user">
		<div class="um-container">
			<div class="um-gauche">
			  <div class="um-test">
			    <label for="pseudo">
			    	<fmt:message key="l_pseudo" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="pseudo" id="pseudo" value='${User.pseudo}'>
			  </div>
			  <div class="um-test">
			    <label for="nom">
			    	<fmt:message key="l_nom" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="nom" id="nom" value='${User.nom}'>
			  </div>
			  <div class="um-test">
			    <label for="prenom">
			    	<fmt:message key="l_prenom" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="prenom" id="prenom" value='${User.prenom}'>
			  </div>
			  <div class="um-test">
			  	<label for="email">
			    	<fmt:message key="l_email" bundle="${r}"></fmt:message>
			    </label>
			    <input type="email" name="email" id="email" value='${User.email}'>
			  </div>
			  <div class="um-test">
			  	<label for="tel">
			    	<fmt:message key="l_telephone" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="tel" id="tel" value='${User.telephone}'>
			  </div>
			  <div class="um-test">
			  	<label for="rue">
			    	<fmt:message key="l_rue" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="rue" id="rue" value='${User.rue}'>
			  </div>
			  <div class="um-test">
			  	<label for="postal">
			    	<fmt:message key="l_codepostal" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="postal" id="postal" value='${User.codePostal}'>
			  </div>
			  <div class="um-test">
			  	<label for="ville">
			    	<fmt:message key="l_ville" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="ville" id="ville" value='${User.ville}'>
			  </div>
			  <div class="um-test">
			  	<label for="current_mdp">
			    	<fmt:message key="l_current_mdp" bundle="${r}"></fmt:message>
			    </label>
			    <input type="password" name="current_mdp" id="current_mdp" placeholder="*****">
			  </div>
			  <div class="um-test">
			  	<label for="new_mdp">
			    	<fmt:message key="l_new_mdp" bundle="${r}"></fmt:message>
			    </label>
			    <input type="password" name="new_mdp" id="new_mdp" placeholder="*****">
			  </div>
			  <div class="um-test">
			  	<label for="mdp2">
			    	<fmt:message key="l_confirmation" bundle="${r}"></fmt:message>
			    </label>
			    <input type="password" name="mdp2" id="mdp2" placeholder="*****">
			  </div>
			  	
			  <div class="um-test">
				  <label for="credit">
				    	<fmt:message key="l_credit" bundle="${r}"></fmt:message>
				  </label>
				  <input type="text" name="credit" id="credit" value='${user.credit}' readonly>
			  </div>
	  		</div>
		</div>
	  <div class="um-bouton">
	  	<fmt:message key="btn_enregistrer" bundle="${r}" var="enregistrer"/>	    
		<input type="submit" name="save" value="${enregistrer}">
	  </div>
	  <div>
	    <fmt:message key="btn_delete_user" bundle="${r}" var="supprimer"/>   
		<input type="submit" name="delete" value="${supprimer}">
	  </div>
	  
	</form>
	
</body>
</html>