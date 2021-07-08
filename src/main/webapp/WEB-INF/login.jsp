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
	<fmt:message key="t_user_connect" bundle="${r}"></fmt:message>
</title>
</head>
<body>

	<form action="LoginServlet" method="POST" class="f-connect">
	  <div>
	    <label for="identifiant">
	    	<fmt:message key="l_identifiant" bundle="${r}"></fmt:message>
	    </label>
	    <input type="text" name="identifiant" id="identifiant" required>
	  </div>
	  <div>
	    <label for="mdp">
	    	<fmt:message key="l_mdp" bundle="${r}"></fmt:message>
	    </label>
	    <input type="password" name="mdp" id="mdp" required>
	  </div>
	  <div>
	    <input type="checkbox" name="souvenir" id="souvenir">
	    <label for="souvenir">
	    	<fmt:message key="l_cb_souvenir" bundle="${r}"></fmt:message>
	    </label>
	  </div>
	  <div>
	  	<fmt:message key="btn_connexion" bundle="${r}" var="connect"/>	    
		<input type="submit" name="go" value="${connect}">
	  </div>
	  <div>
	    <fmt:message key="h_mdp_lost" bundle="${r}" var="lost"/>   
		<input type="submit" name="lost" value="${lost}">
	  </div>
	</form>
	
	<form action="CreateUserServlet" method="POST" class="f-connect">
	  <div>
	    <fmt:message key="btn_creation" bundle="${r}" var="create"/>    
		<input type="submit" name="new" value="${create}">
	  </div>
	</form>
	
</body>
</html>