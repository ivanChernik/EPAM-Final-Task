<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Account Applicant</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<ul class="nav nav-tabs">
					<li><a class="navbar-brand" href="index.html">HR System</a></li>
					<li><a class="navbar-brand" href="creatingResume.html">Создать
							резюме</a></li>
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
	<section>
		<div class="thumbnail table-information">
			<table class="table">
				<caption>Таблица состояния откликов</caption>
				<thead>
					<tr>
						<th>Компания</th>
						<th>Статус</th>
						<th>Дата</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>EPAM</td>
						<td>Просмотрен</td>
						<td>21.01.2016</td>
					</tr>
					<tr>
						<td>Issoft</td>
						<td>Отказ</td>
						<td>22.02.2016</td>
					</tr>
					<tr>
						<td>Эфтех</td>
						<td>Не просмотрен</td>
						<td>01.05.2016</td>
					</tr>
				</tbody>
			</table>
		</div>

	</section>
	<footer>
		<p class="text-footer">© 2016 EPAM Training Center, HR System. <a href="siteMap.html"> Карта сайта</a></p>
	</footer>
</body>
</html>