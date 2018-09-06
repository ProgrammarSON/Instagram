<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.newsfeed.*" %>
<%@ page import="java.util.*" %>

<%
	LinkedHashMap<String,feedDTO> map = (LinkedHashMap<String,feedDTO>)request.getAttribute("map");
	String hashtag = (String)request.getAttribute("hashtag");
	String imagePath = (String)request.getAttribute("imagePath");
%>

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
	               		<img id="my_profile_img" class="image" src="feed_image/<%=imagePath%>">
	               	</div>
	            </div>
            
            
            </div>

            <div class="eleven wide middle aligned column">
                
                <h1 class="ui header" id="header_hashtag">
					#<span><%=hashtag%></span>
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


       <div class="ui three cards dim-card">
            <% for(String key : map.keySet()) { %>
			<div class="card">
				<a class="image centered-and-cropped" href="viewcomment.do?feed_id=<%=key%>">
					<div class="ui dimmer">
		            	<div class="content">
		            		<!-- 좋아요 표시 시작 --><!-- 좋아요 표시 끝 -->
		            		<i class="heart icon"></i>
		            		<span style="margin-right: 25px;"><%=map.get(key).getLike_count() %></span>
		            		<!-- 댓글 표시 시작 -->
							<i class="comment icon"></i>
							<span><%=map.get(key).getComment_count() %></span>
							<!-- 댓글 표시 끝 -->
		            	</div>
	              	</div>
	              	<div class="cropped-image">
              	<% if(map.get(key).getImage_path() == null) { %>
	            	<img class="image centered-and-cropped" src="./feed_image/null.jpg">
	              	<% } else { %>
	                <img class="image centered-and-cropped" src="./feed_image/<%= map.get(key).getImage_path() %>">
              	<% } %>
              		</div>
				</a>
			</div>
        <% } %>
    </div>

	<jsp:include page="footer.jsp"/>