<%@ page info="creationVacancy.jsp" language="java"
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
<fmt:message bundle="${loc}" key="local.title.vacancy"
	var="titleVacancy" />
<fmt:message bundle="${loc}" key="local.company.name" var="companyName" />
<fmt:message bundle="${loc}" key="local.contact.data" var="contactData" />
<fmt:message bundle="${loc}" key="local.full.employment"
	var="fullEmployment" />
<fmt:message bundle="${loc}" key="local.part.employment"
	var="partEmployment" />
<fmt:message bundle="${loc}" key="local.short.description"
	var="shortDescription" />
<fmt:message bundle="${loc}" key="local.description" var="description" />
<fmt:message bundle="${loc}" key="local.requirement" var="requirement" />
<fmt:message bundle="${loc}" key="local.salary" var="salary" />
<fmt:message bundle="${loc}" key="local.create.vacancy.index"
	var="createVacancyIndex" />

<title>Create vacancy</title>
</head>
<body>

	<c:set var="pageName" value="creationVacancy.jsp" scope="session" />

	<jsp:include page="components/navigation.jsp"></jsp:include>

	<section class="section-information">

		<form role="form" method="post" action="Controller">

			<input name="command" value="create-vacancy" type="hidden" />
			<div class="thumbnail input-information">
				 <c:if test="${not empty requestScope.errormessages}">
					<div class="form-group alert alert-danger">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span> <strong>
							${requestScope.errormessages}</strong>
					</div>
				</c:if> 
				<div class="form-group">
					<label for="titleVacancy"><span class="required-field">*</span>${titleVacancy}:</label>
					<input type="text" placeholder="${titleVacancy}"
						class="form-control" id="titleVacancy" name="titleVacancy" value="${requestScope.titleVacancy}" required>
				</div>
				<div class="form-group">
					<label for="companyName"><span class="required-field">*</span>${companyName}:</label>
					<input type="text" placeholder="${companyName}"
						class="form-control" id="companyName" name="companyName" value="${requestScope.companyName}" required>
				</div>

				<div class="form-group">
					<label for="contactData"><span class="required-field">*</span>${contactData}:</label>
					<input type="text" placeholder="${contactData}"
						class="form-control" id="contactData" name="contactData" value="${requestScope.contactData}" required>
				</div>

				<div class="form-group">
					<select class="selectpicker" name="employment">
						<option value="full-time">${fullEmployment}</option>
						<option value="part-time">${partEmployment}</option>
					</select>
				</div>

				<div class="form-group">
					<label for="shortDescription"><span class="required-field">*</span>${shortDescription}:</label>
					<textarea class="form-control" placeholder="${shortDescription}"
						rows="5" id="shortDescription" name="shortDescription" required>${requestScope.shortDescription}</textarea>
				</div>

				<div class="form-group">
					<label for="description"><span class="required-field">*</span>${description}:</label>
					<textarea class="form-control" placeholder="${description}"
						rows="5" id="description" name="description" required>${requestScope.description}</textarea>
				</div>
				<div class="form-group">
					<label for="requirement"><span class="required-field">*</span>${requirement}:</label>
					<textarea class="form-control" placeholder="${requirement}"
						rows="5" id="requirement" name="requirement" required>${requestScope.requirement}</textarea>
				</div>

				<div class="form-group">
					<label for="salary"><span class="required-field">*</span>${salary}<span
						class="glyphicon glyphicon-usd" aria-hidden="true"></span>: </label> <input
						type="text" placeholder="${salary}" class="form-control"
						id="salary" name="salary" value="${requestScope.salary}" required>
				</div>
			</div>

			<button type="submit" name="createVacancy" class="btn btn-success">
				${createVacancyIndex}</button>
		</form>
	</section>
	<!-- FOOTER -->
	<jsp:include page="components/footer.jsp" />

</body>
</html>