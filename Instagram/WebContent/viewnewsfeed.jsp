<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.newsfeed.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//feedDAO dao = new feedDAO();
	//LinkedHashMap<String,feedDTO> map = dao.getNewsFeed("sjw");
	LinkedHashMap<String,feedDTO> map =(LinkedHashMap<String,feedDTO>) request.getAttribute("map"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
<script src="semantic/dist/semantic.min.js"></script>

   <!-- Custom -->
<link rel="stylesheet" type="text/css" href="style.css">
<script src="script.js"></script>
<title>Yestagram</title>
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
        
<div class="ui three stackable cards">
    <%for(String key : map.keySet()){%>
    <div class="card">
       <div class="content">
          <div class="right floated meta">14h</div>
             <img class="ui avatar image" src="./images/avatar/large/elliot.jpg"> <%=map.get(key).getUser_id()%>
       </div>
             <div class="image">
                <img src="./feed_image/<%=map.get(key).getImage_path()%>"><br>
              </div>
                <div class="content">
                    <div class="description">
                    	<%=map.get(key).getContents() %>
                    </div>
                </div>
                <div class="extra content">
                    <span class="right floated">
                        <i class="heart outline like icon"></i> 좋아요 9
                    </span>
                    <i class="comment icon"></i> <a href="viewcomment.do?feed_id=<%=key%>">댓글 6</a>
                </div>
     </div>
     <%} %>
	
</div>
</body>
</html>