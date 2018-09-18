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

	<!-- AIzaSyASWjrAjmngCtIBhXu12ALY5G08SCFOBoM <-이건 구글 API사용 할때 필요한 키에요-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyASWjrAjmngCtIBhXu12ALY5G08SCFOBoM&callback=initMap" async defer></script>

	<script>
	/* 구글 맵 시작 */
	
	/*
	* 참고 URI :
	* https://developers.google.com/maps/documentation/javascript/examples/geocoding-simple?hl=ko
	*/

	var address;
	
	function initMap(e) {	
	  	var map = new google.maps.Map(document.getElementById('map_canvas'), {
	    	zoom: 14,
	    	center: {lat: 40.731, lng: -73.997}
	  	});
	  	var geocoder = new google.maps.Geocoder();
	
	  	address = e[0].innerText;
	  	console.log(address);

  		geocodeAddress(geocoder, map);
	};
	
	function geocodeAddress(geocoder, resultsMap) {
      	geocoder.geocode({'address': address}, function(results, status) {
        	if (status === 'OK') {
          		resultsMap.setCenter(results[0].geometry.location);
          		var marker = new google.maps.Marker({
            		map: resultsMap,
            		position: results[0].geometry.location
          		});
        	} else {
          		alert('Geocode was not successful for the following reason: ' + status);
        	}
      	});
    };
	
	$(function() {
		$(".show_map").on("click", function() {
			console.log($(this)[0].innerText);
			
			initMap($(this));
			$('.ui.modal').modal('show').modal('refresh');
    	});
	});
	/* 구글 맵 끝 */
	
	/* 좋아요 기능 */
	<%for(String key : map.keySet()) { %>
	$(function(){
		var key="<%=key%>";
		
		$("#"+key+"like").click(function(){
			//$(this).find('i').toggleClass('outline')
			if($(this).find('i').hasClass('outline')) {
				$(this).find('i').removeClass('outline').css('color','#ff2733');
				$.ajax({
					url: "like.do?feed_id="+key+"&check=like",
					success : function(result){
						var check = JSON.parse(result);
					}
				});
			} else {
				$(this).find('i').addClass('outline').css('color','rgba(0,0,0,.4)');
				$.ajax({
					url: "like.do?feed_id="+key+"&check=unlike",
					success : function(result){
						var check = JSON.parse(result);
					}
				});
			};
		});
	});
	<%} %>
	
	function readURL(input){ 
		if (input.files && input.files[0]) { 
			var reader = new FileReader(); 
			reader.onload = function (e) { 
				$('#blah').attr('src', e.target.result); 
			} 
			reader.readAsDataURL(input.files[0]); 
		}
	};
	
	/* 새로고침 막기 */
	/* function doNotReload() {
		if(	(event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) // Ctrl+N , Ctrl+R 
				|| (event.keyCode == 116)) { // function F5
					event.keyCode = 0;
					event.cancelBubble = true;
					event.returnValue = false;
		}
	}
	document.onkeydown = doNotReload; */

	</script>

</head>

<body>
	<!-- 내비게이션(메뉴) 바 시작 -->
    <jsp:include page="navbar.jsp"/>
    <!-- 내비게이션(메뉴) 바 끝 -->
    
    <!-- 페이지 전체 컨테이너 시작 -->
    <div class="ui container">
    
	    <!-- 카드 구역 시작 -->    
		<div class="ui three stackable cards">
		    <% for(String key : map.keySet()) { %>			<!-- key는 게시물 번호 -->
		    
		    <!-- 카드 시작 -->
		    <div class="card">
		
				<div class="content">
					<div class="right floated meta">
						<%= map.get(key).getDate() %>
					</div>
				
				<% if(map.get(key).getProfile_img() == null) { %>
		            <img class="ui avatar image" src="profile_image/null.jpg">
				<% } else { %>
		            <img class="ui avatar image" src="profile_image/<%= map.get(key).getProfile_img() %>">
				<% } %>  
					<a href="viewmyfeed.do?user_id=<%= map.get(key).getUser_id() %>"><%= map.get(key).getUser_id() %></a>
					<div id="addressInfo">
					<% if(map.get(key).getAddress() != null && !map.get(key).getAddress().equals("null")) { %>
	       				<span><a id="<%=key%>_a_show_map" class="show_map"><%= map.get(key).getAddress() %></a></span>
	   				<% } %>
	       			</div>
				</div>
				
				<a class="centered-and-cropped" href="viewcomment.do?feed_id=<%=key%>">
					<div class="cropped-image">
					<% if(map.get(key).getImage_path() == null) { %>
						<img class="image centered-and-cropped" src="./feed_image/null.jpg">
					<% } else { %>
						<img class="image centered-and-cropped" src="./feed_image/<%=map.get(key).getImage_path()%>">
					<% } %>
					</div>
				</a>
				<div class="extra content">
					<span class="right floated" id="<%=key%>like">
					<%if(map.get(key).getLike_state().equals("unlike")){ %>
						<i class="heart like icon outline"></i> <%=map.get(key).getLike_count() %>
					<%}else{ %>
						<i class="heart like icon" style="color: #ff2733"></i> <%=map.get(key).getLike_count() %>
					<%} %>
					</span>
					<span>
						<i class="comment icon"></i> <%=map.get(key).getComment_count() %>
					</span>
				</div>
			</div>
			<!-- 카드 끝 -->
			<%} %>
	    </div>
	    <!-- 카드 구역 끝 -->
    
	</div>
	<!-- 페이지 전체 컨테이너 끝 -->

	<!-- 모달 시작 -->
	<div class="ui modal">
		<div class="content">
			<div id="map_canvas"></div>
		</div>
	</div>
	<!-- 모달 끝 -->


	<!-- 푸터 시작 -->
	<jsp:include page="footer.jsp"/>
	<!-- 푸터 끝 -->