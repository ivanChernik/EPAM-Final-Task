<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/vacancy.css" type="text/css">

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Vacancy</title>
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
	<section class="main-information">
		<form action="Controller" method="post">
		
			<input name="command" value="responce-to-vacancy" type="hidden">
			 <input name="idVacancy" value="${requestScope.vacancy.id}" type="hidden">
			 
			<div class="thumbnail section-information ">
				<h1>${requestScope.vacancy.name}</h1>
				<h4>${requestScope.vacancy.companyName}</h4>
				<h4 class="page-header">Описание</h4>
				<p>${requestScope.vacancy.descrption}</p>
			</div>
			<div class="thumbnail section-information ">
				<h4 class="page-header">Требования</h4>
				<p>${requestScope.vacancy.requirement}</p>
			</div>
			<div class="thumbnail section-information ">
				<h4 class="page-header">Контактная информация</h4>
				<p>${requestScope.vacancy.contactInformation}</p>
			</div>
			<div class="thumbnail section-information ">
				<h4 class="page-header">Тип занятости</h4>
				<p>${requestScope.vacancy.employment}</p>
			</div>
			<div class="thumbnail section-information ">
				<h4 class="page-header">Заработная плата</h4>
				<p>${requestScope.vacancy.salary}y.e.</p>
			</div>
			<button type="submit" class="btn btn-success">Откликнуться</button>
		</form>
	</section>
	<!-- FOOTER -->
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>