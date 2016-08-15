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

<fmt:message bundle="${loc}" key="local.status" var="status" />

<meta name="viewport" content="width=device-width, initial-scale=1">
<title>List of responces for vacancy</title>
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>

	<section>
		<div class="thumbnail wrap-information">
		
		<h3>${param.vacancyName}</h3>
		
			<c:if test="${empty requestScope.responceList}">
				<p>No resumes</p>
			</c:if>
			<table class="table">
				<caption>Table of responces</caption>
				<thead>
					<tr>
						<th></th>
						<th>Name</th>
						<th>Prefered Position</th>
						<th>Phone number</th>
						<th>${status}</th>
						<th>Date</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="responce" items="${requestScope.responceList}">

						<tr>
							<td><input class="checkbox" type="checkbox" value=""/></td>
							<td><a href="./resume.jsp?idResume=${responce.resume.id}">${responce.person.surname} &nbsp; ${responce.person.name}</a></td>
							<td>${responce.resume.position}</td>
							<td>${responce.resume.contactInfo.phone}</td>
							<td>${responce.status}</td>
							<td>${responce.date}</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			
		<a href="accountHR.jsp" type="submit" class="btn btn-success">Cancel</a>
		</div>

	</section>
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />
</body>
</html>