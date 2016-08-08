<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<fmt:message bundle="${loc}" key="local.titleVacancy" var="titleVacancy" />
<fmt:message bundle="${loc}" key="local.companyName" var="companyName" />
<fmt:message bundle="${loc}" key="local.contactData" var="contactData" />
<fmt:message bundle="${loc}" key="local.fullEmployment" var="fullEmployment" />
<fmt:message bundle="${loc}" key="local.partEmployment" var="partEmployment" />
<fmt:message bundle="${loc}" key="local.shortDescription" var="shortDescription" />
<fmt:message bundle="${loc}" key="local.description" var="description" />
<fmt:message bundle="${loc}" key="local.requirement" var="requirement" />
<fmt:message bundle="${loc}" key="local.salary" var="salary" />
<fmt:message bundle="${loc}" key="local.createVacancyIndex" var="createVacancyIndex" />	

<title>Create vacancy</title>
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
	<section class="section-information">
		<form role="form" method="post" action="Controller">

		<input name="command" value="create-vacancy" type="hidden" />
			<div class="thumbnail input-information">
				<div class="form-group">
					<label for="titleVacancy">*${titleVacancy}:</label> <input
						type="text" placeholder="${titleVacancy}" class="form-control"
						id="titleVacancy" name="titleVacancy">
				</div>
				<div class="form-group">
					<label for="companyName">*${companyName}:</label> <input
						type="text" placeholder="${companyName}" class="form-control"
						id="companyName"  name="companyName">
				</div>

				<div class="form-group">
					<label for="contactData">*${contactData}:</label> <input
						type="text" placeholder="${contactData}" class="form-control"
						id="contactData" name="contactData">
				</div>

				<div class="form-group">
					<select class="selectpicker" name="employment">
						<option value ="full-time">${fullEmployment}</option>
						<option value ="part-time">${partEmployment}</option>
					</select>
				</div>

				<div class="form-group">
					<label for="shortDescription">*${shortDescription}:</label>
					<textarea class="form-control" placeholder="${shortDescription}" rows="5"
						id="shortDescription" name="shortDescription"></textarea>
				</div>

				<div class="form-group">
					<label for="description">*${description}:</label>
					<textarea class="form-control" placeholder="${description}" rows="5"
						id="description" name="description"></textarea>
				</div>
				<div class="form-group">
					<label for="requirement">*${requirement}:</label>
					<textarea class="form-control" placeholder="${requirement}" rows="5"
						id="requirement" name="requirement"></textarea>
				</div>

				<div class="form-group">
					<label for="salary">*${salary}:</label> <input type="text"
						placeholder="${salary}" class="form-control" id="salary"
						name="salary">
				</div>
			</div>

			<button type="submit" name="createVacancy" class="btn btn-success"> ${createVacancyIndex} </button>
		</form>
	</section>
	<!-- FOOTER -->
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>