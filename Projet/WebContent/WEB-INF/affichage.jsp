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

	<div class="center" id="messages"></div>


	<script>
		var source = new EventSource("AfficheMessages");
		source.onmessage = function(event) {
			document.getElementById('messages').innerHTML = event.data;
		};
	</script>
</body>
</html>