<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String user_id = (String)session.getAttribute("id"); %>
<% request.setCharacterEncoding("UTF-8");
	//html에서부터 값 받아옴
	String address = request.getParameter("add");
	String latitude = request.getParameter("lat");
	String longitude = request.getParameter("lng");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="semantic/dist/semantic.min.js"></script>
    
    <!-- Custom -->
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/script.js"></script>
	<title>포스팅 작성</title>
	
	<script>
		function formchk(){
			if($('#add').val().length <1 ) $('#add').text("null");
			if($('#lat').val().length <1 ) $('#lat').text("null");
			if($('#lng').val().length <1 ) $('#lng').text("null");
			document.feedform.submit();
		}
	$(function() {
		$("#button_mapAPI").click(function(e) {
			e.preventDefault();
			$(".ui.modal").modal("show");
		});

// 		$("#button_mapAPI").each(function() {
// 			$(this).click(function() {
// 				e.preventDefault();
// 				var url = $(this).attr("href");
// 				$.get(url, function(data) {
// 					$(".ui.modal .content").html(data);
// 					$(".ui.modal").modal({observeChanges:true}).modal("show");
// 				});
// 			})
// 		});
	});
	</script>
	
</head>
<body>
	<div class="ui container">
		<jsp:include page="navbar.jsp"/>
		
		
		<form action="writenewsfeed.do" name="feedform" method="post" enctype="Multipart/form-data" class="ui form" onSubmit="formchk()">
			<div class="ui container">
	            <h1>작성하기</h1>
	            
	           	<div>
<!-- 	           		<a href="mapAPI.html">위치추가</a> -->
					<a class="ui small basic button violet" href="mapAPI.html" id="button_mapAPI">위치 추가</a>
	           	<br>
	           	
	           	<%if(address != null){%>
	           		주소 : <%=address %>
	           		위도 : <%=latitude %>
	           		경도 : <%=longitude %>
	           	<%} %>
	            </div>
	            
            	<div class="ui divider"></div>
            	
				<div class="bordered image centered-and-cropped" id="dimmer_pic">
					<div class="ui dimmer">
						<label for="button_pic" class="ui inverted button violet">사진 선택</label>
						<input type="file" name="fileName1" id="button_pic" onchange="readURL(this);">
						<input type="hidden" id="add" name="address" value="<%=address %>">
						<input type="hidden" id="lat" name="latitude" value="<%=latitude %>">
						<input type="hidden" id="lng" name="longitude" value="<%=longitude %>">
					</div>
					<img class="ui centered image dim_pic" src="images/wireframe/upload_image.png">
				</div>
	        </div>
	        
	        <div class="ui container div-text-input">
				<textarea name="contents" rows="4"></textarea>
			</div>
			<div class="column">
	            <div class="ui right aligned container">
	                <button type="reset" class="ui button">취소하기</button>
	                <button type="submit" class="ui button violet">작성하기</button>
	            </div>
	        </div>
		</form>
	</div>
	
	<!-- mapAPI 모달 시작 -->
	<div class="ui modal">
		<div class="content">
			Map API 영역
		</div>
	</div>
	<!-- mapAPI 모달 끝 -->
	
	<jsp:include page="footer.jsp"/>