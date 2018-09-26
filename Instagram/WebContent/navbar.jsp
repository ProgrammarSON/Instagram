<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String id = (String)session.getAttribute("id"); %>
<script src="js/activelog.js"></script>

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
	        <a class="item" id="liked-icon">
	        	<i class="heart outline large icon"></i>
<!-- 	        	좋아한 글 -->
	        </a>
	        <a class="item disabled">
	        	<i class="hashtag large icon"></i>
<!-- 	        	해시태그 -->
	        </a>
	        
	        <div class="right menu">
	            <form autocomplete="off" action="viewmyfeed.do">
	            <div class="item" id="item_search">
	                <div class="ui tiny icon input autocomplete">
	                    <input placeholder="검색" type="text" id="searchid" name="user_id" />
	                    <i class="search link icon"></i>
	                </div>
	                <div id="viewsearch" class="results"></div> 
	            </div>
	            </form>
	            <div class="ui simple dropdown item">
	            	<i class="user large icon"></i><%=id %>
		            <div class="menu">
		            	<a class="item" href="userinfo.do">프로필 수정</a>
		            	<a class="item" href="./member/logout.jsp">로그아웃</a>
		            </div>
	            </div>
	        </div>
        </div>
    </div>
    
            <!-- 모달 시작 -->
        <div class="ui tiny modal" id="active-modal">
<!--<div class="header"></div> -->
			<div class="scrolling content">
				<div class="ui very relaxed celled selection list" id="active_feed">
				</div>
			</div>
			<div class="actions">
				<button class="ui tiny button basic close">닫기</button>
			</div>
		</div>
        <!-- 모달 끝 -->

<!--     <div class="ui custom popup bottom center transition hidden"></div> -->