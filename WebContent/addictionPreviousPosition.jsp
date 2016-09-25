<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/create.css" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>
<meta charset="UTF-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.general.information"
	var="generalInformation" />
<fmt:message bundle="${loc}" key="local.prefed.position"
	var="prefedPosition" />
<fmt:message bundle="${loc}" key="local.upload.photo" var="uploadPhoto" />
<fmt:message bundle="${loc}" key="local.professional.information"
	var="professionalInformation" />
<fmt:message bundle="${loc}" key="local.skills" var="skills" />
<fmt:message bundle="${loc}" key="local.education" var="education" />
<fmt:message bundle="${loc}" key="local.higher.education"
	var="higherEducation" />
<fmt:message bundle="${loc}" key="local.incomplete.higher.education"
	var="incompleteHigherEducation" />
<fmt:message bundle="${loc}" key="local.average.education"
	var="averageEducation" />
<fmt:message bundle="${loc}" key="local.specialized.secondary.education"
	var="specializedSecondaryEducation" />
<fmt:message bundle="${loc}" key="local.vocational.technical.education"
	var="vocationalTechnicalEducation" />
<fmt:message bundle="${loc}" key="local.name.education.institution"
	var="nameEducationInstitution" />
<fmt:message bundle="${loc}" key="local.name.faculty" var="nameFaculty" />
<fmt:message bundle="${loc}" key="local.name.specialty"
	var="nameSpecialty" />
<fmt:message bundle="${loc}" key="local.experience" var="experience" />
<fmt:message bundle="${loc}" key="local.description" var="description" />
<fmt:message bundle="${loc}" key="local.full.form" var="fullForm" />
<fmt:message bundle="${loc}" key="local.part.form" var="partForm" />
<fmt:message bundle="${loc}" key="local.distant.form" var="distantForm" />
<fmt:message bundle="${loc}" key="local.period.education"
	var="periodEducation" />
<fmt:message bundle="${loc}" key="local.previous.position"
	var="previousPosition" />
<fmt:message bundle="${loc}" key="local.period.work" var="periodWork" />
<fmt:message bundle="${loc}" key="local.contact.data" var="contactData" />
<fmt:message bundle="${loc}" key="local.email" var="email" />
<fmt:message bundle="${loc}" key="local.phone.number" var="phoneNumber" />
<fmt:message bundle="${loc}" key="local.address" var="address" />
<fmt:message bundle="${loc}" key="local.create.resume"
	var="createResume" />
<fmt:message bundle="${loc}" key="local.link.google.plus"
	var="linkGooglePlus" />
<fmt:message bundle="${loc}" key="local.link.linkedin"
	var="linkLinkedin" />
<fmt:message bundle="${loc}" key="local.link.twitter" var="linkTwitter" />
<fmt:message bundle="${loc}" key="local.link.facebook"
	var="linkFacebook" />
<fmt:message bundle="${loc}" key="local.add"
	var="add" />
<fmt:message bundle="${loc}" key="local.add.previous.position.to.resume"
	var="addPreviousPositionToResume" />	


<title>Addiction previous position</title>
</head>
<body>

	<c:set var="pageName" value="creationResume.jsp" scope="session" />

	<jsp:include page="navigation.jsp"></jsp:include>
	<section class="section-information">

		<c:set var="resume" value="${requestScope.resume}" scope="page" />

		<form action="Controller" role="form" method="post"
			enctype="multipart/form-data">

			<input type="hidden" name="command" value="add-previous-position">

			<div class="thumbnail input-information">
				<c:if test="${not empty requestScope.errormessages}">
					<div class="form-group alert alert-danger">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span><strong>${requestScope.errormessages}</strong>
					</div>
				</c:if>

				<h4 class="page-header resume-item-header">${addPreviousPositionToResume}</h4>
				<div class="form-group">
					<label for="previos-position">${previousPosition}:</label> <input
						type="text" placeholder="${previousPosition}" class="form-control"
						id="previos-position" name="previos-position"
						value="${requestScope.prevPosition.previousPosition}" required>
				</div>

				<div class="form-group">
					<label for="period-work">${periodWork}</label> <input type="date"
						placeholder="From" class="form-control" id="workFrom"
						name="workFrom" value="${requestScope.workFrom}" required>
				</div>

				<div class="form-group">
					<input type="date" placeholder="To" class="form-control"
						id="period-work" name="workTo" value="${requestScope.workTo}" required>
				</div>

				<div class="form-group">
					<label for="work-description"> ${description}:</label>
					<textarea class="form-control" placeholder="${description}"
						rows="5" id="work-description" name="work-description" required>${requestScope.prevPosition.workDescription}</textarea>
				</div>

			</div>

			<button type="submit" name="createResume" class="btn btn-success">
				${add}</button>
		</form>
	</section>
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />
</body>
</html>