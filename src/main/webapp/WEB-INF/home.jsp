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
<link rel="stylesheet" href="toto.css" type="text/css"/>
</head>
<body class="body">
	<nav >
		<c:if test="${empty User}">
			<a href="LoginServlet">
				<fmt:message key="nav_inscription_connect" bundle="${r}"></fmt:message>
			</a>
		</c:if>	
		<c:if test="${!empty User}">
			<a href="/encheres">
				<fmt:message key="nav_encheres" bundle="${r}"></fmt:message>
			</a>
			<a href="NewArticleServlet">
				<fmt:message key="nav_vendre" bundle="${r}"></fmt:message>
			</a>
			<a href="ProfilServlet?target=me">
				<fmt:message key="nav_profil" bundle="${r}"></fmt:message>
			</a>
<!-- 			<a href="/deconnection" > -->
 			<a href="DeconnectionServlet" >
				<fmt:message key="nav_deconnexion" bundle="${r}"></fmt:message>
			</a>
		</c:if>	
	</nav>
	
	
	<label for="st" class = "sous-titre">
	   <fmt:message key="st_home" bundle="${r}"></fmt:message>
	</label>

	<form action="HomeServlet" method="POST" class="f-create-article">
	<div class="f-container">
	  <div class = "h-filtre">
	    <label for="filtre">
	    	<fmt:message key="l_filtre" bundle="${r}"></fmt:message>
	    </label>
	    <fmt:message key="ph_filtre" bundle="${r}" var="placeholder"/>     
	    <input placeholder="${placeholder}" type="search" name="filtre" id="filtre" >
	  </div>
	  <div class = "h-categorie">
	    <label for="categorie">
	    	<fmt:message key="l_categorie" bundle="${r}"></fmt:message>
	    </label>
	    <select name="categorie" id="categorie" >
		    <option value="">
		    	<fmt:message key="ph_categorie" bundle="${r}"></fmt:message>
			</option>
		    <c:forEach var="item" items="${categories}">
		    	<option value="${item.libelle}">${item.libelle}</option>
		    </c:forEach>
		</select>
	  </div>
	  
	  <div class="f-container34">
	  <c:if test="${!empty User}">
		  <div class = "bloc-radio-checkbox">
		  	<input class="radio bloc-radio-i" onClick="radioEvent(this,'radio','groupventes','groupachats')" type="radio" name="achats" id="achats">
		    <label for="achats" class="bloc-radio-l">
		    	<fmt:message key="rb_achats" bundle="${r}"></fmt:message>
		    </label>
		    <div>
		    	<div class="bloc-checkbox">
			    	<input class="groupachats" onClick="toggle(this,'groupachats')" type="checkbox" name="achats-open" id="achats-open">
				    <label for="achats-open">
				    	<fmt:message key="cb_achats_open" bundle="${r}"></fmt:message>
				    </label>
		    	</div>
		    	<div class="bloc-checkbox">
			    	<input class="groupachats" onClick="toggle(this,'groupachats')" type="checkbox" name="achats-my" id="achats-my">
				    <label for="achats-my">
				    	<fmt:message key="cb_achats_my" bundle="${r}"></fmt:message>
				    </label>
		    	</div>
		    	<div class="bloc-checkbox">
			    	<input class="groupachats" onClick="toggle(this,'groupachats')" type="checkbox" name="achats-win" id="achats-win">
				    <label for="achats-win">
				    	<fmt:message key="cb_achats_win" bundle="${r}"></fmt:message>
				    </label>
		    	</div>
		    </div>
		  </div>
		  
		  <div class = "bloc-radio-checkbox">
		  	<input class="radio bloc-radio-i" onClick="radioEvent(this,'radio','groupachats','groupventes')" type="radio" name="ventes" id="ventes">
		    <label for="achats" class="bloc-radio-l" >
		    	<fmt:message key="rb_ventes" bundle="${r}"></fmt:message>
		    </label>
		    <div>
		    	<div class="bloc-checkbox">
			    	<input class="groupventes" onClick="toggle(this,'groupventes')" type="checkbox" name="ventes-ec" id="ventes-ec">
				    <label for="ventes-ec">
				    	<fmt:message key="cb_ventes_ec" bundle="${r}"></fmt:message>
				    </label>
		    	</div>
		    	<div class="bloc-checkbox">
			    	<input class="groupventes" onClick="toggle(this,'groupventes')" type="checkbox" name="ventes-nd" id="ventes-nd">
				    <label for="ventes-nd">
				    	<fmt:message key="cb_ventes_nd" bundle="${r}"></fmt:message>
				    </label>
		    	</div >
		    	<div class="bloc-checkbox">
			    	<input class="groupventes" onClick="toggle(this,'groupventes')" type="checkbox" name="ventes-end" id="ventes-end">
				    <label for="ventes-end">
				    	<fmt:message key="cb_ventes_end" bundle="${r}"></fmt:message>
				    </label>
		    	</div>
		    </div>
		  </div>
		</c:if>
		</div>
	</div>
	  <div class = "bouton">
	    <fmt:message key="btn_rechercher" bundle="${r}" var="rechercher"/>   
		<input type="submit" name="search" value="${rechercher}">
	  </div>  
	</form>
	
	<section>
		<c:forEach var="vente" items="${liste}">
		 <a href="your-link-bio" style="text-decoration:none;">
			<div class="card">
				<div>
					<img alt="" src="">
				</div>
				<div>
					<label for="article">
				    	<i>${vente.nomArticle}</i>
				    </label>
					<div>
					  	<label for="offre">
					    	<fmt:message key="l_prix" bundle="${r}"></fmt:message>
					    </label>
					    <fmt:message key="l_pts" bundle="${r}">
            				 <fmt:param value="${vente.prixVente}"/>
        				</fmt:message>
					</div>
					<div>
					  	<label for="end">
					    	<fmt:message key="l_end_enchere" bundle="${r}"></fmt:message>
					    </label>
					   
  						<fmt:parseDate value="${vente.dateFinEncheres}" pattern="y-M-dd'T'H:m" var="myParseDate"></fmt:parseDate> 
						<fmt:formatDate value="${myParseDate}"  pattern="dd MMM yyyy"></fmt:formatDate > 
						<fmt:message key="sep_prix" bundle="${r}"></fmt:message>
						<fmt:formatDate value="${myParseDate}"  pattern="HH:mm"></fmt:formatDate >
					   <%--  <i><fmt:formatDate type = "both" dateStyle = "long" timeStyle = "long" value = "${vente.dateFinEncheres}" /></i>
     					<i><fmt:formatDate pattern = "yyyy-MM-dd" value = "${vente.dateFinEncheres}" /></i> --%>
   					 </div>
					 <div>
					  	<label for="vendeur">
					    	<fmt:message key="l_vendeur" bundle="${r}"></fmt:message>
					    </label>
					    <a href="ProfilServlet?target=${vente.utilisateur.getNoUtilisateur()}">${vente.utilisateur.getPseudo()}</a>
					 </div>
				</div>
			</div>
			</a>
		</c:forEach>
	</section>

<script type="text/javascript">

function radioEvent(which,radioClass,checkboxClass,checkboxClass2){
	toggle(which,radioClass);
	hidden(which,checkboxClass,checkboxClass2);
}
function toggle(which,theClass){
	var checkbox=document.getElementsByClassName(theClass);
	for(var i=0;i<checkbox.length;i++){
		if(checkbox[i]==which){
		}else{
			checkbox[i].checked=false;		
		}
	}
}
function hidden(which,disabledClass,unabledClass){
	var disabled=document.getElementsByClassName(disabledClass);
	var unabled=document.getElementsByClassName(unabledClass);	
	for(var i=0;i<disabled.length;i++){
		if(disabled[i]==which){
			disabled[i].disabled=false;
			unabled[i].disabled=true;
		}else{
			disabled[i].disabled=true;
			disabled[i].checked=false;
			unabled[i].disabled=false;
		}
	}
}

</script>
	
	
	
	
	
</body>
</html>