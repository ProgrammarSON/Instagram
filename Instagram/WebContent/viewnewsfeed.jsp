<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.newsfeed.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String user_id = (String)session.getAttribute("id");
	LinkedHashMap<String,feedDTO> map =(LinkedHashMap<String,feedDTO>) request.getAttribute("map"); 
	int check;
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
	<title>Yestagram</title>


<script>
	function readURL(input){ 
		if (input.files && input.files[0]) { 
			var reader = new FileReader(); 
			reader.onload = function (e) { 
				$('#blah').attr('src', e.target.result); 
			} 
			reader.readAsDataURL(input.files[0]); 
		}
	}
	
</script>

<script>
<%for(String key : map.keySet()){ %>

	$(document).ready(function(){
			//key는 게시물 번호
			var key = "<%=key%>";
			console.log(key);
			$("#"+key+"like").click(function(){
				//$(this).find('i').toggleClass('outline')
				if($(this).find('i').hasClass('outline')){
					$(this).find('i').removeClass('outline').css('color','#ff2733');
					$.ajax({
						url: "like.do?feed_id="+key+"&check=like",
						success : function(result){
							var check = JSON.parse(result);
						}
					})
					
				}else{
					$(this).find('i').addClass('outline').css('color','rgba(0,0,0,.4)');
					$.ajax({
						url: "like.do?feed_id="+key+"&check=unlike",
						success : function(result){
							var check = JSON.parse(result);
						}
					})				
				}
				
			});
		
	});
<%} %>
</script>

</head>

<body>
   <div class="ui container">
        <jsp:include page="navbar.jsp"/>
        
<div class="ui three stackable cards">
    <%for(String key : map.keySet()){%>			<!-- key는 게시물 번호 -->
    <div class="card">
       <div class="content">
          <div class="right floated meta">14h</div>
             <%if(map.get(key).getProfile_img() == null){ %>
             	<img class="ui avatar image" src="profile_image/null.jpg">
             <%}else { %>
             	<img class="ui avatar image" src="profile_image/<%=map.get(key).getProfile_img()%>">
             <%} %> 
             <a href="viewmyfeed.do?user_id=<%=map.get(key).getUser_id()%>"><%=map.get(key).getUser_id()%></a>
       </div>
             <div class="image">
             <%if(map.get(key).getImage_path() == null){ %>
              	<img src="./feed_image/null.jpg"><br>
              <%}else{ %>
                <img src="./feed_image/<%=map.get(key).getImage_path()%>"><br>
              <%} %>
              </div>
                <div class="extra content">
                    <span class="right floated" id="<%=key%>like">
                    	<%if(map.get(key).getLike_state().equals("unlike")){ %>
                        	<i class="heart like icon outline"></i> <%=map.get(key).getLike_count() %>
                        <%}else{ %>
                        	<i class="heart like icon" style="color: #ff2733"></i> <%=map.get(key).getLike_count() %>
                        <%} %>
                    </span>
                    <i class="comment icon"></i> <a href="viewcomment.do?feed_id=<%=key%>"><%=map.get(key).getComment_count() %></a>
                </div>
     </div>
     <%} %>
</div>
<jsp:include page="footer.jsp"/>