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
</head>
<body>
	<label for="st">
	   <fmt:message key="st_user_profil" bundle="${r}"></fmt:message>
	</label>
	
	  <div>
	    <label for="pseudo">
	    	<fmt:message key="l_pseudo" bundle="${r}"></fmt:message>
	    </label>
	    <i>${profil.pseudo}</i>
	  </div>
	  <div>
	    <label for="nom">
	    	<fmt:message key="l_nom" bundle="${r}"></fmt:message>
	    </label>
	    <i>${profil.nom}</i>
	  </div>
	  <div>
	    <label for="prenom">
	    	<fmt:message key="l_prenom" bundle="${r}"></fmt:message>
	    </label>
	    <i>${profil.prenom}</i>
	  </div>
	  <div>
	  	<label for="email">
	    	<fmt:message key="l_email" bundle="${r}"></fmt:message>
	    </label>
	    <i>${profil.email}</i>
	  </div>
	  <div>
	  	<label for="tel">
	    	<fmt:message key="l_telephone" bundle="${r}"></fmt:message>
	    </label>
	    <i>${profil.tel}</i>
	  </div>
	  <div>
	  	<label for="rue">
	    	<fmt:message key="l_rue" bundle="${r}"></fmt:message>
	    </label>
	    <i>${profil.rue}</i>
	  </div>
	  <div>
	  	<label for="postal">
	    	<fmt:message key="l_codepostal" bundle="${r}"></fmt:message>
	    </label>
	    <i>${profil.postal}</i>
	  </div>
	  <div>
	  	<label for="ville">
	    	<fmt:message key="l_ville" bundle="${r}"></fmt:message>
	    </label>
	    <i>${profil.ville}</i>
	  </div>
	  
	  <div>
	  	<fmt:message key="btn_retour" bundle="${r}" var="retour"/>	    
		<input type="submit" name="back" value="${retour}">
	  </div>
	  
</body>
</html>