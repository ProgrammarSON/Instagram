<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String user_id = (String)session.getAttribute("id"); %>
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
		<div class="ui segment">
            <h1>작성하기</h1>
            <div class="ui divider"></div>
            
			<input type="hidden" name="user_id" value="<%=user_id %>">
			<div class="bordered image centered-and-cropped" id="dimmer_pic">
				<div class="ui dimmer">
					<label for="button_pic" class="ui inverted button violet">사진 선택</label>
					<input type="file" name="fileName1" id="button_pic" onchange="readURL(this);">
				</div>
				<img class="ui centered image dim_pic" src="images/wireframe/upload_image.png">
			</div>
        </div>
        <div class="ui segment">
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