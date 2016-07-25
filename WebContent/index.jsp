<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/index.css" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!--
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

Latest compiled and minified JavaScript
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

(Optional) Latest compiled and minified JavaScript translation files
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script> -->


<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>HR System</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<ul class="nav nav-tabs">
					<li><a class="navbar-brand" href="#">HR System</a></li>
					<li role="presentation" class="dropdown"><a
						class="dropdown-toggle dropdown-button-color"
						data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
						aria-expanded="false"> Поиск <span class="caret"></span>
					</a>
						<ul class="dropdown-menu dropdown-button-style">
							<li><a href="searchingVacancy.html">Поиск вакансий</a></li>
							<li><a href="searchingResume.html">Поиск резюме</a></li>
						</ul></li>
				</ul>
			</div>

			<div id="navbar" class="navbar-collapse collapse">
				<form class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" placeholder="Login" class="form-control"
							name="login">
					</div>
					<div class="form-group">
						<input type="password" placeholder="Password" class="form-control"
							name="password">
					</div>
					<button type="submit" class="btn btn-success">Войти</button>
					<a href="#popup-registration" class="btn btn-success">Регистрация</a>
				</form>
			</div>
		</div>
	</nav>
	<!-- BACKGROUNG IMAGE -->
	<div class="container jumbotron background-img">
		<div class="topic-button-placing">
			<h2 class="page-header">Добро пожаловать в систему HR</h2>
			<a href="creatingResume.html"
				class="btn btn-success button-img-placing"> <!-- <button type="submit">Создать
				резюме</button> -->Создать резюме
			</a> <a class="btn btn-success button-img-placing"
				href="creatingVacancy.html"> <!-- <button type="submit" class="btn btn-success button-img-placing">  -->Создать
				вакансию<!-- </button> -->
			</a>
		</div>
		<div class="vertical-line"></div>

		<div class="mark-informtion">
			<div class="number-value">50</div>
			<div class="text-value">Резюме</div>
		</div>
		<div class="mark-informtion">
			<div class="number-value">68</div>
			<div class="text-value">Вакансий</div>
		</div>
		<div class="mark-informtion">
			<div class="number-value">40</div>
			<div class="text-value">Компаний</div>
		</div>
	</div>
	<!-- LIST VACANCIES -->
	<section class="section-placing">
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="caption">
						<h3>Junior Java Developer</h3>
						<h4>Черников Иван</h4>
						<p>Пример описания резюме!!!in, egestas eget quam. Donec id
							elit non mi porta gravida at eget metus. Nullam id dolor id nibh
							ultricies vehicula ut id elit.</p>

						<a href="resume.html" class="btn btn-success" role="button">Просмотреть</a>

					</div>
				</div>
			</div>

			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="caption">
						<h3>ASP.NET Tem Leader</h3>
						<h4>Иванов Петр</h4>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>

						<a href="#" class="btn btn-success" role="button">Просмотреть</a>

					</div>
				</div>
			</div>

			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="caption">
						<h3>Middle Frontend Developer</h3>
						<h4>Бранцевич Александр</h4>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>

						<a href="#" class="btn btn-success" role="button">Просмотреть</a>

					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="caption">
						<h3>Senior Java Developer</h3>
						<h4>Жуков Иван</h4>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>

						<a href="#" class="btn btn-success" role="button">Просмотреть</a>

					</div>
				</div>
			</div>

			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="caption">
						<h3>Senior Python Developer</h3>
						<h4>Горбунов Иван</h4>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>

						<a href="#" class="btn btn-success" role="button">Просмотреть</a>

					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- POPUP SING UP -->
	<c:choose>
		<c:when test="${not empty requestScope.errorMessage}">

			<a href="" class="overlay" style="display: block"></a>
			<div class="regestration-window" style="display: block">
		</c:when>
		<c:otherwise>
			<a href="" id="popup-registration" class="overlay"></a>
			<div class="regestration-window thumbnail">
		</c:otherwise>
	</c:choose>
	<form role="form" action="ControllerServlet" method="post">

		<c:if test="${not empty requestScope.errorMessage}">

			<div class="form-group alert alert-danger">
				<strong>${errorMessage}</strong>
			</div>

		</c:if>

		<input name="command" value="REGISTRATION" type="hidden" />

		<div class="form-group">
			<label for="name">Введите имя:</label> <input type="text"
				class="form-control" placeholder="Enter name" id="name" name="name" required>
		</div>

		<div class="form-group">
			<label for="surname">Введите фамилию:</label> <input type="text"
				class="form-control" placeholder="Enter surname" id="surname"
				name="surname" required>
		</div>

		<div class="form-group">
			<label for="patronymic">Введите отчество:</label> <input type="text"
				class="form-control" placeholder="Enter patronymic" id="patronymic"
				name="patronymic">
		</div>

		<div class="form-group">
			<label for="email">Введите email:</label> <input type="email"
				class="form-control" placeholder="Enter email" id="email"
				name="email" required>
		</div>

		<div class="form-group">
			<label for="dateOfBirthday">Введите дату рождения:</label> <input
				type="date" class="form-control"
				placeholder="Enter date of birthday" id="dateOfBirthday"
				name="dateOfBirthday" required>
		</div>

		<div class="form-group">
			<label for="phoneNumber">Введите номер телефона:</label> <input
				type="text" class="form-control" placeholder="Enter phone number"
				id="phoneNumber" name="phoneNumber">
		</div>

		<div class="form-group">
			<label for="patronymic">Введите логин:</label> <input type="text"
				class="form-control" placeholder="Enter login" id="login"
				pattern="[\w]+" name="login" required>
		</div>

		<div class="form-group">
			<label for="pwd">Введите пароль:</label> <input type="password"
				class="form-control" placeholder="Enter password" id="pwd"
				name="password" required>
		</div>

		<div class="form-group">
			<label for="pwd-repeat">Повторите пароль:</label> <input
				type="password" class="form-control" placeholder="Repeat password"
				id="pwd-repeat" name="repeatedPassword" required>
		</div>

		<div class="form-group">
			<select class="selectpicker" name="role">
				<option>Соискатель</option>
				<option>Работадатель</option>
			</select>
		</div>
		<button type="submit" class="btn btn-success">Зарегистрироваться</button>
		<a href="index.html" class="btn btn-success">Отмена</a>
	</form>
	</div>

	<!-- FOOTER -->
	<footer>
	 <jsp:include page="footer.jsp"/>
	</footer>
</body>
</html>