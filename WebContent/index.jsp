<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/index.css" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!--
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

Latest compiled and minified JavaScript
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

(Optional) Latest compiled and minified JavaScript translation files
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script> -->

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.signIn" var="signIn" />
<fmt:message bundle="${loc}" key="local.singUp" var="singUp" />
<%-- <fmt:message bundle="${loc}" key="local.logOut" var="logOut" /> --%>

<fmt:message bundle="${loc}" key="local.searchVacancies" var="searchVacancies" />
<fmt:message bundle="${loc}" key="local.searchResumes" var="searchResumes" />
<fmt:message bundle="${loc}" key="local.search" var="search" />

<fmt:message bundle="${loc}" key="local.welcomeToHR" var="welcomeToHR" />
<fmt:message bundle="${loc}" key="local.countResumes" var="countResumes" />
<fmt:message bundle="${loc}" key="local.countVacancies" var="countVacancies" />
<fmt:message bundle="${loc}" key="local.countCompanies" var="countCompanies" />

<fmt:message bundle="${loc}" key="local.look" var="look" />
<fmt:message bundle="${loc}" key="local.ru" var="ru" />
<fmt:message bundle="${loc}" key="local.en" var="en" />
<fmt:message bundle="${loc}" key="local.createResume" var="createResume" />
<fmt:message bundle="${loc}" key="local.createVacancy" var="createVacancy" />


<fmt:message bundle="${loc}" key="local.enterName" var="enterName" />
<fmt:message bundle="${loc}" key="local.enterSurname" var="enterSurname" />
<fmt:message bundle="${loc}" key="local.enterPatronymic" var="enterPatronymic" />
<fmt:message bundle="${loc}" key="local.enterDateOfBirthday" var="enterDateOfBirthday" />
<fmt:message bundle="${loc}" key="local.enterEmail" var="enterEmail" />
<fmt:message bundle="${loc}" key="local.enterPhone" var="enterPhone" />
<fmt:message bundle="${loc}" key="local.enterLogin" var="enterLogin" />
<fmt:message bundle="${loc}" key="local.enterPassword" var="enterPassword" />
<fmt:message bundle="${loc}" key="local.repeatPassword" var="repeatPassword" />
<fmt:message bundle="${loc}" key="local.cancel" var="cancel" />
<fmt:message bundle="${loc}" key="local.applicant" var="applicant" />
<fmt:message bundle="${loc}" key="local.employer" var="employer" />

