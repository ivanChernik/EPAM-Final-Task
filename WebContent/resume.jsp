<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="myStyle/resume.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-3.0.0.min.js"></script>

<meta charset="UTF-8">
<title>Resume</title>
</head>
<body>
	<!-- NAVIGATION START-->
	<jsp:include page="navigation.jsp"></jsp:include>
	
	<c:set var="resume" value="${requestScope.resume}" scope="page" />
	
	<section>
		<div class="thumbnail head-information">
			<div class="placing-inline main-information">
				<div class="position-head">Junior Java Developer</div>
				<div class="name">${person.name} &nbsp; ${person.surname}</div>

				<div class="location">${resume.contactInfo.address}</div>

				<div class="contact-information">
					<div>Email: ${resume.contactInfo.email}</div>
					<div>Телефон: ${resume.contactInfo.phone}</div>
				</div>

				<div class="social-network-placing">
					<a class="social-network" href="#"><img
						src="myStyle/img/google.png" alt="google+ account"></a> <a
						class="social-network" href="#"><img
						src="myStyle/img/linkedin.png" alt="linkedin account"></a> <a
						class="social-network" href="#"><img
						src="myStyle/img/twitter.png" alt="twitter account"></a> <a
						class="social-network" href="#"><img
						src="myStyle/img/facebook.png" alt="facebook account"></a>
				</div>
			</div>
			<div class="placing-inline">
				<img class="img-rounded profile-photo" src="${resume.pathImage}"
					alt="photo for resume">
			</div>
		</div>

		<div class="thumbnail section-information ">
			<h4 class="page-header">Профессиональная информация</h4>

			<p>Lorem ipsum dolor sit amet</p>
		</div>

		<div class="thumbnail section-information">
			<h4 class="page-header">Навыки</h4>

			<p>ООП, Java SE, J2EE, JSP/Servlets, Struts2,XML,Hibernate, JDBC,
				SQL, Design Patterns, UML, Git, C/C++.</p>
		</div>

		<div class="thumbnail section-information">
			<h4 class="page-header">Опыт работы</h4>

			<div class="subtitle">Junior Java Developer</div>
			<p>2014 - 2016</p>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam
				eget justo pellentesque, tempor lectus eget, dictum urna. Praesent
				vitae arcu risus. Donec eleifend tortor sapien, vel malesuada massa
				faucibus sed. Aliquam nec dictum velit, ac hendrerit magna. Vivamus
				pulvinar odio vitae orci auctor, eget consequat neque cursus. Nulla
				volutpat tincidunt massa, id sodales eros aliquam tristique.</p>

			<div class="subtitle">Junior C# Developer</div>
			<p>2014 - 2016</p>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam
				eget justo pellentesque, tempor lectus eget, dictum urna. Praesent
				vitae arcu risus. Donec eleifend tortor sapien, vel malesuada massa
				faucibus sed. Aliquam nec dictum velit, ac hendrerit magna. Vivamus
				pulvinar odio vitae orci auctor, eget consequat neque cursus. Nulla
				volutpat tincidunt massa, id sodales eros aliquam tristique.</p>
		</div>

		<div class="thumbnail section-information">
			<h4 class="page-header">Образование (Высшее)</h4>
			<div class="subtitle">Белорусский государственный университет
				информатики и радиоэлкутроники</div>
			<p>2013 - 2017</p>
			<p>Факультет информационных технологий и управления.
				Искусственный интеллект.Дневное отделение Подробное описание
				изученного</p>
		</div>
	</section>
	<!-- FOOTER -->
	<footer>
		<jsp:include page="footer.jsp" />
	</footer>
</body>
</html>