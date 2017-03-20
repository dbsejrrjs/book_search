<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도서 검색</title>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js" ></script>
<script type="text/javascript">
$(function(){
	$("#bookName").css("height", 25);
	$("#bookName").css("size", 20);
	$("#searchStart").css("width",25);
	$("#searchStart").css("height",25);
	$("#searchStart").css("cursor", "pointer");
	$("#searchStart").css("vertical-align", "middle");
	
	$("#searchStart").click(function(){
		$.ajax({
			type : "POST",
			async : "true",
			dataType: "json",
			url : "searchStart",      
			data : "bookName="+$("#bookName").val(),      
			success:function(args){   
				alert(args.bookName);
				console.log(args);
//                    $("#result").html(args);      
			},   
			error:function(e){  
                   alert("에러 = " + e);  
			}  
		});  

	});
	
});
</script>
</head>
<body>
	<div>현재 검색 지원 사이트 (${searchMainDataCount})</div>
	<c:forEach items="${searchMainData}" var="li" varStatus="stus">
		<div><span>${stus.count}</span> : <span>${li.name}</span></div>
	</c:forEach>
	<br/>
	<div>
		도서명 검색 : <input type="text" id="bookName"/> <img alt="검색" src="resources/search.jpg" id="searchStart"/>
	</div>
</body>
</html>
