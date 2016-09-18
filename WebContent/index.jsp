<%@ page info="index.jsp" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.sing.up.index" var="singUpIndex" />
<fmt:message bundle="${loc}" key="local.create.resume.index"
	var="createResumeIndex" />
<fmt:message bundle="${loc}" key="local.create.vacancy.index"
	var="createVacancyIndex" />

<fmt:message bundle="${loc}" key="local.welcome.to.hr.system"
	var="welcomeToHR" />
<fmt:message bundle="${loc}" key="local.count.resumes"
	var="countResumes" />
<fmt:message bundle="${loc}" key="local.count.vacancies"
	var="countVacancies" />
<fmt:message bundle="${loc}" key="local.count.companies"
	var="countCompanies" />

<fmt:message bundle="${loc}" key="local.look" var="look" />

<fmt:message bundle="${loc}" key="local.enter.name" var="enterName" />
<fmt:message bundle="${loc}" key="local.enter.surname"
	var="enterSurname" />
<fmt:message bundle="${loc}" key="local.enter.patronymic"
	var="enterPatronymic" />
<fmt:message bundle="${loc}" key="local.enter.date.of.birthday"
	var="enterDateOfBirthday" />
<fmt:message bundle="${loc}" key="local.enter.email" var="enterEmail" />
<fmt:message bundle="${loc}" key="local.enter.phone" var="enterPhone" />
<fmt:message bundle="${loc}" key="local.enter.login" var="enterLogin" />
<fmt:message bundle="${loc}" key="local.enter.password"
	var="enterPassword" />
<fmt:message bundle="${loc}" key="local.repeat.password"
	var="repeatPassword" />
<fmt:message bundle="${loc}" key="local.cancel" var="cancel" />
<fmt:message bundle="${loc}" key="local.applicant" var="applicant" />
<fmt:message bundle="${loc}" key="local.employer" var="employer" />



<title>HR System</title>
</head>
<body>
	<c:set var="pageName" value="index.jsp" scope="session" />

	<!-- NAVIGATION START-->
	<jsp:include page="navigation.jsp"></jsp:include>
	<!-- NAVIGATION END -->


	<jsp:include page="${request.contextPath}/Controller">
		<jsp:param name="command" value="show-top-vacancies" />
	</jsp:include>


	<!-- BACKGROUNG IMAGE -->
	<div class="container jumbotron background-img">
		<div class="topic-button-placing">
			<h2 class="page-header">${welcomeToHR}</h2>
		</div>
		<div class="vertical-line"></div>

		<div class="mark-informtion">
			<div class="number-value">${requestScope.countResumes}</div>
			<div class="text-value">${countResumes}</div>
		</div>
		<div class="mark-informtion">
			<div class="number-value">${requestScope.countVacancies}</div>
			<div class="text-value">${countVacancies}</div>
		</div>
		<div class="mark-informtion">
			<div class="number-value">${requestScope.countCompanies}</div>
			<div class="text-value">${countCompanies}</div>
		</div>
	</div>
	<!-- LIST VACANCIES START -->
	<section class="section-placing">
		<div class="row">
			<c:set var="countVacancies" value="${0}" scope="page" />
			<c:forEach var="vacancy" items="${requestScope.vacancyList}">

				<form action="Controller" method="get">
					<input name="command" value="show-vacancy" type="hidden"> <input
						name="idVacancy" value="${vacancy.id}" type="hidden">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<div class="caption">
								<h3>${vacancy.name}</h3>
								<h4>${vacancy.salary}$</h4>
								<p>${vacancy.shortDescription}</p>
								<button type="submit" class="btn btn-success">${look}</button>
							</div>
						</div>
					</div>
				</form>
				<c:set var="countVacancies" value="${countVacancies + 1}"
					scope="page" />
				<c:if test="${(countVacancies % 3) == 0}">
		</div>
		<div class="row">
			</c:if>
			</c:forEach>
		</div>
	</section>
	<!-- LIST VACANCIES END -->
	<!-- POPUP SING UP START-->
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
	<form role="form" action="Controller" method="post">

		<c:if test="${not empty requestScope.errorMessage}">

			<div class="form-group alert alert-danger">
				<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span><strong>${errorMessage}</strong>
			</div>

		</c:if>

		<input name="command" value="registration" type="hidden" />

		<div class="form-group">
			<label for="name"><span class="required-field">*</span>${enterName}:</label> <input type="text"
				class="form-control" placeholder="${enterName}" id="name"
				name="name" required value="${requestScope.name}">
		</div>

		<div class="form-group">
			<label for="surname"><span class="required-field">*</span>${enterSurname}:</label> <input type="text"
				class="form-control" placeholder="${enterSurname}" id="surname"
				name="surname" required value="${requestScope.surname}">
		</div>

		<div class="form-group">
			<label for="middleName">${enterPatronymic}:</label> <input
				type="text" class="form-control" placeholder="${enterPatronymic}"
				id="middleName" name="middleName" value="${requestScope.middleName}">
		</div>

		<div class="form-group">
			<label for="email"><span class="required-field">*</span>${enterEmail}:</label> <input type="email"
				class="form-control" placeholder="${enterEmail}" id="email"
				name="email" required value="${requestScope.email}">
		</div>

		<div class="form-group">
			<label for="dateOfBirthday"><span class="required-field">*</span>${enterDateOfBirthday}:</label> <input
				type="date" class="form-control"
				placeholder="${enterDateOfBirthday}" id="dateOfBirthday"
				name="dateOfBirthday" required
				value="${requestScope.dateOfBirthday}">
		</div>

		<div class="form-group">
			<label for="phoneNumber">${enterPhone}:</label> <input type="text"
				class="form-control" placeholder="${enterPhone}" id="phoneNumber"
				name="phoneNumber" value="${requestScope.phoneNumber}">
		</div>

		<div class="form-group">
			<label for="patronymic"><span class="required-field">*</span>${enterLogin}:</label> <input type="text"
				class="form-control" placeholder="${enterLogin}" id="login"
				pattern="[\w]+" name="login" required value="${requestScope.login}">
		</div>

		<div class="form-group">
			<label for="pwd"><span class="required-field">*</span>${enterPassword}: </label> <input type="password"
				class="form-control" placeholder="${enterPassword}" id="pwd"
				name="password" required value="${requestScope.password}">
		</div>

		<div class="form-group">
			<label for="pwd-repeat"><span class="required-field">*</span>${repeatPassword}:</label> <input
				type="password" class="form-control" placeholder="${repeatPassword}"
				id="pwd-repeat" name="repeatedPassword" required
				value="${requestScope.repeatedPassword}">
		</div>

		<div class="form-group">
			<select class="selectpicker" name="role">
				<option value="applicant">${applicant}</option>
				<option value="hr">${employer}</option>
			</select>
		</div>
		<button type="submit" class="btn btn-success">${singUpIndex}</button>
		<a href="index.jsp" class="btn btn-success"> ${cancel} </a>
	</form>
	</div>
	<!-- POPUP SING UP END-->
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />

</body>
</html>