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
<<<<<<< HEAD
	    <i>${user.pseudo}</i>
=======
	    <i>${profil.pseudo}</i>
>>>>>>> 7305f828b0cabac32fdd0c2cefd22f5e928b9361
	  </div>
	  <div>
	    <label for="nom">
	    	<fmt:message key="l_nom" bundle="${r}"></fmt:message>
	    </label>
<<<<<<< HEAD
	    <i>${user.nom}</i>
=======
	    <i>${profil.nom}</i>
>>>>>>> 7305f828b0cabac32fdd0c2cefd22f5e928b9361
	  </div>
	  <div>
	    <label for="prenom">
	    	<fmt:message key="l_prenom" bundle="${r}"></fmt:message>
	    </label>
<<<<<<< HEAD
	    <i>${user.prenom}</i>
=======
	    <i>${profil.prenom}</i>
>>>>>>> 7305f828b0cabac32fdd0c2cefd22f5e928b9361
	  </div>
	  <div>
	  	<label for="email">
	    	<fmt:message key="l_email" bundle="${r}"></fmt:message>
	    </label>
<<<<<<< HEAD
	    <i>${user.email}</i>
=======
	    <i>${profil.email}</i>
>>>>>>> 7305f828b0cabac32fdd0c2cefd22f5e928b9361
	  </div>
	  <div>
	  	<label for="tel">
	    	<fmt:message key="l_telephone" bundle="${r}"></fmt:message>
	    </label>
<<<<<<< HEAD
	    <i>${user.tel}</i>
=======
	    <i>${profil.tel}</i>
>>>>>>> 7305f828b0cabac32fdd0c2cefd22f5e928b9361
	  </div>
	  <div>
	  	<label for="rue">
	    	<fmt:message key="l_rue" bundle="${r}"></fmt:message>
	    </label>
<<<<<<< HEAD
	    <i>${user.rue}</i>
=======
	    <i>${profil.rue}</i>
>>>>>>> 7305f828b0cabac32fdd0c2cefd22f5e928b9361
	  </div>
	  <div>
	  	<label for="postal">
	    	<fmt:message key="l_codepostal" bundle="${r}"></fmt:message>
	    </label>
<<<<<<< HEAD
	    <i>${user.postal}</i>
=======
	    <i>${profil.postal}</i>
>>>>>>> 7305f828b0cabac32fdd0c2cefd22f5e928b9361
	  </div>
	  <div>
	  	<label for="ville">
	    	<fmt:message key="l_ville" bundle="${r}"></fmt:message>
	    </label>
<<<<<<< HEAD
	    <i>${user.ville}</i>
	  </div>
	  
	  <div>
		  <label for="credit">
		    	<fmt:message key="l_credit" bundle="${r}"></fmt:message>
		  </label>
		  <i>${user.credit}</i>
=======
	    <i>${profil.ville}</i>
>>>>>>> 7305f828b0cabac32fdd0c2cefd22f5e928b9361
	  </div>
	  
	  <div>
	  	<fmt:message key="btn_retour" bundle="${r}" var="retour"/>	    
		<input type="submit" name="back" value="${retour}">
	  </div>
<<<<<<< HEAD

	  <c:if test="${idProfil == idUser}">
		  <div>
		  	<fmt:message key="btn_modifier" bundle="${r}" var="modifier"/>	    
			<input type="submit" name="upload" value="${modifier}">
		  </div>
	  </c:if>
=======
	  
>>>>>>> 7305f828b0cabac32fdd0c2cefd22f5e928b9361
</body>
</html>