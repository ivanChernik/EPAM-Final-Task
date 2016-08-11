<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<fmt:message bundle="${loc}" key="local.professional.information" var="professionalInformation" />
<fmt:message bundle="${loc}" key="local.phone.number" var="phoneNumber" />
<fmt:message bundle="${loc}" key="local.email" var="email" />
<fmt:message bundle="${loc}" key="local.skills" var="skills" />
<fmt:message bundle="${loc}" key="local.experience" var="experience" /> 
<fmt:message bundle="${loc}" key="local.education" var="education" /> 


<title>Resume</title>
</head>
<body>
	<!-- NAVIGATION START-->
	<jsp:include page="navigation.jsp"></jsp:include>

	<c:set var="resume" value="${requestScope.resume}" scope="page" />

	<section>
		<div class="thumbnail head-information">
			<div class="placing-inline main-information">
				<div class="position-head">${resume.position}</div>
				<div class="name">${resume.person.name}&nbsp;
					${resume.person.surname}</div>

				<div class="location">${resume.contactInfo.address}</div>

				<div class="contact-information">
					<div>${email}: &nbsp; ${resume.contactInfo.email}</div>
					<div>${phoneNumber}: &nbsp; ${resume.contactInfo.phone}</div>
				</div>

				<div class="social-network-placing">

					<c:if test="${not empty resume.contactInfo.linkGooglePlus}">
						<a class="social-network"
							href="${resume.contactInfo.linkGooglePlus}" target="_blank"><img
							src="myStyle/img/google.png" alt="google+ account"></a>
					</c:if>

					<c:if test="${not empty resume.contactInfo.linkLinkedIn}">
						<a class="social-network"
							href="${resume.contactInfo.linkLinkedIn}" target="_blank"><img
							src="myStyle/img/linkedin.png" alt="linkedin account"></a>
					</c:if>

					<c:if test="${not empty resume.contactInfo.linkTwitter}">
						<a class="social-network" href="${resume.contactInfo.linkTwitter}"
							target="_blank"><img src="myStyle/img/twitter.png"
							alt="twitter account"></a>
					</c:if>

					<c:if test="${not empty resume.contactInfo.linkFacebook}">
						<a class="social-network"
							href="${resume.contactInfo.linkFacebook}" target="_blank"><img
							src="myStyle/img/facebook.png" alt="facebook account"></a>
					</c:if>
				</div>
			</div>
			<div class="placing-inline">
				<img class="img-rounded profile-photo" src="${resume.pathImage}"
					alt="photo for resume">
			</div>
		</div>


		<c:if test="${not empty resume.profInformation}">
			<div class="thumbnail section-information ">
				<h4 class="page-header"> ${professionalInformation}</h4>

				<p>${resume.profInformation}</p>
			</div>
		</c:if>

		<c:if test="${not empty resume.skill}">
			<div class="thumbnail section-information">
				<h4 class="page-header"> ${skills}</h4>

				<p>${resume.skill}</p>
			</div>
		</c:if>


		<c:if test="${not empty resume.previousWorkList}">

			<div class="thumbnail section-information">
				<h4 class="page-header">${experience}</h4>

				<c:forEach var="position" items="${resume.previousWorkList}">

					<div>
						<div class="subtitle">${position.previousPosition}</div>
						<p>${position.workFrom} &nbsp; - &nbsp;
							${position.workTo}</p>
						<p>${position.workDescription}</p>
					</div>

				</c:forEach>
			</div>

		</c:if>

		<c:if test="${not empty resume.educationList}">

			<div class="thumbnail section-information">
				<h4 class="page-header"> ${education}</h4>

				<c:forEach var="education" items="${resume.educationList}">
					<div>
						<div class="subtitle">${education.university}</div>
						<p>${education.educationFrom} &nbsp; - &nbsp;
							${education.educationTo}</p>
						<p>${education.faculty}.${education.specialty}.${education.formEducation}.${education.educationDescription}.</p>
					</div>
				</c:forEach>
			</div>

		</c:if>

	</section>
	<!-- FOOTER -->
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>