<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.sing.up" var="singUp" />
<fmt:message bundle="${loc}" key="local.sing.in" var="singIn" />
<fmt:message bundle="${loc}" key="local.log.out" var="logOut" />

<fmt:message bundle="${loc}" key="local.search" var="search" />
<fmt:message bundle="${loc}" key="local.search.vacancies"
	var="searchVacancies" />
<fmt:message bundle="${loc}" key="local.search.resumes"
	var="searchResumes" />

<fmt:message bundle="${loc}" key="local.resume" var="resume" />
<fmt:message bundle="${loc}" key="local.create.resume"
	var="createResume" />
<fmt:message bundle="${loc}" key="local.update.resume"
	var="updateResume" />

<fmt:message bundle="${loc}" key="local.profile" var="profile" />
<fmt:message bundle="${loc}" key="local.responces" var="responces" />
<fmt:message bundle="${loc}" key="local.update.profile"
	var="updateProfile" />
<fmt:message bundle="${loc}" key="local.view.profile" var="viewProfile" />

<fmt:message bundle="${loc}" key="local.create.vacancy"
	var="createVacancy" />
<fmt:message bundle="${loc}" key="local.vacancy" var="vacancy" />

<fmt:message bundle="${loc}" key="local.ru" var="ru" />
<fmt:message bundle="${loc}" key="local.en" var="en" />

<fmt:message bundle="${loc}" key="local.enter.login" var="enterLogin" />
<fmt:message bundle="${loc}" key="local.enter.password"
	var="enterPassword" />
<fmt:message bundle="${loc}" key="local.look.resume" var="lookResume" />
<fmt:message bundle="${loc}" key="local.show.vacancies"
	var="showVacancies" />
<fmt:message bundle="${loc}" key="local.add.education"
	var="addEducation" />

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

				<c:if test="${not empty sessionScope.person}">
					<!-- APPLICANT -->
					<c:if test="${sessionScope.person.role eq 'applicant'}">
						<li role="presentation" class="dropdown"><a
							class="dropdown-toggle dropdown-button-color"
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="true" aria-expanded="false"> ${resume} <span
								class="caret"></span>
						</a>
							<ul class="dropdown-menu dropdown-button-style">
								<li><a href="creationResume.jsp"> ${createResume}</a></li>
								<li><a href="addictionEducation.jsp"> ${addEducation}</a></li>
								<li><a href="#"> ${updateResume}</a></li>
								<li><a
									href="./resume.jsp?idResume=${sessionScope.person.id}">
										${lookResume}</a></li>
								<li><a href="accountApplicant.jsp">${responces}</a></li>
							</ul></li>
					</c:if>
					<!-- HR -->
					<c:if test="${sessionScope.person.role eq 'hr'}">
						<li role="presentation" class="dropdown"><a
							class="dropdown-toggle dropdown-button-color"
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="true" aria-expanded="false"> ${vacancy} <span
								class="caret"></span>
						</a>
							<ul class="dropdown-menu dropdown-button-style">
								<li><a href="creationVacancy.jsp"> ${createVacancy}</a></li>
								<li><a href="accountHR.jsp">${responces}</a></li>
								<li><a href="tableVacancy.jsp">${showVacancies}</a></li>
							</ul></li>
					</c:if>
					<li role="presentation" class="dropdown"><a
						class="dropdown-toggle dropdown-button-color"
						data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
						aria-expanded="false"> ${profile} <span class="caret"></span>
					</a>
						<ul class="dropdown-menu dropdown-button-style">
							<li><a href="updateProfile.jsp">${updateProfile}</a></li>
							<li><a href="viewProfile.jsp">${viewProfile}</a></li>
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
								<input type="text" placeholder="${enterLogin}"
									class="form-control" name="login" value="">
							</div>
							<div class="form-group">
								<input type="password" placeholder="${enterPassword}"
									class="form-control" name="password" value="">
							</div>
							<input type="submit" class="btn btn-success" value="${singIn}" />

							<a href="#popup-registration" class="btn btn-success">
								${singUp} </a>
						</form>
					</li>
				</c:if>
				<c:if test="${not empty sessionScope.person}">
					<li>
						<form action="Controller" class="navbar-form navbar-right"
							method="post">
							<input name="command" value="log-out" type="hidden">
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