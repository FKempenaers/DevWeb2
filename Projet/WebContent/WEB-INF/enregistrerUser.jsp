<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enregistrement</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div id=connex2>
	<p>Bienvenue, nouvel utilisateur !</p>
		<form class=form-connex method="post" action="EnregistrerUser">
 
       		<label for="nom">Pseudo</label>
       		<input type="text" name="pseudo" id="pseudo" /><br />
            <label for="mdp">Mot De Passe</label>
       		<input type="password" name="mdp" id="mdp" /><br />

			<input type="submit" name="ok" value ="S'enregistrer" />
		</form>
	</div>
</body>
</html>