<%@ page import="com.myfeed.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	myfeedDTO dto = (myfeedDTO)request.getAttribute("dto"); 
	int check = (int)request.getAttribute("check");
	String user_id = (String)session.getAttribute("id");
	String follow_id = dto.getUser_id();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="semantic/dist/semantic.min.js"></script>

    <!-- Custom -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/script.js"></script>
    <title>Yestagram</title>
    
    <script>
    $(document).ready(function(){
    	var id = "<%=user_id %>";
    	var follow = "<%=follow_id %>";
    	var state = <%=check%>
    	
    	if(state == -1)
    	{
    		$("#following").show();
    		$("#followingjung").hide();
    		$("#modify").hide();
    	}else if(state == 0){
    		$("#following").hide();
    		$("#followingjung").hide();
    		$("#modify").show();
    	}else{
    		$("#following").hide();
    		$("#followingjung").show();
    		$("#modify").hide();
    	}   	
    	
    	
    	$("#following").click(function(){
        	$.ajax({
            	url: "following.do?user_id=<%=user_id%>&follow_id=<%=follow_id%>",
            	success : function(result){
                	var check = JSON.parse(result);
					console.log(check);                
            	}
        	})
    	})
    	
    	$("#followingjung").click(function(){
        	$.ajax({
            	url: "following.do?user_id=<%=user_id%>&follow_id=<%=follow_id%>",
            	success : function(result){
                	var check = JSON.parse(result);
					console.log(check);                
            	}
        	})
    	})
    	
    	
    });
    </script>
</head>

<body>
    <div class="ui container">
        <a class="" href="#">
            <img src="images/yestagram.png" class="ui image centered small" id="brand" alt="Yestagram">
        </a>

        <div class="ui huge secondary pointing stackable menu violet">
            <a class="item" href="./writenewsfeed.jsp">새 포스트</a>
            <a class="item">좋아한 글</a>
            <a class="item">태그</a>
            <div class="right menu">
                <div class="item">
                    <div class="ui small input">
                        <input placeholder="검색" type="text" />
                    </div>
                </div>
                <a class="ui item" href="./member/logout.jsp">로그아웃</a>
            </div>
        </div>

        <div class="ui grid container">

            <div class="five wide column">
                <img class="ui image small circular centered" src="./profile_image/<%=dto.getProfile_img() %>">
            </div>

            <div class="eleven wide column">
                
                <h1 class="ui header" id="header_user">
                    <%=follow_id %>
                </h1>
            
                <div class="ui relaxed horizontal list">
                    <div class="item">
                        <div class="content">
                            게시물 <span><%=dto.getFeed_num() %></span>
                        </div>
                    </div>

                    <div class="item">
                        <div class="content">
                            팔로워 <span><%=dto.getFollower_num() %></span>
                        </div>
                    </div>

                    <div class="item">
                        <div class="content">
                            팔로우 <span><%=dto.getFollowing_num() %></span>
                        </div>
                    </div>
                </div>

                <div class="ui column">
                    <p>
                        <%=dto.getContents() %><br>
                    </p>
                </div>
                <div class="ui column">
                    <p>
                    <form>
                    	<button type="button" id="following">팔로우</button>
                    	<button type="button" id="followingjung">팔로잉중</button>
                    	<button type="button" id="modify">프로필 수정</button>
                    </form>
                    </p>
                </div>
                
            </div>
        </div>

        <div class="ui divider"></div>


        <div class="ui three cards">
            <div class="card">
                <div class="image">
                    <img src="./images/avatar/large/elliot.jpg">
                </div>
            </div>

            <div class="card">
                <div class="image">
                    <img src="./images/avatar/large/elliot.jpg">
                </div>
            </div>

            <div class="card">
                <div class="image">
                    <img src="./images/avatar/large/elliot.jpg">
                </div>
            </div>

            <div class="card">
                <div class="image">
                    <img src="./images/avatar/large/elliot.jpg">
                </div>
            </div>

            <div class="card">
                <div class="image">
                    <img src="./images/avatar/large/elliot.jpg">
                </div>
            </div>

            <div class="card">
                <div class="image">
                    <img src="./images/avatar/large/elliot.jpg">
                </div>
            </div>

            <div class="card">
                <div class="image">
                    <img src="./images/avatar/large/elliot.jpg">
                </div>
            </div>

            <div class="card">
                <div class="image">
                    <img src="./images/avatar/large/elliot.jpg">
                </div>
            </div>

        </div>
    </div>

</body>

</html>