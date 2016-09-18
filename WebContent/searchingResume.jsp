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
<meta charset="UTF-8">
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

<title>Search resume</title>
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
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
						<label for="position">Название должности:</label> <input
							type="text" placeholder="Vacancy title" class="form-control"
							id="position" name="position">
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

					<button type="submit" class="btn btn-success">Найти
						резюме</button>
				</form>
			</div>

			<div class="row">
			<c:forEach var="resume" items="${requestScope.resumeList}">
						<div class="col-sm-6 col-md-4">
							<div class="thumbnail">
								<div class="caption">
									<h3>${resume.position}</h3>
									<h4>${resume.person.name}&nbsp;${resume.person.surname}</h4>
									<p>${resume.profInformation}</p>
									<a href="./resume.jsp?idResume=${resume.id}"  type="submit" class="btn btn-success">${look}</a>
								</div>
							</div>
						</div>
				</c:forEach>
				<!-- <div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<div class="caption item-height">
							<h3>Junior Java Developer</h3>
							<h4>Иванов Иван</h4>
							<p>Cras justo odio, dapibus ac facilisis in, egestas eget
								quam. Donec id elit non mi porta gravida at eget metus. Nullam
								id dolor id nibh ultricies vehicula ut id elit.</p>

							<a href="resume.html" class="btn btn-success" role="button">Просмотреть</a>

						</div>
					</div> -->
				</div>
			</div>
	</section>
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />
</body>
</html>