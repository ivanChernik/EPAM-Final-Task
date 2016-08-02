<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/create.css" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>
<meta charset="UTF-8">
<title>Create vacancy</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<ul class="nav nav-tabs">
					<li><a class="navbar-brand" href="index.html">HR System</a></li>
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
					<button type="submit" class="btn btn-success">Выйти</button>
				</form>
			</div>
		</div>
	</nav>
	<section class="section-information">
		<form role="form" method="post" action="ControllerServlet">

		<input name="command" value="create-vacancy" type="hidden" />
			<div class="thumbnail input-information">
				<div class="form-group">
					<label for="titleVacancy">Заголовок вакансии:</label> <input
						type="text" placeholder="Vacancy title" class="form-control"
						id="titleVacancy" name="titleVacancy">
				</div>
				<div class="form-group">
					<label for="companyName">Название компании:</label> <input
						type="text" placeholder="Company name" class="form-control"
						id="companyName"  name="companyName">
				</div>

				<div class="form-group">
					<label for="contactData">Контактные данные:</label> <input
						type="text" placeholder="Contact company" class="form-control"
						id="contactData" name="contactData">
				</div>

				<div class="form-group">
					<select class="selectpicker" name="employment">
						<option>Полная занятость</option>
						<option>Частичная занятость</option>
					</select>
				</div>

				<div class="form-group">
					<label for="shortDescription">Краткое описание:</label>
					<textarea class="form-control" placeholder="Description" rows="5"
						id="shortDescription" name="shortDescription"></textarea>
				</div>

				<div class="form-group">
					<label for="description">Полное описание:</label>
					<textarea class="form-control" placeholder="Description" rows="5"
						id="description" name="description"></textarea>
				</div>
				<div class="form-group">
					<label for="requirement">Требования:</label>
					<textarea class="form-control" placeholder="Requirements" rows="5"
						id="requirement" name="requirement"></textarea>
				</div>

				<div class="form-group">
					<label for="salary">Уровень оплаты:</label> <input type="text"
						placeholder="Salary" class="form-control" id="salary"
						name="salary">
				</div>
			</div>

			<button type="submit" name="createVacancy" class="btn btn-success">Создать
				вакансию</button>
		</form>
	</section>
	<!-- FOOTER -->
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>