<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도서 검색</title>
<link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css">
	a{ text-decoration: none; }
	a:link { color: blue; }
	a:visited { color: blue; }
	a:hover { color: red; }
	
	#bgImg {
		background-image: url("resources/background_img_1.jpg");
		background-repeat:no-repeat;
		background-size: 100%;
		opacity: 0.4;
	}
	
	section {
		padding : 10px;
	}
	
	footer {
		position: fixed;
		padding-top : 2px;
		padding-bottom : 2px;
		padding-right : 15px;
		left: 0px;
		bottom: 0px;
		width: 100%;
		text-align: right;
		background-color: Lavender;
		font-size: 11px;
	}
</style>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js" ></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){

	$("#searchStart").click(function(){
		if($("#bookName").val() == ""){
			alert("검색어를 입력하세요.");
			return;
		}
		$.ajax({
			type : "POST",
			async : "true",
			dataType: "json",
			url : "searchStart",      
			data : "bookName="+$("#bookName").val(),      
			success:function(args){   
				$("#searchList").empty();
				$(args.searchList).each(function(i,e){
					var $divTmp = $("<div>").css("height","230px");
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

</head>
<body>
	<header>
		<article>
			<div id="searchLoading" style="position:absolute; width: 100%; height: 100%; display: none; z-index: 999;"><img src="resources/search_loading.gif" alt="검색중" style="position : absolute; left:20%;"/></div>
			<div id="bgImg" style="position:fixed; width: 100%; height: 100%; z-index: -1;"></div>
		</article>
	</header>
	<br/><br/>
	<section>
		<article>
			<div class="input-group col-xs-4">
				<span class="input-group-addon">도서명 검색</span>
				<input type="text" class="form-control" id="bookName" placeholder="책 제목을 입력하세요.">
				<div class="input-group-btn">
					<button class="btn btn-default" id="searchStart">
					<i class="glyphicon glyphicon-search" ></i>
					</button>
				</div>
			</div> 
<!-- 				<input type="text" id="bookName" class="form-control"/> -->
<!-- 				<img alt="검색" src="resources/search.jpg" id="searchStart"/> -->
		</article>
	</section>
	<section>
		<article>
		  <ul class="nav nav-pills  nav-justified" style="width: 50%;">
			  <li role="presentation" class="active"><a href="#">알라딘</a></li>
			  <li role="presentation"><a href="#" style="background-color:lightgray; color: gray;">YES24</a></li>
			</ul>
		</article>
	</section>
	<br/>
	<section>
		<article>
			<div id="searchList" style=""></div>
		</article>
	</section>
	
	<footer>
		중고도서 통합 검색 (Dukgun Yoon)/(${searchMainDataCount})/<c:forEach items="${searchMainData}" var="li" varStatus="stus">${li.name}/</c:forEach>
	</footer>
</body>
</html>
