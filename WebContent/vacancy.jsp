<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.description" var="description" />
<fmt:message bundle="${loc}" key="local.requirement" var="requirement" />
<fmt:message bundle="${loc}" key="local.contact.data" var="contactData" />	
<fmt:message bundle="${loc}" key="local.salary" var="salary" />	
<fmt:message bundle="${loc}" key="local.send.resume" var="sendResume" />
<fmt:message bundle="${loc}" key="local.employment" var="employment" />

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
				<h4 class="page-header"> ${description}</h4>
				<p>${requestScope.vacancy.descrption}</p>
			</div>
			<div class="thumbnail section-information ">
				<h4 class="page-header"> ${requirement}</h4>
				<p>${requestScope.vacancy.requirement}</p>
			</div>
			<div class="thumbnail section-information ">
				<h4 class="page-header"> ${contactData}</h4>
				<p>${requestScope.vacancy.contactInformation}</p>
			</div>
			<div class="thumbnail section-information ">
				<h4 class="page-header"> ${employment}</h4>
				<p>${requestScope.vacancy.employment}</p>
			</div>
			<div class="thumbnail section-information ">
				<h4 class="page-header"> ${salary}</h4>
				<p>${requestScope.vacancy.salary} &nbsp; y.e.</p>
			</div>
			<button type="submit" class="btn btn-success"> ${sendResume}</button>
		</form>
	</section>
	<!-- FOOTER -->
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>