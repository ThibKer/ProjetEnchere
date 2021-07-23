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
	<fmt:message key="t_user_crea" bundle="${r}"></fmt:message>
</title>
	<link rel="stylesheet" href="toto.css" type="text/css"/>
</head>
<body>
	<label for="st" class="mp-st">
	   <fmt:message key="st_user_crea" bundle="${r}"></fmt:message>
	</label>

	<form action="NewUserServlet" method="POST" class="f-create-user">
		<div class = "uc-container">
			<div class = "uc-gauche">
			  <div class="test">
			    <label for="pseudo">
			    	<fmt:message key="l_pseudo" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="pseudo" id="pseudo" required>
			  </div>
			  <div class="test">
			    <label for="nom">
			    	<fmt:message key="l_nom" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="nom" id="nom" required>
			  </div>
			  <div class="test">
			    <label for="prenom">
			    	<fmt:message key="l_prenom" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="prenom" id="prenom" required>
			  </div>
			  <div class="test">
			  	<label for="email">
			    	<fmt:message key="l_email" bundle="${r}"></fmt:message>
			    </label>
			    <input type="email" name="email" id="email">
			  </div>
			  <div class="test">
			  	<label for="tel">
			    	<fmt:message key="l_telephone" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="tel" id="tel">
			  </div>
			  <div class="test">
			  	<label for="rue">
			    	<fmt:message key="l_rue" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="rue" id="rue">
			  </div>
			  <div class="test">
			  	<label for="postal">
			    	<fmt:message key="l_codepostal" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="postal" id="postal">
			  </div>
			  <div class="test">
			  	<label for="ville">
			    	<fmt:message key="l_ville" bundle="${r}"></fmt:message>
			    </label>
			    <input type="text" name="ville" id="ville">
			  </div>
			  <div class="test">
			  	<label for="mdp">
			    	<fmt:message key="l_mdp" bundle="${r}"></fmt:message>
			    </label>
			    <input type="password" name="mdp" id="mdp">
			  </div>
			  <div class="test">
			  	<label for="mdp2">
			    	<fmt:message key="l_confirmation" bundle="${r}"></fmt:message>
			    </label>
			    <input type="password" name="mdp2" id="mdp2">
			  </div>
			</div>
		 </div>
	  <div>
	  	<fmt:message key="btn_creer" bundle="${r}" var="creer"/>	    
		<input type="submit" name="create" value="${creer}">
	  </div>
	  <div>
	    <fmt:message key="btn_annuler" bundle="${r}" var="annuler"/>   
		<input type="submit" name="cancel" value="${annuler}">
	  </div>
	  
	</form>
	
</body>
</html>