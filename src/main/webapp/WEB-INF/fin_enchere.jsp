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
	<fmt:message key="t_win_vente" bundle="${r}"></fmt:message>
</title>
</head>
<body>
	<label for="st">
	   <fmt:message key="st_win_vente" bundle="${r}"></fmt:message>
	</label>

	<form action="HomeServlet" method="POST" class="f-win-vente">
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
	  	<label for="retrait">
	    	<fmt:message key="l_retrait" bundle="${r}"></fmt:message>
	    </label>
	    <i>${vente.rue} ${vente.codepostal} ${vente.ville}</i>
	  </div>
	  <div>
	  	<label for="vendeur">
	    	<fmt:message key="l_vendeur" bundle="${r}"></fmt:message>
	    </label>
	    <i>${vente.vendeur.name}</i>
	  </div>
	  
	  <div>
	  	<label for="tel">
	    	<fmt:message key="l_proposition" bundle="${r}"></fmt:message>
	    </label>
	   	<i>${vente.vendeur.tel}</i>
	  </div>
	  
	  <div>
	    <fmt:message key="btn_retour" bundle="${r}" var="retour"/>   
		<input type="submit" name="back" value="${retour}">
	  </div>
	  
	</form>
	
</body>
</html>