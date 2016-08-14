<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.site.map" var="siteMap" />

<footer>
	<p class="text-footer">
		&#169; 2016 EPAM Training Center, HR System. <a href="siteMap.html">${siteMap}</a>
	</p>
</footer>