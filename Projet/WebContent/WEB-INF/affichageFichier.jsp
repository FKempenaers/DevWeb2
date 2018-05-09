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
<title>Insert title here</title>
</head>
<body>
	<%-- 	${listeMessages.afficher()} --%>

	<textarea id="fichier" onkeyup="envoi()"></textarea>


	<script>
		// implementation sse

				setInterval(refresh,3000);
		
		function refresh() {
			var r = new XMLHttpRequest();

			r.onload = function() {
				document.getElementById('fichier').value = r.responseText;
			};

			r.open("GET", "AfficheFichier");
			r.send();

// 		var source = new EventSource("AfficheFichier");
// 			source.onmessage = function(event) {
// 				document.getElementById('fichier').value = event.data;
// 			};
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


		// 		var source = new EventSource("AfficheFichier");
		// 		source.onmessage = function(event) {
		// 			document.getElementById('fichier').value = event.data;
		// 		};

		// 		setInterval(function() {
		// 			var fichier = document.getElementById('fichier').value;
		// 			var postdata = 'fichier='+fichier;
		// 			var formData = new FormData();
		// 			formData.append('fichier',fichier);

		// 			var r = new XMLHttpRequest();
		// 			r.open("POST", "AfficheFichier",true);

		// 			r.setRequestHeader("fichier", fichier);

		// 			r.send();
		// 		}, 1);

		// implementation webSocket
		/*var socket = new WebSocket("ws://localhost:8080/Projet/AfficheFichier");
		sendText();

		/*function sendText() {
		// Construct a msg object containing the data the server needs to process the message from the chat client.
		var msg = {
		type : "message",
		text : document.getElementById("fichier").value,
		};
		// Send the msg object as a JSON-formatted string.
		socket.send(JSON.stringify(msg));
		// Blank the text input element, ready to receive the next line of text from the user.
		document.getElementById("fichier").value = "";
		}

		socket.onmessage = function(event) {
		var f = document.getElementById("fichier").contentDocument;
		var msg = JSON.parse(event.data);

		if (msg.length) {
		f.write(msg);
		document.getElementById("fichier").contentWindow
				.scrollByPages(1);
		}
		};*/
	</script>

</body>
</html>