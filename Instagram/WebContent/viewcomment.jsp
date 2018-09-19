<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.newsfeed.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String feed_id = (String)request.getAttribute("feed_id");
	String user_id = (String)session.getAttribute("id");
	feedDTO dto = (feedDTO)request.getAttribute("dto"); 
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
    <title>포스트</title>
	
	<!-- 대댓글  -->
    <script>
    $(document).ready(function(){
    	
    <c:forEach items="${list}" var="dto">
        
    	 $("#${dto.getComment_id()}").hide();
    	 $("#${dto.getComment_id()}_reply_hide").hide();
    	     	 
    	$("#${dto.getComment_id()}_reply_show").click(function(){
        	$("#${dto.getComment_id()}").show();
    		$("#${dto.getComment_id()}_reply_show").hide();
    		$("#${dto.getComment_id()}_reply_hide").show();
   		});
    	
    	$("#${dto.getComment_id()}_reply_hide").click(function(){
    		$("#${dto.getComment_id()}").hide();
    		$("#${dto.getComment_id()}_reply_show").show();
    		$("#${dto.getComment_id()}_reply_hide").hide();
		});
    	
    	$.ajax({
    		url:"viewreply.do?comment_id=${dto.getComment_id()}",
		    success : function(result){
		    	var datas = JSON.parse(result);
		    	//console.log(dates[0].contents);
		    	for(i=0; i<datas.length; i++){
		    	//	$("#${dto.getComment_id()}").html("hhhhhhhh"+datas[0].contents);
		    		console.log(datas[i].contents);
		    				    		
		    		$("#${dto.getComment_id()}_reply_id").append(
			    		"<div class='comment'>" +
			    		" <a class='avatar'> " +
	                    " <img src='profile_image/"+ datas[i].img_path+"'> "+
	                  	" </a> " +
	                  	"<div class='content'> "+
	                  		"<a class='author'> " + datas[i].user_id + "</a>" +
	                  	"<div class='metadata'>" +
	        				"<span class='date'> "+datas[i].reply_date +"</span> " +
		      				"</div> " +
	    				"<div class='text'> " + datas[i].contents + "</div>" +
	        			"</div>"+
						"</div>"
    				);
		    	}
		    }
    	});
    	
    	$("#${dto.getComment_id()}_reply_write").click(function(){
			var id = "<%=user_id%>";
			var contents = $("#${dto.getComment_id()}_reply_contents").val();
			
			$.ajax({
				url:"writereply.do?comment_id=${dto.getComment_id()}&user_id="+id+"&contents="+contents,
				success : function(result) {
					var datas = JSON.parse(result);
					
					$("#${dto.getComment_id()}_reply_id").prepend(
			    		"<div class='comment'>" +
			    		" <a class='avatar'> " +
                        " <img src='profile_image/"+datas[i].img_path+"'>"+
                      	" </a> " +
                      	"<div class='content'> "+
                      		"<a class='author'> " + id + "</a>" +
                      	"<div class='metadata'>" +
            				"<span class='date'>방금 전</span> " +
  	      				"</div> " +
        				"<div class='text'> " + contents + "</div>" +
            			"</div>"+
						"</div>"
					);
					$("#${dto.getComment_id()}_reply_contents").val("");
				}
			});  	
		});
          
 	</c:forEach>
 	
 		$(".a_edit").click(function(){
 			$('.coupled.modal')
 			  .modal({
 			    allowMultiple: false
 			  })
 			;
        	$('.ui.basic.first.modal').modal('show');
   			
        	$('.mini.second.modal')
 		   .modal('attach events', '.ui.button.red')
 			; 	
 		});
 		
 		$('.ui.negative.button').click(function(){
 			document.location.href="deletenewsfeed.do?feed_id=<%=feed_id%>";	
 		});
 		
 		$('.ui.button.olive').click(function(){
 			document.location.href="viewmodifynewsfeed.do?feed_id=<%=feed_id%>";
 		});
 		
		var key="<%=feed_id%>";
		
		$("#"+key+"like").click(function(){
			//$(this).find('i').toggleClass('outline')
			if($(this).find('i').hasClass('outline')) {
				$(this).find('i').removeClass('outline').css('color','#ff2733');
				$.ajax({
					url: "like.do?feed_id="+key+"&check=like",
					success : function(result){
						var check = JSON.parse(result);
						var value = parseInt($('#like_count').text())+1;
						$('#like_count').text(value);
						console.log(value);
					}
					
				});
			} else {
				$(this).find('i').addClass('outline').css('color','rgba(0,0,0,.4)');
				$.ajax({
					url: "like.do?feed_id="+key+"&check=unlike",
					success : function(result){
						var check = JSON.parse(result);
						var value = parseInt($('#like_count').text())-1;
						$('#like_count').text(value);
						console.log(value);
					}
				});
			};
		});
 		
	
    });
 	</script>
 	
    <script>     
         //var content = document.querySelector("#hoho");
        $(document).ready(function(){
			var content = document.getElementById("feed_contents").innerHTML;
        	var splited = content.split(' ');
        	var linked='';

        	for(var word in splited)
        	{
            	//console.log(word);
        		word = splited[word];
        		//console.log(word);
        		
        		word = word.replace(/#([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣_-]+)/g, '<a href ="viewhashtag.do?hashtag='+'$1'+'">#'+'$1'+'</a>');
        		word = word.replace(/\n/g,"<br>");
        		
        		/* if(word.indexOf('#') == 0) // # 문자를 찾는다.
             	{
                	 word = '<a href="#">'+word+'</a>'; 
               	} */           	
          			
              	linked += word+' ';
           		console.log(linked);
        	}
        	document.getElementById('feed_contents').innerHTML = linked;
              
        });
     </script>
     
     <script>
    <%--  $(document).ready(function(){
    	 var feedid = "<%=feed_id%>"; 
    	 $('#'+feedid+'_comment_write').click(function(){
 			var id = "<%=user_id%>";
 			var contents = $("#comment_text").val();
 			
 			console.log(contents);
 			$.ajax({
				url:"writecomment.do?comment_feed_id="+feedid+"&comment_content="+contents,
				success : function(result) {
					var datas = JSON.parse(result);
					
					
				}
			});  	
 		}); 
     }); --%>
     
     </script>

</head>

<body>
	<!-- 내비게이션(메뉴) 바 시작 -->
    <jsp:include page="navbar.jsp"/>
    <!-- 내비게이션(메뉴) 바 끝 -->
    
	<!-- 페이지 전체 컨테이너 시작 -->
    <div class="ui grid container" id="container_comment">

       	<!-- 왼쪽 영역 시작 -->
       	<div class="sixteen wide mobile ten wide tablet ten wide computer column">
	        <div class="ui container">	
				<img id="posted_image" class="ui image fluid" src="feed_image/<%=dto.getImage_path() %>" alt="upload image">
	       	</div>
       	</div>
       	<!-- 왼쪽 영역 끝 -->
       	
       	<!-- 오른쪽 영역 시작 -->
       	<div class="sixteen wide mobile six wide tablet six wide computer column">
	        <div class="ui container">
	        	<!-- user_id, 프로필 이미지  -->
	        	<div class="ui large middle aligned list">
	        		<div class="item">
		        		<div class="right floated content">

	        			<%if(user_id.equals(dto.getUser_id())){ %>
							<a class="a_edit"><i class="ellipsis horizontal circular violet link icon"></i></a>
						<%} %>

						</div>
						<img class="ui avatar image" src="profile_image/<%=dto.getProfile_img()%>">
						<div class="content">
							<a href="viewmyfeed.do?user_id=<%= dto.getUser_id() %>" class="header"><%=dto.getUser_id()%></a>							
						</div>		        		
	        		</div>
	        	</div>
	           	
	        	
	        	<div id="feed_contents">
	        	<% if(dto.getContents() != null) { %><span><%=dto.getContents() %></span>
	        		<% } else { %><span></span><% } %></div>
	           	<div id="feed_hashtag"></div>
	           
	           	<!-- 댓글 달기 -->
	            <div class="div-comments">
	                <form class="ui reply form" action="writecomment.do">
	                    <input type="hidden" name="comment_feed_id" value=<%=feed_id%>>
	                    
	                    <div class="field">
	                        <textarea placeholder="댓글을 작성해보세요." rows="2" name="comment_content" id="comment_text"></textarea>
	                    </div>
                      		<button class="ui button fluid violet" type="submit" id="<%=feed_id%>_comment_write">작성하기</button>
	                </form>
	            </div>
	        	<h4 class="ui text" id="<%=feed_id%>like">
	        		<%if(dto.getLike_state().equals("unlike")){ %>
						<i class="heart like icon outline"></i> 
					<%}else{ %>
						<i class="heart like icon" style="color: #ff2733"></i>
					<%} %>
					좋아요 <like id="like_count"> <%=dto.getLike_count() %></like> 개
	        	</h4>
	        	
	        	<h4 class="ui dividing header"><%=dto.getComment_count() %>개의 댓글</h4>
	        	
				<div class="ui small comments">
					
				<!--  댓글 출력  -->
				<div id="comment_id">
					<c:forEach items="${list}" var="dto">
	                <div class="comment"> 
						<a class="avatar">
	                    	<c:if test="${dto.getImg_path() ne null}">
								<img src="profile_image/${dto.getImg_path()}">
	                        </c:if>
	                         
							<c:if test="${dto.getImg_path() eq null}">
	                        	<img src="profile_image/null.jpg">
	                        </c:if>
						</a>
						<div class="content">
							<a class="author" href="viewmyfeed.do?user_id=${dto.getUser_id()}">${dto.getUser_id()}</a>  <!-- 사용자 이름 -->
	                        <div class="metadata">
	                            <span class="date">${dto.getComment_date()}</span>
	                        </div>
	                        <div class="text">${dto.getContent()}</div>
	                        <div class="actions">
	                            <a class="reply" id="${dto.getComment_id()}_reply_show">댓글 보기</a>
	                            <a class="reply" id="${dto.getComment_id()}_reply_hide">댓글 숨기기</a>
	                        </div>
	                        <div class="ui reply form">
	                        	<div class="field" id="${dto.getComment_id()}">
	                            	<textarea placeholder="댓글을 작성해보세요." id="${dto.getComment_id()}_reply_contents"></textarea>
									<div id="div-reply-button">
		                        		<button class="ui button fluid violet" type="submit" id="${dto.getComment_id()}_reply_write">작성하기</button>
									</div>
									
									<!-- 대댓글 리스트 -->
		                            <div class="ui small comments" id="${dto.getComment_id()}_reply_id"></div>
								</div>
							</div>
						</div>
					</div>
					</c:forEach>
	             </div>				
				</div>
			</div>
			
		</div>
		<!-- 오른쪽 영역 끝 -->

    </div>
    <!-- 페이지 전체 컨테이너 끝 -->
	
	<div class="ui basic modal first coupled">
		<div class="content" align="center">
			<button type="button" class="ui button olive"><i class="edit icon"></i>게시물 수정</button>
			<button type="button" class="ui button red"><i class="remove icon"></i>게시물 삭제</button>
		</div>
	</div>
	
	<div class="ui mini test modal second coupled">
    	<div class="header">
      		 삭제 확인
    	</div>
    <div class="content">
      <p>이 게시물을 삭제 하시겠습니까?</p>
    </div>
    <div class="actions">
      <div class="ui positive right button">
        	삭제 안 함
      </div>
      
       <div class="ui negative button">
        	삭제
      </div>
    </div>
  </div>

    <jsp:include page="footer.jsp" />