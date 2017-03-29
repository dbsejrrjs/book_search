<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>중고도서 통합 검색 시스템</title>
<link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css">
.bg-1 { 
	position : fixed;
	width : 100%;
	height : 100%;
 	background-color: #2d2d30;
	color: #ffffff;
}
#enterImg {
	width: 150px;
	height: 150px;
	cursor: pointer;
	border-radius : 10%;
}
</style>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js" ></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#enterImg").click(function(){
		$(location).attr("href","searchMain");
	});
});
</script>
</head>
<body>
	<div class="container-fluid bg-1 text-center">
		<br/><br/><br/><br/>
		<h3>중고도서 통합 검색 시스템에 오신걸 환영합니다.</h3>
		<br/>
		<img src="resources/enter.jpg" alt="enter" id="enterImg">
		<br/>
		<h5>입장을 원하시면 클릭하세요.</h5>
	</div>
</body>
</html>