<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
    <title>프로필 수정</title>
    
    <script>
    </script>
</head>

<body>
    <div class="ui container">
    	<!-- 내비게이션(메뉴) 바 시작 -->
        <jsp:include page="navbar.jsp"/>
        <!-- 내비게이션(메뉴) 바 끝 -->

		<!-- 프로필 구역 시작 -->
        <div class="ui segment">
        	<form class="ui large form" action="" method="">
        		<h1 class="ui header">username</h1>
        		<div class="two fields">
        			<div class="field">
						<label>프로필 사진</label>
					</div>
					<div class="field">
	        			<label>아이디</label>
	        			<input type="email" name="" placeholder="아이디">
	        		</div>
        		</div>
        		
        		<div class="two fields">
					<div class="field">
						<label>이름</label>
						<input type="text" name="" placeholder="이름">
					</div>
					<div class="field">
						<label>별명</label>
						<input type="text" name="" placeholder="별명">
					</div>
				</div>
				
				<div class="two fields">
					<div class="field">
						<label>비밀번호</label>
						<input type="password" name="" placeholder="비밀번호">
					</div>
					<div class="field">
						<label>비밀번호 확인</label>
						<input type="password" name="" placeholder="비밀번호 확인">
					</div>
				</div>
				
				<div class="field">
					<label>프로필 소개</label>
					<input type="text" name="" placeholder="프로필 소개">
				</div>
				
				<div class="ui fluid large button violet">수정 완료</div>
        	</form>
        </div>
        <!-- 프로필 구역 끝 -->
        
		<!-- 카드 -->
        
    
		<!-- 푸터 시작 -->
		<jsp:include page="footer.jsp"/>
		<!-- 푸터 끝 -->
	</div>