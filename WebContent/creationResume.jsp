<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Create Resume</title>
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>
	<section class="section-information" >
		<form action="Controller" role="form" method="post" enctype="multipart/form-data">

			<input type="hidden" name="command" value="create-resume">
			<div class="thumbnail input-information">
				<h4 class="page-header resume-item-header">Общая информация</h4>
				<div class="form-group">
					<label for="position">Желаемая должность:</label> <input
						type="text" placeholder="Position" class="form-control"
						id="position" pattern="[\w]+" name="position">
				</div>

				<div class="form-group">
					<label for="photo">Загрузите фото:</label> <input type="file"
						name="photo" id="photo" accept="image/jpeg">
				</div>

				<div class="form-group">
					<label for="prof-infirmation">Профессиональная информация:</label>
					<textarea class="form-control"
						placeholder="Professional information" rows="5"
						id="prof-infirmation" name="prof-infirmation"></textarea>
				</div>
				<div class="form-group">
					<label for="skill">Навыки:</label>
					<textarea class="form-control" placeholder="Skills" rows="5"
						id="skill" name="skill"></textarea>
				</div>
			</div>

			<div class="thumbnail input-information">
				<h4 class="page-header resume-item-header">Информация об
					образовании</h4>
					

				<div class="form-group">
					<select class="selectpicker" name="kind-education">
						<option value="Higher">Высшее</option>
						<option value="Average">Неоконченное высшее</option>
						<option value="Specialized-secondary">Средне-специальное</option>
						<option value="Vocational-technical">Среднее</option>
						<option value="Incomplete-higher-education">Профессионально-техническое</option>
					</select>
				</div>


				<div class="form-group">
					<label for="university">Название учреждения образования:</label> <input
						type="text" placeholder="University" class="form-control"
						id="university" name="university">
				</div>

				<div class="form-group">
					<label for="faculty">Название факультета:</label> <input
						type="text" placeholder="Faculty" class="form-control"
						id="faculty" name="faculty">
				</div>

				<div class="form-group">
					<label for="specialty">Название специальности:</label> <input
						type="text" placeholder="Specialty" class="form-control"
						id="specialty" name="specialty">
				</div>


				<div class="form-group">
					<select class="selectpicker" name="form-education">					
						<option value="Full-time">Дневное отделение</option>
						<option value="Part-time">Заочное отделение</option>
						<option value="Distant">Дистанционное отделение</option>
					</select>
				</div>

				<div class="form-group">
					<label for="period-education">Период обучения:</label> <input
						type="date" placeholder="From" class="form-control"
						id="period-education" name="educationFrom">
				</div>

				<div class="form-group">
					<input type="date" placeholder="From" class="form-control"
						id="period-education" name="educationTo">
				</div>

				<div class="form-group">
					<label for="education-description">Описание:</label>
					<textarea class="form-control" placeholder="Description education"
						rows="5" id="education-description" name="education-description"></textarea>
				</div>
			</div>

			<div class="thumbnail input-information">
				<h4 class="page-header resume-item-header">Опыт</h4>
				<div class="form-group">
					<label for="previos-position">Должность:</label> <input type="text"
						placeholder="Previos position" class="form-control"
						id="previos-position" name="previos-position">
				</div>

				<div class="form-group">
					<label for="period-work">Период работы:</label> <input type="date"
						placeholder="From" class="form-control" id="workFrom"
						name="workFrom">
				</div>

				<div class="form-group">
					<input type="date" placeholder="To" class="form-control"
						id="period-work" name="workTo">
				</div>

				<div class="form-group">
					<label for="work-description">Описание:</label>
					<textarea class="form-control"
						placeholder="Description previos work" rows="5"
						id="work-description" name="work-description"></textarea>
				</div>

			</div>

			<div class="thumbnail input-information">
				<div class="form-group">
					<h4 class="page-header resume-item-header">Контактная
						информация</h4>
					<label for="phone">Контактный телефон:</label> <input type="text"
						placeholder="Phone" class="form-control" id="phone" name="phone">
				</div>

				<div class="form-group">
					<label for="email">Контактный email:</label> <input type="email"
						class="form-control" id="email" placeholder="Email" name="email">
				</div>

				<div class="form-group">
					<label for="address">Адрес:</label> <input type="text"
						class="form-control" id="address" placeholder="Address"
						name="address">
				</div>

				<div class="form-group">
					<label for="google-plus">Ссылка Google+:</label> <input type="text"
						class="form-control" id="google-plus" name="link-google-plus"
						placeholder="Google+">
				</div>


				<div class="form-group">
					<label for="link-linkedin">Ссылка LinkedIn:</label> <input
						type="text" class="form-control" id="link-linkedin"
						name="link-linkedin" placeholder="LinkedIn">
				</div>

				<div class="form-group">
					<label for="link-twitter">Ссылка Twitter:</label> <input
						type="text" class="form-control" id="link-twitter"
						name="link-twitter" placeholder="Twitter">
				</div>
				<div class="form-group">
					<label for="link-facebook">Ссылка Facebook:</label> <input
						type="text" class="form-control" id="link-facebook"
						name="link-facebook" placeholder="Facebook">
				</div>
			</div>
			<button type="submit" name="createResume" class="btn btn-success">Создать
				резюме</button>
		</form>
	</section>
	<footer>
		<p class="text-footer">
			© 2016 EPAM Training Center, HR System. <a href="siteMap.html">
				Карта сайта</a>
		</p>
	</footer>
</body>
</html>