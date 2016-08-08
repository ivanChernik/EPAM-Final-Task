<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.singIn" var="singIn" />
<fmt:message bundle="${loc}" key="local.singUp" var="singUp" />
<fmt:message bundle="${loc}" key="local.logOut" var="logOut" />

<fmt:message bundle="${loc}" key="local.search" var="search" />
<fmt:message bundle="${loc}" key="local.searchVacancies"
	var="searchVacancies" />
<fmt:message bundle="${loc}" key="local.searchResumes"
	var="searchResumes" />

<fmt:message bundle="${loc}" key="local.resume" var="resume" />
<fmt:message bundle="${loc}" key="local.createResume" var="createResume" />
<fmt:message bundle="${loc}" key="local.updateResume" var="updateResume" />

<fmt:message bundle="${loc}" key="local.profile" var="profile" />
<fmt:message bundle="${loc}" key="local.responces" var="responces" />
<fmt:message bundle="${loc}" key="local.updateProfile" var="updateProfile" />

<fmt:message bundle="${loc}" key="local.createResume" var="createResume" />
<fmt:message bundle="${loc}" key="local.createVacancy" var="createVacancy" />
<fmt:message bundle="${loc}" key="local.vacancy" var="vacancy" />

<fmt:message bundle="${loc}" key="local.ru" var="ru" />
<fmt:message bundle="${loc}" key="local.en" var="en" />

<fmt:message bundle="${loc}" key="local.enterLogin" var="enterLogin" />
<fmt:message bundle="${loc}" key="local.enterPassword" var="enterPassword" />

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<ul class="nav nav-tabs">
				<li><a class="navbar-brand" href="index.jsp">HR System</a></li>
				<li role="presentation" class="dropdown"><a
					class="dropdown-toggle dropdown-button-color"
					data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
					aria-expanded="false"> ${search} <span class="caret"></span>
				</a>
					<ul class="dropdown-menu dropdown-button-style">
						<li><a href="searchingVacancy.html">${searchVacancies}</a></li>
						<li><a href="searchingResume.html">${searchResumes}</a></li>
					</ul></li>

				<c:if test="${sessionScope.person.role eq 'applicant'}">
					<li role="presentation" class="dropdown"><a
						class="dropdown-toggle dropdown-button-color"
						data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
						aria-expanded="false"> ${resume} <span class="caret"></span>
					</a>
						<ul class="dropdown-menu dropdown-button-style">
							<li><a href="creationResume.jsp"> ${createResume}</a></li>
							<li><a href="#"> ${updateResume}</a></li>
						</ul></li>
						
						<li role="presentation" class="dropdown"><a
						class="dropdown-toggle dropdown-button-color"
						data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
						aria-expanded="false"> ${profile} <span class="caret"></span>
					</a>
						<ul class="dropdown-menu dropdown-button-style">
							<li><a href="accountApplicant.jsp">${responces}</a></li>
							<li><a href="#">${updateProfile}</a></li>
						</ul></li>
				</c:if>
				
				<c:if test="${sessionScope.person.role eq 'hr'}">
					<li role="presentation" class="dropdown"><a
						class="dropdown-toggle dropdown-button-color"
						data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
						aria-expanded="false"> ${vacancy} <span class="caret"></span>
					</a>
						<ul class="dropdown-menu dropdown-button-style">
							<li><a href="creationVacancy.jsp"> ${createVacancy}</a></li>
						</ul></li>
						
						<li role="presentation" class="dropdown"><a
						class="dropdown-toggle dropdown-button-color"
						data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
						aria-expanded="false"> ${profile} <span class="caret"></span>
					</a>
						<ul class="dropdown-menu dropdown-button-style">
							<li><a href="accountApplicant.jsp">${responces}</a></li>
							<li><a href="#">${updateProfile}</a></li>
						</ul></li>
				</c:if>
				
			</ul>
		</div>

		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${empty sessionScope.person}">
					<li>
						<form class="navbar-form" action="Controller" method="post">
							<input name="command" value="authorization" type="hidden">
							<div class="form-group">
								<input type="text" placeholder="${enterLogin}" class="form-control"
									name="login" value="">
							</div>
							<div class="form-group">
								<input type="password" placeholder="${enterPassword}"
									class="form-control" name="password" value="">
							</div>
							<button type="submit" class="btn btn-success"> ${singIn} </button>
							<a href="#popup-registration" class="btn btn-success">
								${singUp} </a>
						</form>
					</li>
				</c:if>
				<c:if test="${not empty sessionScope.person}">
					<li>
						<form class="navbar-form navbar-right" method="post">
							<input name="command" value="log_out" type="hidden">
							<button type="submit" class="btn btn-success">${logOut}</button>
						</form>
					</li>
				</c:if>
				<li>
					<form class="navbar-form" action="Controller" method="post"
						style="width: 200px;">
						<input name="command" value="change-local" type="hidden">
						<button type="submit" class="btn btn-link" name="local" value="en">${en}</button>
						<button type="submit" class="btn btn-link" name="local" value="ru">${ru}</button>
					</form>
				</li>
			</ul>
		</div>
	</div>
</nav>