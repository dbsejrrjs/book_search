<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>중고도서 통합 검색 시스템</title>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js" ></script>
<script type="text/javascript">
$(function(){
	$("#enterImg").css("width",100);
	$("#enterImg").css("height",100);
	$("#enterImg").css("position", "absolute");
	$("#enterImg").css("left", 50);
	$("#enterImg").css("cursor", "pointer");
	
	$("#enterImg").click(function(){
		$(location).attr("href","searchMain");
	});
});
</script>
</head>
<body>
	<div><h3>중고도서 통합 검색 시스템입니다.</h3></div>
	<br/>
	<div><img id="enterImg" alt="이동" src="resources/enter.jpg"></div>
</body>
</html>