<title>HR System</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<ul class="nav nav-tabs">
					<li><a class="navbar-brand" href="#">HR System</a></li>
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
				<div class="navbar-right">

					<form class="navbar-form" action="ControllerServlet" method="post">
					<input name="command" value="authorization" type="hidden" />
						<div class="form-group">
							<input type="text" placeholder="Login" class="form-control"
								name="login" value="">
						</div>
						<div class="form-group">
							<input type="password" placeholder="Password"
								class="form-control" name="password" value="">
						</div>
						<button type="submit" class="btn btn-success"> ${signIn} </button>
						<a href="#popup-registration" class="btn btn-success"> ${singUp} </a>
					</form>
				</div>
				<div class="navbar-right">
					<form class="navbar-form" action="ControllerServlet" method="post">
						<input name="command" value="change-local" type="hidden" />
						<button type="submit" class="btn btn-link" name="local" value="en"> ${en}</button>
						<button type="submit" class="btn btn-link" name="local" value="ru"> ${ru}</button>
					</form>
				</div>
			</div>
		</div>
	</nav>
	<!-- BACKGROUNG IMAGE -->
	<div class="container jumbotron background-img">
		<div class="topic-button-placing">
			<h2 class="page-header">${welcomeToHR}</h2>
			<a href="creatingResume.html"
				class="btn btn-success button-img-placing"> <!-- <button type="submit">Создать
				резюме</button> --> ${createResume}
			</a> <a class="btn btn-success button-img-placing"
				href="creatingVacancy.html"> <!-- <button type="submit" class="btn btn-success button-img-placing">  -->
				${createVacancy}<!-- </button> -->
			</a>
		</div>
		<div class="vertical-line"></div>

		<div class="mark-informtion">
			<div class="number-value">50</div>
			<div class="text-value">${countResumes}</div>
		</div>
		<div class="mark-informtion">
			<div class="number-value">68</div>
			<div class="text-value">${countVacancies}</div>
		</div>
		<div class="mark-informtion">
			<div class="number-value">40</div>
			<div class="text-value">${countCompanies}</div>
		</div>
	</div>
	<!-- LIST VACANCIES -->
	<section class="section-placing">
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="caption">
						<h3>Junior Java Developer</h3>
						<h4>Черников Иван</h4>
						<p>Пример описания резюме!!!in, egestas eget quam. Donec id
							elit non mi porta gravida at eget metus. Nullam id dolor id nibh
							ultricies vehicula ut id elit.</p>

						<a href="resume.html" class="btn btn-success" role="button">${look}</a>

					</div>
				</div>
			</div>

			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="caption">
						<h3>ASP.NET Tem Leader</h3>
						<h4>Иванов Петр</h4>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>

						<a href="#" class="btn btn-success" role="button">${look}</a>

					</div>
				</div>
			</div>

			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="caption">
						<h3>Middle Frontend Developer</h3>
						<h4>Бранцевич Александр</h4>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>

						<a href="#" class="btn btn-success" role="button">${look}</a>

					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="caption">
						<h3>Senior Java Developer</h3>
						<h4>Жуков Иван</h4>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>

						<a href="#" class="btn btn-success" role="button">${look}</a>

					</div>
				</div>
			</div>

			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="caption">
						<h3>Senior Python Developer</h3>
						<h4>Горбунов Иван</h4>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>

						<a href="#" class="btn btn-success" role="button">${look}</a>

					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- POPUP SING UP -->
	<c:choose>
		<c:when test="${not empty requestScope.errorMessage}">

			<a href="" class="overlay" style="display: block"></a>
			<div class="regestration-window" style="display: block">
		</c:when>
		<c:otherwise>
			<a href="" id="popup-registration" class="overlay"></a>
			<div class="regestration-window thumbnail">
		</c:otherwise>
	</c:choose>
	<form role="form" action="ControllerServlet" method="post">

		<c:if test="${not empty requestScope.errorMessage}">

			<div class="form-group alert alert-danger">
				<strong>${errorMessage}</strong>
			</div>

		</c:if>

		<input name="command" value="registration" type="hidden" />

		<div class="form-group">
			<label for="name">*${enterName}:</label> <input type="text"
				class="form-control" placeholder="Enter name" id="name" name="name"
				required>
		</div>

		<div class="form-group">
			<label for="surname">*${enterSurname}:</label> <input type="text"
				class="form-control" placeholder="Enter surname" id="surname"
				name="surname" required>
		</div>

		<div class="form-group">
			<label for="patronymic">${enterPatronymic}:</label> <input type="text"
				class="form-control" placeholder="Enter patronymic" id="patronymic"
				name="patronymic">
		</div>

		<div class="form-group">
			<label for="email">*${enterEmail}:</label> <input type="email"
				class="form-control" placeholder="Enter email" id="email"
				name="email" required>
		</div>

		<div class="form-group">
			<label for="dateOfBirthday">*${enterDateOfBirthday}:</label> <input
				type="date" class="form-control"
				placeholder="Enter date of birthday" id="dateOfBirthday"
				name="dateOfBirthday" required>
		</div>

		<div class="form-group">
			<label for="phoneNumber">${enterPhone}:</label> <input
				type="text" class="form-control" placeholder="Enter phone number"
				id="phoneNumber" name="phoneNumber">
		</div>

		<div class="form-group">
			<label for="patronymic">*${enterLogin}:</label> <input type="text"
				class="form-control" placeholder="Enter login" id="login"
				pattern="[\w]+" name="login" required>
		</div>

		<div class="form-group">
			<label for="pwd">* ${enterPassword}: </label> <input type="password"
				class="form-control" placeholder="Enter password" id="pwd"
				name="password" required>
		</div>

		<div class="form-group">
			<label for="pwd-repeat">*${repeatPassword}:</label> <input
				type="password" class="form-control" placeholder="Repeat password"
				id="pwd-repeat" name="repeatedPassword" required>
		</div>

		<div class="form-group">
			<select class="selectpicker" name="role">
				<option> ${applicant} </option>
				<option> ${employer} </option>
			</select>
		</div>
		<button type="submit" class="btn btn-success"> ${singUp} </button>
		<a href="index.jsp" class="btn btn-success"> ${cancel} </a>
	</form>
	</div>

	<!-- FOOTER -->
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>