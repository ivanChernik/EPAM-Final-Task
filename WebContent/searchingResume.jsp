<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/search.css" type="text/css">

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
<fmt:message bundle="${loc}" key="local.full.employment"
	var="fullEmployment" />
<fmt:message bundle="${loc}" key="local.part.employment"
	var="partEmployment" />
<fmt:message bundle="${loc}" key="local.look" var="look" />
<fmt:message bundle="${loc}" key="local.postion.name" var="positionName" />
<fmt:message bundle="${loc}" key="local.search.resumes"
	var="searchResumes" />
<fmt:message bundle="${loc}" key="local.resume.not.found"
	var="resumeNotFound" />

<title>Search resume</title>
</head>
<body>
	<c:set var="pageName" value="searchingResume.jsp" scope="session" />
	<jsp:include page="components/navigation.jsp"></jsp:include>
	<section>
		<div class="row row-grid">
			<div class="thumbnail search-block col-sm-6 col-md-4 ">
				<form role="form" action="Controller" method="post">
					<input name="command" value="search-resume" type="hidden">
					<c:if test="${not empty requestScope.errorMessage}">

						<div class="form-group alert alert-danger">
							<span class="glyphicon glyphicon-exclamation-sign"
								aria-hidden="true"></span><strong>${errorMessage}</strong>
						</div>
					</c:if>

					<div class="form-group">
						<label for="position">${positionName}:</label> <input type="text"
							placeholder="${positionName}" class="form-control" id="position"
							name="position">
					</div>

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

					<button type="submit" class="btn btn-success">${searchResumes}</button>
				</form>
			</div>

			<c:if
				test="${empty requestScope.resumeList && requestScope.result eq true}">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail message">
						<p>${resumeNotFound}</p>
					</div>
				</div>
			</c:if>

			<div class="row">
				<c:forEach var="resume" items="${requestScope.resumeList}">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<div class="caption">
								<h3>${resume.position}</h3>
								<h4>${resume.person.name}&nbsp;${resume.person.surname}</h4>
								<p>${resume.profInformation}</p>
								<a href="./resume.jsp?idResume=${resume.id}" type="submit"
									class="btn btn-success">${look}</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- FOOTER -->
	<jsp:include page="components/footer.jsp" />
</body>
</html>