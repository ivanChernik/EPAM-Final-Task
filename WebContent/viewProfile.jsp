<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/resume.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<meta charset="UTF-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.role" var="role" />
<fmt:message bundle="${loc}" key="local.date.birthday" var="dateOfBirthday" />
<fmt:message bundle="${loc}" key="local.delete.account" var="deleteAccount" />
<fmt:message bundle="${loc}" key="local.phone.number" var="phoneNumber" />
<fmt:message bundle="${loc}" key="local.email" var="email" />
<fmt:message bundle="${loc}" key="local.update.profile" var="updateProfile" />
<fmt:message bundle="${loc}" key="local.delete.account" var="deleteAccount" />

<title>View profile</title>
</head>
<body>
	<!-- NAVIGATION START-->

	<c:set var="pageName" value="viewProfile.jsp" scope="session" />
	<jsp:include page="components/navigation.jsp"></jsp:include>
	<c:set var="person" value="${sessionScope.person}" scope="page" />

	<section>
		<div class="thumbnail head-information">
			<h2>${person.surname}&nbsp;${person.name}&nbsp;${person.middleName}</h2>
			<p>${role}:&nbsp;${person.role}</p>
			<p>${dateOfBirthday}:&nbsp;${person.dateOfBirthday}</p>
			<p>${email}:&nbsp;${person.email}</p>
			<p>${phoneNumber}:&nbsp;${person.phone}</p>
				<a role="button" type="submit" class="btn btn-success" href="updateProfile.jsp">${updateProfile}</a>
		</div>
	</section>
	<!-- FOOTER -->
	<jsp:include page="components/footer.jsp" />
</body>
</html>