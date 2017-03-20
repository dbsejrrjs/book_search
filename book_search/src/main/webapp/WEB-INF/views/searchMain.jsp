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
				$("#searchList").empty();
				console.log(args);
				$(args.searchList).each(function(i,e){
					var $divTmp = $("<div>").css("position","relative").css("height","230px");
					$divTmp.append($("<div>").append($("<img>").attr("src",e.bookImg).css("width","150px").css("height","210px")).css("float","left"));
					
					var $divInTmp = $("<div>").css("float","left").css("margin-left","10px");
					$divInTmp.append($("<span>").append(e.bookName).css("font-size","15px"));
					
					var $ulTmp = $("<ul>").css("font-size","13px").css("height","168px").css("width","530px").css("overflow","auto");
					$(e.bookDetail).each(function(di, de){
						$ulTmp.append($("<li>").append($("<a>").append(de.targetName + " / " + de.targetPrice).attr("href",de.targetUrl).attr("target","_blank")));
					});
					$divInTmp.append($ulTmp);
					$divTmp.append($divInTmp);
					$("#searchList").append($divTmp);
				});
			},   
			error:function(e){  
				alert("에러 = " + e);  
			},
			beforeSend: function() {
				$('#searchLoading').fadeIn("fast"); 
		    },
		   complete: function() {
				$('#searchLoading').hide();
			}
		});  
	});
	
});
</script>
<style type="text/css">
	a{ text-decoration: none; }
	a:link { color: blue; }
	a:visited { color: blue; }
	a:hover { color: red; }
</style>
</head>
<body>
<div id="searchLoading" style="position:absolute; width: 100%; height: 100%; display: none; z-index: 999;"><img src="resources/search_loading.gif" alt="검색중" style="position : absolute; left:5%;"/></div>
	<div>현재 검색 지원 사이트 (${searchMainDataCount})</div>
	<c:forEach items="${searchMainData}" var="li" varStatus="stus">
		<div><span>${stus.count}</span> : <span>${li.name}</span></div>
	</c:forEach>
	<br/>
	<div>
		도서명 검색 : <input type="text" id="bookName"/> <img alt="검색" src="resources/search.jpg" id="searchStart"/>
	</div>
	<br/>
	<div id="searchList" style=""></div>
</body>
</html>
