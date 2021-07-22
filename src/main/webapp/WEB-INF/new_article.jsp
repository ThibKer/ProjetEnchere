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

	<form action="NewArticleServlet" method="POST" class="f-create-article">
	  <div>
	    <label for="article">
	    	<fmt:message key="l_article" bundle="${r}"></fmt:message>
	    </label>
	    <input type="text" name="article" id="article" >
	  </div>
	  <div>
	    <label for="description">
	    	<fmt:message key="l_description" bundle="${r}"></fmt:message>
	    </label>
	    <textarea id="description" name="description" >
		</textarea>
	  </div>
	  <div>
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
	    <input type="number" name="prix" id="prix" min="0">
	  </div>
	  <div>
	  	<label for="start">
	    	<fmt:message key="l_start_enchere" bundle="${r}"></fmt:message>
	    </label>
	    <input type="date" name="start" id="start" onChange="dateAdjust()">
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
		    <input type="text" name="rue" id="rue" value="${User.rue}">
	    </div>
	  	<div>
		  	<label for="postal">
		    	<fmt:message key="l_codepostal" bundle="${r}"></fmt:message>
		    </label>
		    <input type="text" name="postal" id="postal" value="${User.codePostal}">
	    </div>
	  	<div>
		  	<label for="ville">
		    	<fmt:message key="l_ville" bundle="${r}"></fmt:message>
		    </label>
		    <input type="text" name="ville" id="ville" value="${User.ville}">
	    </div>
	  </div>
	  

	  <div>
	  	<fmt:message key="btn_enregistrer" bundle="${r}" var="enregistrer"/>	    
		<input type="submit" name="save" value="${enregistrer}">
	  </div>
	  <div>
	    <fmt:message key="btn_annuler" bundle="${r}" var="annuler"/> 
	    <a href=Javascript:history.go(-1)>
			<input type="button" name="cancel" value="${annuler}">
		</a> 
	  </div>
	  
	</form>
	
	<script type="text/javascript">
		var today = new Date();
	 	var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0 so need to add 1 to make it 1!
		var yyyy = today.getFullYear(); 
		
		if(dd<10){
			dd='0'+dd
		} 
		if(mm<10){
			mm='0'+mm
		} 
		today = yyyy+'-'+mm+'-'+dd;
		document.getElementById("start").setAttribute("min", today);
			
			
		var today = new Date()
		var tomorrow = new Date(today)
		tomorrow.setDate(tomorrow.getDate() + 1)

		var ddt = tomorrow.getDate();
		var mmt = tomorrow.getMonth()+1; //January is 0 so need to add 1 to make it 1!
		var yyyyt = tomorrow.getFullYear(); 
		
		if(ddt<10){
		  ddt='0'+ddt
		} 
		if(mmt<10){
		  mmt='0'+mmt
		} 
		tomorrow = yyyyt+'-'+mmt+'-'+ddt;
	 	document.getElementById("end").setAttribute("min", tomorrow);  	
	 	
	 	function dateAdjust(){
	 	 	document.getElementById("end").value= '' ;
	 	 	
	 	 	var tod = new Date()
			var tom = new Date(tod)
			tom.setDate(tom.getDate() + 1)

			var dt = tom.getDate();
			var mt = tom.getMonth()+1; //January is 0 so need to add 1 to make it 1!
			var yyt = tom.getFullYear(); 
			if(dt<10){
				  dt='0'+dt
				} 
				if(mt<10){
				  mt='0'+mt
				} 
				tom = yyt+'-'+mt+'-'+dt;
	 	 	document.getElementById("end").setAttribute("min", tom); 
	 	}
	</script>
	
</body>
</html>