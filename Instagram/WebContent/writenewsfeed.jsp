<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String user_id = (String)session.getAttribute("id"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="semantic/dist/semantic.min.js"></script>
    
    <!-- Custom -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/script.js"></script>
	<title>포스팅 작성</title>
</head>
<body>
	<div class="ui container">
	<a class="" href="#">
	    <img src="images/yestagram.png" class="ui image centered small" id="brand" alt="Yestagram">
	</a>
	
	
	<div class="ui huge secondary pointing stackable menu violet">
	    <a class="item">새 포스트</a>
	    <a class="item">좋아한 글</a>
	    <a class="item">태그</a>
	    <div class="right menu">
	        <div class="item">
	            <div class="ui small input">
	                <input placeholder="검색" type="text" />
	            </div>
	        </div>
	        <a class="ui item">로그아웃</a>
	    </div>
	</div>
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
	
</body>
</html>