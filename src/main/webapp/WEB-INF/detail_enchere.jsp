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
	<fmt:message key="t_detail_vente" bundle="${r}"></fmt:message>
</title>
<link rel="stylesheet" href="toto.css" type="text/css"/>
</head>
<body>
	<label for="st" class="de-st">
	   <fmt:message key="st_detail_vente" bundle="${r}"></fmt:message>
	</label>

	<form action="HomeServlet" method="POST" class="f-propo-vente">
	  <div class="de-gauche">
	  </div>
	  <div class="de-droite">
	  <div>
	    <label for="article">
	    	<i>${vente.article}</i>
	    </label>
	  </div>
	  <div>
	    <label for="description">
	    	<fmt:message key="l_description" bundle="${r}"></fmt:message>
	    </label>
	    <textarea id="description" name="description" readonly>
	     	${vente.description}
		</textarea>
	  </div>
	  <div>
	    <label for="categorie">
	    	<fmt:message key="l_categorie" bundle="${r}"></fmt:message>
	    </label>
	    <i>${vente.categorie}</i>
	  </div>
	  
	  <div>
	  	<label for="offre">
	    	<fmt:message key="l_best_enchere" bundle="${r}"></fmt:message>
	    </label>
	    <fmt:message key="msg_best_enchere" bundle="${r}">
             <fmt:param value="${vente.offre}"/>
             <fmt:param value="${vente.owner}"/>
         </fmt:message>
	  </div>
	  <div>
	  	<label for="prix">
	    	<fmt:message key="l_init_prix" bundle="${r}"></fmt:message>
	    </label>
	    <fmt:message key="l_pts" bundle="${r}">
             <fmt:param value="${vente.prix}"/>
         </fmt:message>
	  </div>
	  <div>
	  	<label for="end">
	    	<fmt:message key="l_end_enchere" bundle="${r}"></fmt:message>
	    </label>
	    <i>${vente.fin}</i>
	  </div>
	  <div>
	  	<label for="vendeur">
	    	<fmt:message key="l_vendeur" bundle="${r}"></fmt:message>
	    </label>
	    <i>${vente.vendeur.name}</i>
	  </div>
	  
	  <div>
	  	<label for="proposition">
	    	<fmt:message key="l_proposition" bundle="${r}"></fmt:message>
	    </label>
	   	<input type="number" name="proposition" id="proposition" min="${vente.offre}">
	  </div>
	  
	  <div class="de-bouton">
	    <fmt:message key="btn_encherir" bundle="${r}" var="encherir"/>   
		<input type="submit" name="bet" value="${encherir}">
	  </div>
	  </div>
	</form>
	
</body>
</html>