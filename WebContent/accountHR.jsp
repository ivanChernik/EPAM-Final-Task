<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/account.css" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.logOut" var="logOut" />
<fmt:message bundle="${loc}" key="local.create.vacancy"
	var="createVacancy" />
<fmt:message bundle="${loc}" key="local.search.vacancies"
	var="searchVacancies" />
<fmt:message bundle="${loc}" key="local.search.resumes"
	var="searchResumes" />
<fmt:message bundle="${loc}" key="local.search" var="search" />

<fmt:message bundle="${loc}" key="local.table.status.view.feedback"
	var="tableStatusViewFeedback" />
<fmt:message bundle="${loc}" key="local.name.vacancy" var="nameVacancy" />
<fmt:message bundle="${loc}" key="local.company.name" var="companyName" />
<fmt:message bundle="${loc}" key="local.date.of.application"
	var="dateOfApplication" />
<fmt:message bundle="${loc}" key="local.status" var="status" />
<fmt:message bundle="${loc}" key="local.not.responces"
	var="notResponces" />


<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Account HR</title>
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>

	<jsp:include page="${request.contextPath}/Controller" flush="true">
		<jsp:param name="command" value="show-hr-vacancies" />
	</jsp:include>


	<section>
		<div class="thumbnail wrap-information">
			<c:if test="${empty requestScope.vacancyList}">
				<p>${notResponces}</p>
			</c:if>
			<table class="table">
				<caption>${tableStatusViewFeedback}</caption>
				<thead>
					<tr>
						<th>${nameVacancy}</th>
						<th>${companyName}</th>
						<th>${dateOfApplication}</th>
						<th>${status}</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="vacancy" items="${requestScope.vacancyList}">

						<tr>
							<td><a
								href="./Controller?command=show-responce-to-vacancy&idVacancy=${vacancy.id}&vacancyName=${vacancy.name}">${vacancy.name}</a></td>
							<td>${vacancy.companyName}</td>
							<td>${vacancy.dateSubmission}</td>
							<td>${vacancy.status}</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>

	</section>
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />
</body>
</html>