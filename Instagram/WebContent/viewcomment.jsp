<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String feed_id = (String)request.getAttribute("feed_id");
	String user_id = (String)session.getAttribute("id");

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
                    " <img src='images/avatar/small/jenny.jpg'> "+
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
   
</head>

<body>
	
    <div class="ui container">
        <jsp:include page="navbar.jsp"/>

        <div class="ui segment">
            <h1>제목란입니다.</h1>
            <div class="ui divider"></div>
            <div class="container">
                <img class="ui image fluid" src="images/crowd.jpg" alt="">
                <p>글 내용란입니다.</p>
            </div>
        </div>


        <div class="ui segment">
            <div class="container">
                <form class="ui reply form" action = "writecomment.do">
                     <input type="hidden" name="comment_feed_id" value=<%=feed_id%>>
                	<input type="hidden" name="comment_user_id" value="sjw">
                    
                    <div class="field">
                        <textarea placeholder="댓글을 작성해보세요." rows="3" name="comment_content"></textarea>
                    </div>
                    <div class="ui right aligned container">
                        <button class="ui button violet" type="submit">작성하기</button>
                    </div>
                </form>
            </div>


            <div class="ui threaded comments">
                <h3 class="ui dividing header">#개의 댓글</h3>
      <!--           <div class="comment">
                    <a class="avatar">
                        <img src="/images/avatar/small/matt.jpg">
                      </a>
                    <div class="content">
                        <a class="author">Matt</a>
                        <div class="metadata">
                            <span class="date">오늘 오후 5:42</span>
                        </div>
                        <div class="text">
                            쩐다!
                        </div>
                        <div class="actions">
                            <a class="reply">댓글쓰기</a>
                        </div>
                    </div>
                </div> -->
               <c:forEach items="${list}" var="dto">
                <div class="comment">
                    <a class="avatar">
                        <img src="images/avatar/small/elliot.jpg">
                      </a>
                    <div class="content">
                        <a class="author">${dto.getUser_id()}</a>	<!-- 사용자 이름 -->
                        <div class="metadata">
                            <span class="date">${dto.getComment_date()}</span>
                        </div>
                        <div class="text">
                            <p>${dto.getContent()}</p>
                        </div>
                        <div class="actions">
                            <a class="reply" id="${dto.getComment_id()}_reply_show">댓글보이기</a>
                        	<a class="reply" id="${dto.getComment_id()}_reply_hide">댓글숨기기</a>
                        </div>
                           <div class="field" id="${dto.getComment_id()}">
                           	<textarea placeholder="댓글을 작성해보세요." rows="2" id="${dto.getComment_id()}_reply_contents"></textarea>
                    		<button class="ui button violet" type="submit" id="${dto.getComment_id()}_reply_write">작성하기</button>
                   	 	  	  
                   	 	  	  <div class="comments" id="${dto.getComment_id()}_reply_id"> </div>
                         		   
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

	<jsp:include page="footer.jsp"/>