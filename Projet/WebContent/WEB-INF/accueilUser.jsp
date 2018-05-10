<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<p>fichier public ?</p>
		<form method="post" action="AjouterFichier"
			enctype="multipart/form-data">
			> <input type="file" value="Charger" name="fichier" /> <input
				type="submit" value="Charger" />
		</form>
	</div>

	<div>
		<ul>
			<c:forEach items="${liste}" var="current">
				<li><span>
						<form method="post" action="ChoixFichier">
							<input type="submit" value="${current}" name="fichier" />
						</form>
				</span></li>
			</c:forEach>
		</ul>


	</div>

</body>
</html>