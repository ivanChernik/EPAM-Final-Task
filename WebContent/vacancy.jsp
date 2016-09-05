<%@ page info="vacancy.jsp" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/vacancy.css" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>  -->

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<meta charset="UTF-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.description" var="description" />
<fmt:message bundle="${loc}" key="local.requirement" var="requirement" />
<fmt:message bundle="${loc}" key="local.contact.data" var="contactData" />
<fmt:message bundle="${loc}" key="local.salary" var="salary" />
<fmt:message bundle="${loc}" key="local.send.resume" var="sendResume" />
<fmt:message bundle="${loc}" key="local.employment" var="employment" />
<fmt:message bundle="${loc}" key="local.vacancy.does.not.exist"
	var="vacancyDoesNotExist" />
<fmt:message bundle="${loc}" key="local.delete" var="delete" />


<title>Vacancy</title>
</head>
<body>

	<c:set var="pageName" value="Controller" scope="session" />

	<jsp:include page="navigation.jsp"></jsp:include>
	<section class="main-information">
		<c:choose>
			<c:when test="${empty requestScope.vacancy}">
				<div class="thumbnail section-information ">
					${vacancyDoesNotExist}</div>
			</c:when>
			<c:otherwise>

				<c:if test="${not empty requestScope.errormessages}">
					<div class="form-group alert alert-danger">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span><strong>${requestScope.errormessages}</strong>
					</div>
				</c:if>

				<div class="thumbnail section-information ">
					<c:if test="${sessionScope.person.role eq 'hr'}">

						<jsp:include page="${request.contextPath}/Controller" flush="true">
							<jsp:param name="command" value="check-affiliation-vacancy-to-hr" />
						</jsp:include>

						<c:if test="${requestScope.belongs}">
							<div class="row">
								<div class="col-sm-1">
									<form action="Controller" method="post">
										<input type="hidden" name="command"
											value="show-vacancy-to-update" /> <input name="idVacancy"
											value="${requestScope.vacancy.id}" type="hidden">
										<button role="button" class="btn btn-success" type="submit">
											<span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
										</button>
									</form>
								</div>
								<div class="col-sm-1">
									<form action="Controller" method="post">
										<input type="hidden" name="command" value="delete-vacancy" />
										<input name="idVacancy" value="${requestScope.vacancy.id}"
											type="hidden">
										<button role="button" type="submit" class="btn btn-danger">
											<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
										</button>
									</form>
								</div>
							</div>
						</c:if>
					</c:if>

					<p>${requestScope.vacancy.status}${requestScope.vacancy.dateSubmission}</p>
					<h1>${requestScope.vacancy.name}</h1>
					<h4>${requestScope.vacancy.companyName}</h4>
					<h4 class="page-header">${description}</h4>
					<p>${requestScope.vacancy.description}</p>
				</div>
				<div class="thumbnail section-information ">
					<h4 class="page-header">${requirement}</h4>
					<p>${requestScope.vacancy.requirement}</p>
				</div>
				<div class="thumbnail section-information ">
					<h4 class="page-header">${contactData}</h4>
					<p>${requestScope.vacancy.contactInformation}</p>
				</div>
				<div class="thumbnail section-information ">
					<h4 class="page-header">${employment}</h4>
					<p>${requestScope.vacancy.employment}</p>
				</div>
				<div class="thumbnail section-information ">
					<h4 class="page-header">${salary}</h4>
					<p>${requestScope.vacancy.salary}<span
							class="glyphicon glyphicon-usd" aria-hidden="true"></span>
					</p>
				</div>
				<form action="Controller" method="post">
					<input name="command" value="responce-to-vacancy" type="hidden">
					<input name="idVacancy" value="${requestScope.vacancy.id}"
						type="hidden">
					<c:if test="${sessionScope.person.role eq 'applicant'}">
						<button type="submit" class="btn btn-success">${sendResume}</button>
					</c:if>
				</form>
			</c:otherwise>
		</c:choose>

	</section>
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />
</body>
</html>