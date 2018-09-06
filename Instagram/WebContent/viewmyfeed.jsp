<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.newsfeed.*" %>
<%@ page import="com.myfeed.*" %>

<% 
	myfeedDTO dto = (myfeedDTO)request.getAttribute("dto"); 
	int check = (int)request.getAttribute("check");
	String user_id = (String)session.getAttribute("id");
	String follow_id = dto.getUser_id();
	String profile_img = (String)request.getAttribute("profile_img");
	LinkedHashMap<String,feedDTO> map =(LinkedHashMap<String,feedDTO>) request.getAttribute("map");	
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
    $(document).ready(function(){
    	var id = "<%=user_id %>";
    	var follow = "<%=follow_id %>";
    	var state = "<%=check%>";
    	
    	
    	if(state == -1)		//상대 계정과 follow가 안되어 있음
    	{
    		$("#following_btn").show();
    		$("#followingjung_btn").hide();
    		$("#modify_btn").hide();
    	}else if(state == 0){		//내 계정 
    		$("#following_btn").hide();
    		$("#followingjung_btn").hide();
    		$("#modify_btn").show();
    	}else{						//상대 계정을 팔로우중
    		$("#following_btn").hide();
    		$("#followingjung_btn").show();
    		$("#modify_btn").hide();
    	}   	
    	    
    	$("#following_btn").click(function(){
        	$.ajax({
            	url: "following.do?user_id="+id+"&follow_id="+follow+"&check=follow",
            	success : function(result){
                	var check = JSON.parse(result);
    				console.log(check);                
            	}
        	})  
        	$("#following_btn").hide();
    		$("#followingjung_btn").show();
    	});
    	
    	$("#followingjung_btn").click(function(){
        	$.ajax({
            	url: "following.do?user_id="+id+"&follow_id="+follow+"&check=unfollow",
            	success : function(result){
                	var check = JSON.parse(result);
    				console.log(check);                
            	}
        	})
        	$("#following_btn").show();
    		$("#followingjung_btn").hide();
       	});
    });    	
    </script>
</head>

<body>
	<!-- 페이지 전체 컨테이너 시작 -->
    <div class="ui container">
    	<!-- 내비게이션(메뉴) 바 시작 -->
        <jsp:include page="navbar.jsp"/>
        <!-- 내비게이션(메뉴) 바 끝 -->

		<!-- 프로필 구역 시작 -->
        <div class="ui grid container">

            <div class="five wide column center aligned">
	            <div class="div-profile">
	            	<div class="div-profile-img">
	               		<img id="my_profile_img" class="image" src="./profile_image/<%=dto.getProfile_img() %>">
	               	</div>
	            </div>
            </div>

            <div class="eleven wide middle aligned column">

                <div class="ui header" id="header_user">
                    <span id="header_userid"><%=follow_id %></span>
                    <span id="header_button">
		                <button type="button" class="ui tiny basic button violet" id="following_btn">팔로우</button>
		               	<button type="button" class="ui tiny button violet" id="followingjung_btn">팔로잉</button>
		               	<button type="button" class="ui tiny basic button violet" id="modify_btn">프로필 수정</button>
	            	</span>
                </div>
            	
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
							<a id="show_modal_follow">팔로우 <span><%=dto.getFollowing_num() %></span></a>
                        </div>
                    </div>
                </div>

                <div class="ui column">
                    <p>
                        <%=dto.getContents() %>
                    </p>
                </div>
            </div>
        </div>
		
        <div class="ui divider"></div>
        <!-- 프로필 구역 끝 -->
        
        <!-- 모달 시작 -->
        <div class="ui modal">
			<div class="ui icon header">
				<i class="archive icon"></i>
				Archive Old Messages
			</div>
			<div class="content">
				<p>Your inbox is getting full, would you like us to enable automatic archiving of old messages?</p>
			</div>
			<div class="actions">
				<div class="ui red basic cancel inverted button">
					<i class="remove icon"></i>
					No
				</div>
				<div class="ui green ok inverted button">
					<i class="checkmark icon"></i>
					Yes
				</div>
			</div>
		</div>
        <!-- 모달 끝 -->
        
		<!-- 카드 -->
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
    </div>
    <!-- 페이지 전체 컨테이너 끝 -->
	
	<!-- 푸터 시작 -->
	<jsp:include page="footer.jsp"/>
	<!-- 푸터 끝 -->
	