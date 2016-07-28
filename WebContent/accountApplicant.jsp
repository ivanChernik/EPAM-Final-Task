<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/account.css" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.logOut" var="logOut" />
<fmt:message bundle="${loc}" key="local.createResume" var="createResume" />

<fmt:message bundle="${loc}" key="local.searchVacancies" var="searchVacancies" />
<fmt:message bundle="${loc}" key="local.searchResumes" var="searchResumes" />
<fmt:message bundle="${loc}" key="local.search" var="search" />
<fmt:message bundle="${loc}" key="local.tableStatusFeedback" var="tableStatusFeedback" />
<fmt:message bundle="${loc}" key="local.company" var="company" />
<fmt:message bundle="${loc}" key="local.status" var="status" />
<fmt:message bundle="${loc}" key="local.date" var="date" />

<title>Account Applicant</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<ul class="nav nav-tabs">
					<li><a class="navbar-brand" href="index.html">HR System</a></li>
					<li><a class="navbar-brand" href="creatingResume.html"> ${createResume}</a></li>
					<li role="presentation" class="dropdown"><a
						class="dropdown-toggle dropdown-button-color"
						data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
						aria-expanded="false"> ${search} <span class="caret"></span>
					</a>
						<ul class="dropdown-menu dropdown-button-style">
							<li><a href="searchingVacancy.html">${searchVacancies}</a></li>
							<li><a href="searchingResume.html">${searchResumes}</a></li>
						</ul></li>
				</ul>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<form class="navbar-form navbar-right">
					<button type="submit" class="btn btn-success">${logOut}</button>
				</form>
			</div>
		</div>
	</nav>
	<section>
		<div class="thumbnail table-information">
			<table class="table">
				<caption> ${tableStatusFeedback}</caption>
				<thead>
					<tr>
						<th> ${company}</th>
						<th> ${status}</th>
						<th> ${date}</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>EPAM</td>
						<td>Просмотрен</td>
						<td>21.01.2016</td>
					</tr>
					<tr>
						<td>Issoft</td>
						<td>Отказ</td>
						<td>22.02.2016</td>
					</tr>
					<tr>
						<td>Эфтех</td>
						<td>Не просмотрен</td>
						<td>01.05.2016</td>
					</tr>
				</tbody>
			</table>
		</div>

	</section>
	<!-- FOOTER -->
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>