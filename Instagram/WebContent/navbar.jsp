<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String id = (String)session.getAttribute("id"); %>
	<div class="menu_padding"></div>
    <div class="ui large secondary pointing main menu violet">
    	<div class="ui container">
    	
			<a class="item brand" href="viewnewsfeed.do">
				<img src="images/yestagram.png" class="ui image small" id="brand" alt="Yestagram">
	    	</a>

<!--         <a class="item" href="./writenewsfeed.jsp"><i class="image icon"></i>새 포스트</a> -->
	        <a class="item" href="./writenewsfeed.jsp">
        		<i class="plus square outline violet large icon"></i>
<!-- 	        		새 포스트 -->
	        </a>
	        <a class="item" id="" href="viewmyfeed.do?user_id=<%=id%>">
	        	<i class="images outline large icon"></i>
<!-- 	        	내 피드 -->
	        </a>
	        <a class="item disabled" id="liked">
	        	<i class="heart outline large icon"></i>
<!-- 	        	좋아한 글 -->
	        </a>
	        <a class="item disabled">
	        	<i class="hashtag large icon"></i>
<!-- 	        	해시태그 -->
	        </a>
	        
	        <div class="right menu">
	            <div class="item">
	                <div class="ui tiny icon input">
	                    <input placeholder="검색" type="text" id="searchid" />
	                    <i class="search link icon"></i>
	                </div>
	                <div id="viewsearch" class="results"></div> 
	            </div>
	            
	            <a class="item" href="./member/logout.jsp">
	            	<i class="user large icon"></i>
	            	로그아웃
	            </a>
	        </div>
        </div>
    </div>

<!--     <div class="ui custom popup bottom center transition hidden"></div> -->