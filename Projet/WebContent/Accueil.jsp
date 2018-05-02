<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	${session.setAttribute("usertoken","loggedin")}
	<c:choose>
		<c:when
			test="${fn:substring('authenticated', 0,8 ) == (session.usertoken)}">
            
         <button type="button" name="communiquer">Communiquer</button>
			<button type="button" name="profil">Profil</button>
			<button type="button" name="deconnexion" class="deco">Déconnexion</button>
			<br />
			<p>
         liste des docs qui viendra sans doute de la bdd, select * des docs publics triés par date avec nom du createur</p>
         </c:when>
		<c:otherwise>


			<form name="login" action="lolilol" method="POST">
				<label for="pseudo">Pseudo</label> : <input type="text"
					name="pseudo" id="pseudo" /> <br /> <label for="t2">Mot
					de Passe </label> : <input type="password" name="password" id="password" />
				<br />
				<button type="submit">Se Connecter</button>
			</form>
	<br/>
	<div>Pas encore de compte ?<br/>
				<button type="button" name="register">S'enregistrer</button>
</div>

		</c:otherwise>
	</c:choose>



</body>
</html>