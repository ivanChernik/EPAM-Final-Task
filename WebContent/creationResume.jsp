<%@ page info="creationResume.jsp" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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


<title>Create Resume</title>
</head>
<body>

	<c:set var="pageName" value="creationResume.jsp" scope="session" />

	<jsp:include page="navigation.jsp"></jsp:include>
	<section class="section-information">

		<c:set var="resume" value="${requestScope.resume}" scope="page" />

		<form action="Controller" role="form" method="post"
			enctype="multipart/form-data">

			<input type="hidden" name="command" value="create-resume">
			<div class="thumbnail input-information">
				<c:if test="${not empty requestScope.errormessages}">
					<div class="form-group alert alert-danger">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span><strong>${requestScope.errormessages}</strong>
					</div>
				</c:if>

				<h4 class="page-header resume-item-header">
					${generalInformation}</h4>
				<div class="form-group">
					<label for="position"><span class="required-field">*</span>${prefedPosition}:</label> <input type="text"
						placeholder="${prefedPosition}" class="form-control" id="position"
						name="position" value="${resume.position}" required>
				</div>

				<div class="form-group">
					<label for="photo"><span class="required-field">*</span>${uploadPhoto}:</label> <input
						type="file" name="photo" id="photo" accept="image/jpeg" required>
				</div>

				<div class="form-group">
					<label for="prof-infirmation"><span class="required-field">*</span>${professionalInformation}:</label>
					<textarea class="form-control"
						placeholder="${professionalInformation}" rows="5"
						id="prof-infirmation" name="prof-infirmation" required>${resume.profInformation}</textarea>
				</div>
				<div class="form-group">
					<label for="skill"><span class="required-field">*</span>${skills}:</label>
					<textarea class="form-control" placeholder="${skills}" rows="5"
						id="skill" name="skill" required>${resume.skill}</textarea>
				</div>
			</div>

			<div class="thumbnail input-information">
			<div class="checkbox">
					<label><input type="checkbox" name="doNotStudy" value="doNotStudy">Я нигде не учился</label>
				</div>
				<h4 class="page-header resume-item-header">${education}</h4>


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
					<label for="university"><span class="required-field">*</span>${nameEducationInstitution}:</label> <input
						type="text" placeholder="${nameEducationInstitution}"
						class="form-control" id="university" name="university"
						value="${resume.educationList.get(0).university}">
				</div>

				<div class="form-group">
					<label for="faculty"><span class="required-field">*</span>${nameFaculty}:</label> <input type="text"
						placeholder="${nameFaculty}" class="form-control" id="faculty"
						name="faculty" value="${resume.educationList.get(0).faculty}">
				</div>

				<div class="form-group">
					<label for="specialty"><span class="required-field">*</span>${nameSpecialty}:</label> <input
						type="text" placeholder="${nameSpecialty}" class="form-control"
						id="specialty" name="specialty"
						value="${resume.educationList.get(0).specialty}">
				</div>


				<div class="form-group">
					<select class="selectpicker" name="form-education">
						<option value="Full-form">${fullForm}</option>
						<option value="Part-form">${partForm}</option>
						<option value="Distant-form">${distantForm}</option>
					</select>
				</div>

				<div class="form-group">
					<label for="period-education"><span class="required-field">*</span>${periodEducation}:</label> <input
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
					<label for="education-description"><span class="required-field">*</span>${description}:</label>
					<textarea class="form-control" placeholder="${description}"
						rows="5" id="education-description" name="education-description">${resume.educationList.get(0).educationDescription}</textarea>
				</div>
			</div>

			<div class="thumbnail input-information">

				<div class="checkbox">
					<label><input type="checkbox" name="doNotWork" value="doNotWork">Я нигде не работал</label>
				</div>

				<h4 class="page-header resume-item-header">${experience}</h4>
				<div class="form-group">
					<label for="previos-position"><span class="required-field">*</span>${previousPosition}:</label> <input
						type="text" placeholder="${previousPosition}" class="form-control"
						id="previos-position" name="previos-position"
						value="${resume.previousWorkList.get(0).previousPosition}">
				</div>

				<div class="form-group">
					<label for="period-work"><span class="required-field">*</span>${periodWork}</label> <input type="date"
						placeholder="From" class="form-control" id="workFrom"
						name="workFrom" value="${requestScope.workFrom}">
				</div>

				<div class="form-group">
					<input type="date" placeholder="To" class="form-control"
						id="period-work" name="workTo" value="${requestScope.workTo}">
				</div>

				<div class="form-group">
					<label for="work-description"><span class="required-field">*</span>${description}:</label>
					<textarea class="form-control" placeholder="${description}"
						rows="5" id="work-description" name="work-description">${resume.previousWorkList.get(0).workDescription}</textarea>
				</div>

			</div>

			<div class="thumbnail input-information">
				<div class="form-group">
					<h4 class="page-header resume-item-header"><span class="required-field">*</span>${contactData}</h4>
					<label for="phone"><span class="required-field">*</span> ${phoneNumber}:</label> <input type="text"
						placeholder="${phoneNumber}" class="form-control" id="phone"
						name="phone" value="${resume.contactInfo.phone}" required>
				</div>

				<div class="form-group">
					<label for="email"><span class="required-field">*</span> ${email}:</label> <input type="email"
						class="form-control" id="email" placeholder="${email}"
						name="email" value="${resume.contactInfo.email}" required>
				</div>

				<div class="form-group">
					<label for="address"><span class="required-field">*</span>${address}:</label> <input type="text"
						class="form-control" id="address" placeholder="${address}"
						name="address" value="${resume.contactInfo.address}" required>
				</div>

				<div class="form-group">
					<label for="google-plus">${linkGooglePlus}:</label> <input
						type="text" class="form-control" id="google-plus"
						name="link-google-plus" placeholder="${linkGooglePlus}"
						value="${resume.contactInfo.linkGooglePlus}">
				</div>


				<div class="form-group">
					<label for="link-linkedin"> ${linkLinkedin}:</label> <input
						type="text" class="form-control" id="link-linkedin"
						name="link-linkedin" placeholder="${linkLinkedin}"
						value="${resume.contactInfo.linkLinkedIn}">
				</div>

				<div class="form-group">
					<label for="link-twitter"> ${linkTwitter}:</label> <input
						type="text" class="form-control" id="link-twitter"
						name="link-twitter" placeholder="${linkTwitter}"
						value="${resume.contactInfo.linkTwitter}">
				</div>
				<div class="form-group">
					<label for="link-facebook">${linkFacebook}:</label> <input
						type="text" class="form-control" id="link-facebook"
						name="link-facebook" placeholder="${linkFacebook}"
						value="${resume.contactInfo.linkFacebook}">
				</div>
			</div>
			<button type="submit" name="createResume" class="btn btn-success">
				${createResume}</button>
		</form>
	</section>
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />
</body>
</html>