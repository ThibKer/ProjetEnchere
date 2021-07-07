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
	<fmt:message key="t_home" bundle="${r}"></fmt:message>
</title>
</head>
<body>
	<nav>
		<a href="/loggin">
			<fmt:message key="nav_inscription_connect" bundle="${r}"></fmt:message>
		</a>
		
		<a href="/encheres">
			<fmt:message key="nav_encheres" bundle="${r}"></fmt:message>
		</a>
		
		<a href="/vendre">
			<fmt:message key="nav_vendre" bundle="${r}"></fmt:message>
		</a>
		
		<a href="/profil">
			<fmt:message key="nav_profil" bundle="${r}"></fmt:message>
		</a>
		
		<a href="/deconnection">
			<fmt:message key="nav_deconnexion" bundle="${r}"></fmt:message>
		</a>
	</nav>
	
	
	<label for="st">
	   <fmt:message key="st_home" bundle="${r}"></fmt:message>
	</label>

	<form action="HomeServlet" method="POST" enctype="multipart/form-data" class="f-create-article">
	  <div>
	    <label for="filtre">
	    	<fmt:message key="l_filtre" bundle="${r}"></fmt:message>
	    </label>
	    <fmt:message key="ph_filtre" bundle="${r}" var="placeholder"/>     
	    <input placeholder="${placeholder}" type="search" name="filtre" id="filtre" required>
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
	  	<input type="radio" name="achats" id="achats">
	    <label for="achats">
	    	<fmt:message key="rb_achats" bundle="${r}"></fmt:message>
	    </label>
	    <div>
	    	<div>
		    	<input type="checkbox" name="achats-open" id="achats-open">
			    <label for="achats-open">
			    	<fmt:message key="cb_achats_open" bundle="${r}"></fmt:message>
			    </label>
	    	</div>
	    	<div>
		    	<input type="checkbox" name="achats-my" id="achats-my">
			    <label for="achats-my">
			    	<fmt:message key="cb_achats_my" bundle="${r}"></fmt:message>
			    </label>
	    	</div>
	    	<div>
		    	<input type="checkbox" name="achats-win" id="achats-win">
			    <label for="achats-win">
			    	<fmt:message key="cb_achats_win" bundle="${r}"></fmt:message>
			    </label>
	    	</div>
	    </div>
	  </div>
	  <div>
	  	<input type="radio" name="achats" id="achats">
	    <label for="achats">
	    	<fmt:message key="rb_ventes" bundle="${r}"></fmt:message>
	    </label>
	    <div>
	    	<div>
		    	<input type="checkbox" name="ventes-ec" id="ventes-ec">
			    <label for="ventes-ec">
			    	<fmt:message key="cb_ventes_ec" bundle="${r}"></fmt:message>
			    </label>
	    	</div>
	    	<div>
		    	<input type="checkbox" name="ventes-nd" id="ventes-nd">
			    <label for="ventes-nd">
			    	<fmt:message key="cb_ventes_nd" bundle="${r}"></fmt:message>
			    </label>
	    	</div>
	    	<div>
		    	<input type="checkbox" name="ventes-end" id="ventes-end">
			    <label for="ventes-end">
			    	<fmt:message key="cb_ventes_end" bundle="${r}"></fmt:message>
			    </label>
	    	</div>
	    </div>
	  </div>

	  <div>
	    <fmt:message key="btn_rechercher" bundle="${r}" var="rechercher"/>   
		<input type="submit" name="search" value="${rechercher}">
	  </div>  
	</form>
	
	<section>
		<a href="var">
		<c:forEach var="vente" items="${liste}">
			<div class="card">
				<div>
					<img alt="" src="">
				</div>
				<div>
					<label for="article">
				    	<i>${vente.article}</i>
				    </label>
					<div>
					  	<label for="offre">
					    	<fmt:message key="l_prix" bundle="${r}"></fmt:message>
					    </label>
					    <fmt:message key="l_pts" bundle="${r}">
            				 <fmt:param value="${vente.offre}"/>
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
				</div>
			</div>
		</c:forEach>
		</a>
	</section>
	
	
	
	
	
</body>
</html>