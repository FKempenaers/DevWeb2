<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body>

	<div class=centre>
		<p>Upload un nouveau fichier</p>
		<form method="post" action="AjouterFichier" enctype="multipart/form-data">
		> <input type="file" value="Charger" name="fichier" /> 
		<input type="submit" value="Charger" />
		</form>
	</div>

	<div id=gauche>
		<ul>
			<c:forEach items="${liste}" var="item" varStatus="i">
				<li><span>
						<form method="post" action="ChoixFichier">
							<input type="hidden" value="${item[0]}" name="id" /> 
							<input type="hidden" value="${item[3]}" name="lien" /> 
								<input type="submit" value="${item[2]}" name="fichier" />
								${permissions.get(i.getIndex())}
						</form>
				</span></li>
			</c:forEach>
		</ul>
	</div>

	<div id=droite>
		<ul>
			<c:forEach items="${liste}" var="item">
				<li><span>
						<form method="post" action="AjoutUseraFichier">
							<input type="hidden" value="${item[0]}" name="id" />
								<label class=other for="user">Nom de l'utilisateur :</label> 
								<input type="text" name="user" /> 
								<input type="submit" value="Ajout à ${item[2]}" name="fichier" />
						</form>
				</span></li>
			</c:forEach>
		</ul>
	</div>
	
		<div>
		<ul>
			<c:forEach items="${liste}" var="item">
				<li><span>
						<form method="post" action="SuppUserDeFichier">
							<input type="hidden" value="${item[0]}" name="id" />
								<label class=other for="user">Nom de l'utilisateur :</label> 
								<input type="text" name="user" /> 
								<input type="submit" value="Enlever de ${item[2]}" name="fichier" />
						</form>
				</span></li>
			</c:forEach>
		</ul>
	</div>

<a href="Init" >Retour page de connexion</a>

</body>
</html>