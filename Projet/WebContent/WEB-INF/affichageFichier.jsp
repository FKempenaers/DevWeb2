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
<script>
	src = 'fonctions.js'
</script>
<title>Editeur</title>
</head>
<body>
	<textarea id="fichier" onkeyup="envoi()"></textarea>

	<script>
		refresh();
		setInterval(refresh, 3000);

		function refresh() {
			var r = new XMLHttpRequest();

			r.onload = function() {
				document.getElementById('fichier').value = r.responseText;
			};

			r.open("GET", "AfficheFichier");
			r.send();

		}

		function envoi() {
			var fichier = document.getElementById('fichier').value;
			var postdata = 'fichier=' + fichier;
			var formData = new FormData();
			formData.append('fichier', fichier);

			var r = new XMLHttpRequest();
			r.open('POST', 'AfficheFichier');

			r.setRequestHeader('Content-type',
					'application/x-www-form-urlencoded');

			r.send('fichier=' + fichier);
		}

	</script>

</body>
</html>