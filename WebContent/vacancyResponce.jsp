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
<link rel="stylesheet" href="myStyle/vacancy.css" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script> 

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.status" var="status" />
<fmt:message bundle="${loc}" key="local.return" var="returnButton" />
<fmt:message bundle="${loc}" key="local.delete" var="delete" />
<fmt:message bundle="${loc}" key="local.table.of.responces"
	var="tableResponces" />
<fmt:message bundle="${loc}" key="local.phone.number" var="phoneNumber" />
<fmt:message bundle="${loc}" key="local.name.applicant"
	var="nameApplicant" />
<fmt:message bundle="${loc}" key="local.prefed.position"
	var="prefedPosition" />
<fmt:message bundle="${loc}" key="local.not.resumes" var="notResumes" />
<fmt:message bundle="${loc}" key="local.date" var="date" />
<fmt:message bundle="${loc}" key="local.change.responce.status"
	var="changeResponceStatus" />


<meta name="viewport" content="width=device-width, initial-scale=1">
<title>List of responces for vacancy</title>
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>

	<section>
		<div class="thumbnail wrap-information">

			<h3>${param.vacancyName}</h3>

			<c:if test="${empty requestScope.responceList}">
				<p>${notResumes}</p>
			</c:if>
			<!-- <form action="Controller" method="post"> -->
			<form action="Controller" method="post">
				<table class="table">
					<caption>${tableResponces}</caption>
					<thead>
						<tr>
							<th></th>
							<th>${nameApplicant}</th>
							<th>${prefedPosition }</th>
							<th>${phoneNumber}</th>
							<th>${status}</th>
							<th>${date}</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="responce" items="${requestScope.responceList}">

							<tr>
								<td><input class="checkbox" type="checkbox"
									name="idResponce" value="${responce.id}" /></td>
								<td><a href="./resume.jsp?idResume=${responce.resume.id}">${responce.person.surname}
										&nbsp; ${responce.person.name}</a></td>
								<td>${responce.resume.position}</td>
								<td>${responce.resume.contactInfo.phone}</td>
								<td>${responce.status}</td>
								<td>${responce.date}</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
				<c:if test="${not empty requestScope.responceList}">
					<div class="form-group">

						<select class="selectpicker" name="status">
							<option value="viewed">viewed</option>
							<option value="rejection">rejection</option>
							<option value="invitation-for-interview">invitation-for-interview</option>
						</select>
						
					
						<button type="submit" class="btn btn-success">
							${changeResponceStatus}</button>
							
						<input type="hidden" name="command"	value="change-responce-status" /> 
						<input type="hidden" name="idVacancy" value="${param.idVacancy}" /> 
						<input type="hidden" name="vacancyName" value="${param.vacancyName}" />
					</div>
				</c:if>
				<div class="form-group">
					<a href="accountHR.jsp" class="btn btn-success">
						${returnButton}</a>
				</div>
			</form>
			<%-- <div class="form-group">
					<input type="hidden" name="name" value="delete-responce" />
					<button type="submit" class="btn btn-success">${delete}</button>
					<a href="accountHR.jsp" class="btn btn-success">
						${returnButton}</a>
				</div>
			</form> --%>
		</div>

	</section>
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />
</body>
</html>