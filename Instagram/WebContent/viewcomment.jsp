<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import ="com.newsfeed.*"%>
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
    <c:forEach items="${list}" var="dto">
        <script>

            $(document).ready(function(){
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
        				"<span class='date'>방금 전</span> " +
	      				"</div> " +
    				"<div class='text'> " + datas[i].contents + "</div>" +
        			"</div>"+
					"</div>"
    			);
		    		
		    	}
		    }});
		
		$("#${dto.getComment_id()}_reply_write").click(function(){
			var id = "<%=user_id%>";
			var contents = $("#${dto.getComment_id()}_reply_contents").val();
			$.ajax({
					url:"writereply.do?comment_id=${dto.getComment_id()}&user_id="+id+"&contents="+contents,
					success : function(result){
						var datas = JSON.parse(result);
						
						console.log()
						$("#${dto.getComment_id()}_reply_id").append(
					    		"<div class='comment'>" +
					    		" <a class='avatar'> " +
		                        " <img src='images/avatar/small/jenny.jpg'> "+
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
			}});
              	
		});
	});
            
 	</script>
    </c:forEach>
    
    <script>     
         //var content = document.querySelector("#hoho");
        $(document).ready(function(){
			var content = document.getElementById("feed_contents").innerHTML;
        	var splited = content.split(' ');
        	var linked='';

        	for(var word in splited)
        	{
            	console.log(word);
        		word = splited[word];
        		console.log(word);
             	if(word.indexOf('#') == 0) // # 문자를 찾는다.
             	{
                	 word = '<a href="#">'+word+'</a>'; 
               	}
             	
              	linked += word+' ';
           	}
        	document.getElementById('feed_contents').innerHTML = linked;
        });
     </script>
    
</head>

<body>

    <div class="ui container">
        <jsp:include page="navbar.jsp" />
        
        
        <div class="ui grid">
        	<div class="sixteen wide mobile ten wide tablet nine wide computer column">
		        <div class="ui container end-div">	
					<img id="posted_image" class="ui image fluid" src="feed_image/<%=dto.getImage_path() %>" alt="upload image">
					<div id="feed_contents"><%=dto.getContents() %></div>
		           	<div id="feed_hashtag"></div>
		       	</div>
	       	</div>
	       	
	       	
	       	<div class="sixteen wide mobile six wide tablet seven wide computer column">
		        <div class="ui container">
		            <div class="div-comments">
		                <form class="ui reply form" action="writecomment.do">
		                    <input type="hidden" name="comment_feed_id" value=<%=feed_id%>>
		                    
		                    <div class="field">
		                        <textarea placeholder="댓글을 작성해보세요." rows="3" name="comment_content"></textarea>
		                    </div>
		                    <button class="ui button violet right floated" type="submit">작성하기</button>    
		                </form>
		            </div>
		        </div>
	
				<div class="ui container">
					<div class="ui threaded comments">
						<h3 class="ui dividing header">#개의 댓글</h3>
		              
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
								<a class="author">${dto.getUser_id()}</a> <!-- 사용자 이름 -->
		                        <div class="metadata">
		                            <span class="date">${dto.getComment_date()}</span>
		                        </div>
		                        <div class="text">
		                            <p>${dto.getContent()}</p>
		                        </div>
		                        <div class="actions">
		                            <a class="reply" id="${dto.getComment_id()}_reply_show">댓글 보기</a>
		                            <a class="reply" id="${dto.getComment_id()}_reply_hide">댓글 숨기기</a>
		                        </div>
		                        <div class="field" id="${dto.getComment_id()}">
		                            <div class="ui reply form">
										<textarea placeholder="댓글을 작성해보세요." id="${dto.getComment_id()}_reply_contents"></textarea>
										<div class="ui right aligned container">
			                        		<button class="ui tiny button violet" type="submit" id="${dto.getComment_id()}_reply_write">작성하기</button>
										</div>
			                            <div class="comments" id="${dto.getComment_id()}_reply_id"></div>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>
		             
		             
						<div class="comments" id="reply_id">
							<div class="comment">
			    				<a class="avatar">
			        				<img src="images/avatar/small/jenny.jpg">
			    				</a>
			    				<div class="content">
			        				<a class="author">Jenny Hess</a>
			        				<div class="metadata">
			            				<span class="date">방금 전</span>
			        				</div>
			        				<div class="text">
			            				친구 넌 항상 옳아 :)
			        				</div>
			        				<div class="actions">
			            				<a class="reply">댓글쓰기</a>
			        				</div>
			    				</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
   		</div>
    </div>

    <jsp:include page="footer.jsp" />