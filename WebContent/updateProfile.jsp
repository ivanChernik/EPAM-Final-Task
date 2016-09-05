<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/update.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<meta charset="UTF-8">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.update.profile"
	var="updateProfile" />
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
<fmt:message bundle="${loc}" key="local.enter.new.profile.data"
	var="enterNewProfileData" />
<fmt:message bundle="${loc}" key="local.cancel"
	var="cancel" />	

<title>Update profile</title>
</head>
<body>

	<c:if test="${empty requestScope.errorMessage}">
		<c:set var="personSession" value="${sessionScope.person}" scope="page" />
	</c:if>

	<jsp:include page="navigation.jsp"></jsp:include>

	<c:set var="pageName" value="updateProfile.jsp" scope="session" />

	<section class="section-information">

		<form role="form" method="post" action="Controller">
			<div class="thumbnail input-information">
				<h4 class="page-header item-header">${enterNewProfileData}</h4>
				<c:if test="${not empty requestScope.errorMessage}">

					<div class="form-group alert alert-danger">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span><strong>${errorMessage}</strong>
					</div>

				</c:if>

				<input name="command" value="update-profile" type="hidden" />

				<div class="form-group">
					<label for="name">${enterName}:</label> <input type="text"
						class="form-control" placeholder="${enterName}" id="name"
						name="name" required value="${personSession.name}${requestScope.name}">
				</div>

				<div class="form-group">
					<label for="surname">${enterSurname}:</label> <input type="text"
						class="form-control" placeholder="${enterSurname}" id="surname"
						name="surname" required
						value="${personSession.surname}${requestScope.surname}">
				</div>

				<div class="form-group">
					<label for="middleName">${enterPatronymic}:</label> <input
						type="text" class="form-control" placeholder="${enterPatronymic}"
						id="middleName" name="middleName"
						value="${personSession.middleName}${requestScope.middleName}">
				</div>

				<div class="form-group">
					<label for="email">${enterEmail}:</label> <input type="email"
						class="form-control" placeholder="${enterEmail}" id="email"
						name="email" required value="${personSession.email}${requestScope.email}">
				</div>

				<div class="form-group">
					<label for="dateOfBirthday">${enterDateOfBirthday}:</label> <input
						type="date" class="form-control"
						placeholder="${enterDateOfBirthday}" id="dateOfBirthday"
						name="dateOfBirthday" required
						value="${personSession.dateOfBirthday}${requestScope.dateOfBirthday}">
				</div>

				<div class="form-group">
					<label for="phoneNumber">${enterPhone}:</label> <input type="text"
						class="form-control" placeholder="${enterPhone}" id="phoneNumber"
						name="phoneNumber"
						value="${personSession.phone}${requestScope.phoneNumber}">
				</div>

				<input name="command" value="update-profile" type="hidden" />
				<div class="form-group">
					<button type="submit" class="btn btn-success">${updateProfile}</button>
					<a role="button" class="btn btn-danger" href="viewProfile.jsp"> ${cancel}</a>
				</div>
			</div>
		</form>
	</section>
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />

</body>
</html>