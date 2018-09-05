<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String id = (String)session.getAttribute("id"); %>
	<script>
	$(document).ready(function() {
		$.ajax({
			url : "searchid.do",
			success : function(result){
				var datas = JSON.parse(result);
				var datajson = JSON.stringify(datas);
				console.log(datajson);
				var resulttag;
				console.log(datas);
				resulttag += "<table border='1'>";
				for(i=0; i<datas.length; i++){
					resulttag += "<tr><td><a href=viewmyfeed.do?user_id="+datas[i].user_id+">"
					+datas[i].user_id
					+"</a></td></tr>";
				}				
				resulttag += "</table>";
				$("#viewsearch").html(resulttag);
			}
		});
				
		$("#searchid").on("keyup",function(){
			var value = $(this).val().toLowerCase();
			$("table tr").filter(function(){
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
			});
		});		
	});	
	</script>
	<a class="" href="viewnewsfeed.do">
		<img src="images/yestagram.png" class="ui image centered small" id="brand" alt="Yestagram">
    </a>

    <div class="ui large secondary pointing stackable menu violet">
<!--         <a class="item" href="./writenewsfeed.jsp"><i class="image icon"></i>새 포스트</a> -->
        <a class="item" href="./writenewsfeed.jsp">
        	<button class="ui labeled icon button violet"><i class="plus square outline icon"></i>새 포스트</button>
        </a>
        <a class="item" id="" href="viewmyfeed.do?user_id=<%=id%>"><i class="images outline icon"></i>내 피드</a>
        <a class="item" id="liked"><i class="heart icon"></i>좋아한 글</a>
        <a class="item"><i class="hashtag icon"></i>해시태그</a>
        <div class="right menu">
            <div class="item">
                <div class="ui mini icon input">
                    <input placeholder="검색" type="text" id="searchid" />
                    <i class="search link icon"></i>
                </div>
                
                <div id="viewsearch" class="results"></div>
                
            </div>
            <a class="ui item" href="./member/logout.jsp"><i class="user icon"></i>로그아웃</a>
        </div>
    </div>

    <div class="ui custom popup bottom center transition hidden"></div>