<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/search.css" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.look" var="look" />
<fmt:message bundle="${loc}" key="local.search.vacancies" var="searchVavancy" />
<fmt:message bundle="${loc}" key="local.vacancies.not.found" var="vacanciesNotFound" />
<fmt:message bundle="${loc}" key="local.salary" var="salary" />
<fmt:message bundle="${loc}" key="local.postion.name" var="positionName" />
<fmt:message bundle="${loc}" key="local.full.employment" var="fullEmployment" />
<fmt:message bundle="${loc}" key="local.part.employment" var="partEmployment" />
<fmt:message bundle="${loc}" key="local.from" var="from" />
<fmt:message bundle="${loc}" key="local.to" var="to" />

<meta charset="UTF-8">
<title>Search vacancy</title>
</head>
<body>
	<c:set var="pageName" value="searchingVacancy.jsp" scope="session" />

	<!-- NAVIGATION START-->
	<jsp:include page="components/navigation.jsp"></jsp:include>
	<!-- NAVIGATION END -->

	<section>
		<div class="row row-grid">
			<div class="row">
				<div class="thumbnail search-block col-sm-6 col-md-4 ">
					<form role="form" method="post" action="Controller">

						<c:if test="${not empty requestScope.errorMessage}">

							<div class="form-group alert alert-danger">
								<span class="glyphicon glyphicon-exclamation-sign"
									aria-hidden="true"></span><strong>${errorMessage}</strong>
							</div>
						</c:if>

						<input name="command" value="search-vacancy" type="hidden" />
						<div class="form-group">
							<label for="titleVacancy">${positionName}:</label> <input
								type="text" placeholder="${positionName}" class="form-control"
								id="titleVacancy" name="titleVacancy" required>
						</div>
						<div class="form-group">
							<select class="selectpicker" name="employment">
								<option value="full-time">${fullEmployment}</option>
								<option value="part-time">${partEmployment}</option>
							</select>
						</div>
						<p>${salary}</p>
						<div class="form-group">
							<label for="salary-from">${from}:</label> <input type="text"
								placeholder="Salary from" class="form-control" id="salary-from"
								name="salaryFrom" pattern="[0-9]+" required>
						</div>

						<div class="form-group">
							<label for="salary-to">${to}:</label> <input type="text"
								placeholder="Salary from" class="form-control" id="salary-to"
								name="salaryTo" pattern="[0-9]+" required>
						</div>

						<button type="submit" class="btn btn-success">${searchVavancy}</button>
					</form>
				</div>
				
				
				<c:if test="${empty requestScope.vacancyList && requestScope.result eq true}">
					<div class="col-sm-6 col-md-4">
					<div class="thumbnail message">
						<p>${vacanciesNotFound}</p>
						</div>
					</div>
				</c:if>

				<c:forEach var="vacancy" items="${requestScope.vacancyList}">

					<form action="Controller" method="get">
						<input name="command" value="show-vacancy" type="hidden">
						<input name="idVacancy" value="${vacancy.id}" type="hidden">
						<div class="col-sm-6 col-md-4">
							<div class="thumbnail">
								<div class="caption">
									<h3>${vacancy.name}</h3>
									<h4>${vacancy.salary}$</h4>
									<p>${vacancy.shortDescription}</p>
									<button type="submit" class="btn btn-success">${look}</button>
								</div>
							</div>
						</div>
					</form>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- FOOTER -->
	<jsp:include page="components/footer.jsp" />
</body>
</html>