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
</head>
<body>
	<label for="st">
	   <fmt:message key="st_detail_vente" bundle="${r}"></fmt:message>
	</label>

	<form action="EnchereServlet?target=${vente.noArticle}" method="POST" class="f-propo-vente">
	  <div>
	    <label for="article">
	   <%--  <input type="hidden" name="target" value="${vente.noArticle}"> --%>
	    	<i>${vente.nomArticle}</i>
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
	    <i>${vente.categorie.getLibelle()}</i>
	  </div>
	  
	  <div>
	  	<label for="offre">
	    	<fmt:message key="l_best_enchere" bundle="${r}"></fmt:message>
	    </label>
	    <fmt:message key="msg_best_enchere" bundle="${r}">
		    <c:choose>
			    <c:when test="${!empty vente.encheres}">
			        <fmt:param value="${vente.encheres.get(0).getMontant_Enchere()}"/>
			        <fmt:param value="${vente.encheres.get(0).getUtilisateur().getPseudo()}"/> 
			    </c:when>    
			    <c:otherwise>
			       <fmt:param value="..."/>
			       <fmt:param value="personne"/> 
			    </c:otherwise>
			</c:choose>
        </fmt:message>
	  </div>
	  <div>
	  	<label for="prix">
	    	<fmt:message key="l_init_prix" bundle="${r}"></fmt:message>
	    </label>
	    <fmt:message key="l_pts" bundle="${r}">
             <fmt:param value="${vente.miseAPrix}"/>
         </fmt:message>
	  </div>
	  <div>
	  	<label for="end">
	    	<fmt:message key="l_end_enchere" bundle="${r}"></fmt:message>
	    </label>
	    <i>
		    <fmt:parseDate value="${vente.dateFinEncheres}" pattern="y-M-dd'T'H:m" var="myParseDate"></fmt:parseDate> 
			<fmt:formatDate value="${myParseDate}"  pattern="dd MMM yyyy"></fmt:formatDate > 
			<fmt:message key="sep_prix" bundle="${r}"></fmt:message>
			<fmt:formatDate value="${myParseDate}"  pattern="HH:mm"></fmt:formatDate >
	 	</i>
	 	</div>
	  <div>
	  	<label for="vendeur">
	    	<fmt:message key="l_vendeur" bundle="${r}"></fmt:message>
	    </label>
	    <i>${vente.utilisateur.getPseudo()}</i>
	  </div>
	  
	  <div>
	  	<label for="proposition">
	    	<fmt:message key="l_proposition" bundle="${r}"></fmt:message>
	    </label>
	    	<c:choose>
			    <c:when test="${!empty vente.encheres}">
			        <c:set var="count" value="${vente.encheres.get(0).getMontant_Enchere() + 1}" scope="page"/>
			    </c:when>    
			    <c:otherwise>
			      <c:set var="count" value="1" scope="page"/>
			    </c:otherwise>
			</c:choose>
	    
	   	<input type="number" name="proposition" id="proposition" min="${count}">
	  </div>
	  
	  <div>
	    <fmt:message key="btn_encherir" bundle="${r}" var="encherir"/>   
		<input type="submit" name="bet" value="${encherir}">
	  </div>
	  
	</form>
	
</body>
</html>