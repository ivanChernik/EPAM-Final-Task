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
<meta charset="UTF-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.add.education"
	var="addEducation" />
<fmt:message bundle="${loc}" key="local.add.education.to.resume" var="addEducationToResume" />
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

<title>Addiction education to resume</title>
</head>
<body>

	<c:set var="pageName" value="addictionEducation.jsp" scope="session" />

	<jsp:include page="navigation.jsp"></jsp:include>
	<section class="section-information">

		<form action="Controller" role="form" method="post"
			enctype="multipart/form-data">

			<input type="hidden" name="command" value="add-education">

			<div class="thumbnail input-information">
				<c:if test="${not empty requestScope.errormessages}">
					<div class="form-group alert alert-danger">
					<c:set var="education" value="${requestScope.education}" scope="page" />
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span><strong>${requestScope.errormessages}</strong>
					</div>
				</c:if>
				<h4 class="page-header resume-item-header">${addEducationToResume}</h4>


				<div class="form-group">
					<select class="selectpicker" name="kind-education">
						<option value="Higher">${higherEducation}</option>
						<option value="Incomplete-higher">
							${incompleteHigherEducation}</option>
						<option value="Specialized-secondary">
							${specializedSecondaryEducation}</option>
						<option value="Average">${averageEducation}</option>
						<option value="Vocational-technical">
							${vocationalTechnicalEducation}</option>
					</select>
				</div>


				<div class="form-group">
					<label for="university"> ${nameEducationInstitution}:</label> <input
						type="text" placeholder="${nameEducationInstitution}"
						class="form-control" id="university" name="university"
						value="${education.university}">
				</div>

				<div class="form-group">
					<label for="faculty"> ${nameFaculty}:</label> <input type="text"
						placeholder="${nameFaculty}" class="form-control" id="faculty"
						name="faculty" value="${education.faculty}">
				</div>

				<div class="form-group">
					<label for="specialty"> ${nameSpecialty}:</label> <input
						type="text" placeholder="${nameSpecialty}" class="form-control"
						id="specialty" name="specialty"
						value="${education.specialty}">
				</div>


				<div class="form-group">
					<select class="selectpicker" name="form-education">
						<option value="Full-form">${fullForm}</option>
						<option value="Part-form">${partForm}</option>
						<option value="Distant-form">${distantForm}</option>
					</select>
				</div>

				<div class="form-group">
					<label for="period-education">${periodEducation}:</label> <input
						type="date" placeholder="From" class="form-control"
						id="period-education" name="educationFrom"
						value="${requestScope.educationFrom}">
				</div>

				<div class="form-group">
					<input type="date" placeholder="From" class="form-control"
						id="period-education" name="educationTo"
						value="${requestScope.educationTo}">
				</div>

				<div class="form-group">
					<label for="education-description"> ${description}:</label>
					<textarea class="form-control" placeholder="${description}"
						rows="5" id="education-description" name="education-description">${education.educationDescription}</textarea>
				</div>
			</div>

			<button type="submit" class="btn btn-success">
				${addEducation}</button>
		</form>
	</section>
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />
</body>
</html>