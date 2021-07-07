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
	<fmt:message key="t_vente" bundle="${r}"></fmt:message>
</title>
</head>
<body>
	<label for="st">
	   <fmt:message key="st_vente_new" bundle="${r}"></fmt:message>
	</label>

	<form action="HomeServlet" method="POST" enctype="multipart/form-data" class="f-create-article">
	  <div>
	    <label for="article">
	    	<fmt:message key="l_article" bundle="${r}"></fmt:message>
	    </label>
	    <input type="text" name="article" id="article" required>
	  </div>
	  <div>
	    <label for="description">
	    	<fmt:message key="l_description" bundle="${r}"></fmt:message>
	    </label>
	    <textarea id="description" name="description" form="f-create-article" required>
		</textarea>
	  </div>
	  <div>
	    <label for="categorie">
	    	<fmt:message key="l_categorie" bundle="${r}"></fmt:message>
	    </label>
	    <select name="categorie" id="categorie" required>
		    <option value="">
		    	<fmt:message key="ph_categorie" bundle="${r}"></fmt:message>
			</option>
		    <c:forEach var="item" items="${global.categorie}">
		    	<option value="${item}">${item}</option>
		    </c:forEach>
		</select>
	  </div>
	  
	  
	  
	  <div>
	  	<label for="photo">
	    	<fmt:message key="l_photo" bundle="${r}"></fmt:message>
	    </label>
	    <fmt:message key="btn_photo" bundle="${r}" var="upload"/>    
		<input type="file" name="photo"/>
	  </div>
	  <div>
	  	<label for="prix">
	    	<fmt:message key="l_init_prix" bundle="${r}"></fmt:message>
	    </label>
	    <input type="number" name="prix" id="prix">
	  </div>
	  <div>
	  	<label for="start">
	    	<fmt:message key="l_start_enchere" bundle="${r}"></fmt:message>
	    </label>
	    <input type="date" name="start" id="start">
	  </div>
	  <div>
	  	<label for="end">
	    	<fmt:message key="l_end_enchere" bundle="${r}"></fmt:message>
	    </label>
	    <input type="date" name="end" id="end">
	  </div>
	  
	  <div id="vente-retrait">
	  	<label for="retrait">
		    <fmt:message key="l_retrait" bundle="${r}"></fmt:message>
		</label>
	  
	  	<div>
		  	<label for="rue">
		    	<fmt:message key="l_rue" bundle="${r}"></fmt:message>
		    </label>
		    <input type="text" name="rue" id="rue" value="${user.ville}">
	    </div>
	  	<div>
		  	<label for="postal">
		    	<fmt:message key="l_codepostal" bundle="${r}"></fmt:message>
		    </label>
		    <input type="text" name="postal" id="postal" value="${user.ville}">
	    </div>
	  	<div>
		  	<label for="ville">
		    	<fmt:message key="l_ville" bundle="${r}"></fmt:message>
		    </label>
		    <input type="text" name="ville" id="ville" value="${user.ville}">
	    </div>
	  </div>
	  

	  <div>
	  	<fmt:message key="btn_enregistrer" bundle="${r}" var="enregistrer"/>	    
		<input type="submit" name="save" value="${enregistrer}">
	  </div>
	  <div>
	    <fmt:message key="btn_annuler" bundle="${r}" var="annuler"/>   
		<input type="submit" name="cancel" value="${annuler}">
	  </div>
	  
	</form>
	
</body>
</html>