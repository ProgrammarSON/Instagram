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
    <title>Yestagram</title>
    
    <script>
    
    </script>
</head>

<body>
    <div class="ui container">
        <jsp:include page="navbar.jsp"/>

        <div class="ui grid container">

            <div class="five wide column center aligned">
	            <div class="div-profile">
	            	<div class="div-profile-img">
	               		<img id="my_profile_img" class="image" src="./profile_image/ronaldo.jpg">
	               	</div>
	            </div>
            
            
            </div>

            <div class="eleven wide middle aligned column">
                
                <h1 class="ui header" id="header_hashtag">
					#<span>ronaldo</span>
                </h1>
            
                <div class="ui relaxed horizontal list">
                    <div class="item">
                        <div class="content">
                        	게시물 <span>123,456,789</span>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>

        <div class="ui divider"></div>


        <div class="ui three cards">
    		<div class="card">
       			<div class="content">
          			<div class="right floated meta">14h</div>
		             	<img class="ui avatar image" src="profile_image/null.jpg"> 
       			</div>
             	<div class="image">
              		<img src="./feed_image/null.jpg">
              	</div>
                <div class="content">
                    <div class="description">
<%--                     	<%=map.get(key).getContents() %> --%>
                    </div>
                </div>
                <div class="extra content">
                    <span class="right floated">
                        <i class="heart outline like icon"></i> 좋아요 9
                    </span>
                    <i class="comment icon"></i>댓글 6
                </div>
        </div>
    </div>

	<jsp:include page="footer.jsp"/>