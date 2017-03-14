<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	검색 Url
</h1>

<div>
	<c:forEach items="${resultData}" var="li" varStatus="status">
		<div>${li.no} / ${li.name} / ${li.url} / ${li.site_type}</div>
	</c:forEach>
</div>

</body>
</html>
