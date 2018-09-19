<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.*" %>

<%	memberDTO dto = (memberDTO)request.getAttribute("dto"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="semantic/dist/semantic.min.js"></script>
	
    <!-- Custom -->
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/script.js"></script>
    <script src="js/signup.js"></script>
    <title>프로필 수정</title>

	
</head>

<body>
	<!-- 내비게이션(메뉴) 바 시작 -->
	<jsp:include page="navbar.jsp"/>
    <!-- 내비게이션(메뉴) 바 끝 -->

	<!-- 페이지 전체 컨테이너 시작 -->
    <div class="ui container">

		<!-- 프로필 구역 시작 -->
        <div class="ui segment">
        	<form class="ui large form" action="modifyuserinfo.do" method="post" enctype="Multipart/form-data">
         		<h1 class="ui header">프로필 수정</h1>
        		
        		<div class="field">
        			<label>아이디</label>
        			<input type="email" name="email" placeholder="아이디" value="<%=dto.getEmail() %>" disabled/>
        		</div>
        		
       			<div class="field">
					<label id="user_image_label">프로필 사진</label>
					<div id="div-avatar">
						<div class="image centered-and-cropped" id="choice_avatar">
	                        <div class="ui dimmer">
	                        	<label for="profilePath" class="ui inverted button violet">사진 선택</label>
	                        	<label class="ui inverted button violet" id="deleteimg">사진 삭제</label>
	                            
	                            <input type="file" name="profilePath" id="profilePath" onchange="readURL(this);">
	                            <input type="hidden" name="original_img" id="original_img" value="<%=dto.getProfile_img()%>">
	                        </div>
	                        <img class="image centered-and-cropped dim_pic" id="defaultimg" src="profile_image/<%=dto.getProfile_img()%>">
	                    </div>
                    </div>
				</div>
				
        		<div class="two fields">
					<div class="field">
						<label>이름</label>
						<input type="text" name="username" placeholder="이름" value="<%=dto.getUsername() %>">
					</div>
					<div class="field">
						<label>별명</label>
						<input type="text" name="user_id" placeholder="별명" value="<%=dto.getUser_id() %>">
					</div>
				</div>
				
				<div class="two fields">
					<div class="field">
						<label>비밀번호</label>
						<input type="password" name="password" placeholder="비밀번호">
					</div>
					<div class="field">
						<label>비밀번호 확인</label>
						<input type="password" name="password-check" placeholder="비밀번호 확인">
					</div>
				</div>
				
				<div class="field">
					<label>프로필 소개</label>
					<input type="text" name="contents" placeholder="프로필 소개" value="<%=dto.getContents() %>">
				</div>
				
				<div class="two fields">
					<div class="field">
						<button type="reset" class="ui fluid large button" onclick="history.go(-1)">취소</button>
					</div>
					<div class="field">
						<button type="submit" class="ui fluid large button violet">수정 완료</button>
					</div>
				</div>
        	</form>
        </div>
        <!-- 프로필 구역 끝 -->
        
		<!-- 카드 -->
        
	</div>
	<!-- 페이지 전체 컨테이너 끝 -->
	
	<script>
	$('#choice_avatar.image').dimmer({
        on: 'hover'
    });
	
	$('#deleteimg').click(function(){
		$('#defaultimg').attr('src','profile_image/jenny.jpg');
		$('#original_img').val('jenny.jpg');		
	});
	</script>
	
	<!-- 푸터 시작 -->
	<jsp:include page="footer.jsp"/>
	<!-- 푸터 끝 -->