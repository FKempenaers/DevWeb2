<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Saisie</title>
</head>
<body>

			<form method="post" action="Tchatche" target = "haut">

       <label for="message"> Message :</label>
       <input type="text" name="message" id="message" />

	<input type="submit" name="Envoyer"  value="Envoyer"/>
	
		</form>
</body>
</html>