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
</head>
<body>
	<div class="ui container">
		<jsp:include page="navbar.jsp"/>
		
		
	<form action="writenewsfeed.do" method="post" enctype="Multipart/form-data" class="ui form">
		<div class="ui container">
            <h1>작성하기</h1>
            
           	<div>
           	<a href="mapAPI.html" target="_blank">위치추가</a> <br>
           	
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
	
<jsp:include page="footer.jsp"/>