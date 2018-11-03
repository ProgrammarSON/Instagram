<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.newsfeed.*" %>    
<% 
	feedDTO dto = (feedDTO)request.getAttribute("dto");
	String feed_id = (String)request.getAttribute("feed_id");
%>
<% request.setCharacterEncoding("UTF-8");
	//html에서부터 값 받아옴
	/* String address = request.getParameter("add");
	String latitude = request.getParameter("lat");
	String longitude = request.getParameter("lng"); */
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
	<title>포스팅 작성</title>

	<!-- AIzaSyASWjrAjmngCtIBhXu12ALY5G08SCFOBoM <-이건 구글 API사용 할때 필요한 키에요-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyASWjrAjmngCtIBhXu12ALY5G08SCFOBoM&callback=initMap"></script>	
	
	<script>
		
	/* 구글 맵 시작 */
	var map;
	var infowindow = new google.maps.InfoWindow();
	var infowin = new google.maps.InfoWindow();
	var marker = [];
	var geocoder;
	var geocodemarker = [];
	/*var GreenIcon = new google.maps.MarkerImage(

	"http://labs.google.com/ridefinder/images/mm_20_green.png",

	new google.maps.Size(12, 20), 
	new google.maps.Point(0, 0),
	new google.maps.Point(6, 20));*/

	// 녹색 마커 아이콘을 정의하는 부분

	function initialize() {
		var latlng = new google.maps.LatLng(37.5240220, 126.9265940);
		var myOptions = {
			zoom : 12,
			center : latlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		map = new google.maps.Map(document.getElementById("map_canvas"),
				myOptions);
		geocoder = new google.maps.Geocoder();
		google.maps.event.addListener(map, 'click', codeCoordinate);

		/* 아랫 글에서 설명한 event를 이용 지도를 'click'하면 codeCoordinate 함수를 실행합니다.
		codeCoordinate 함수는 클릭한 지점의 좌표를 가지고 주소를 찾는 함수입니다. */
	}

	function Setmarker(latLng) {
		if (marker.length > 0) {
			marker[0].setMap(null);
		}
		
		// marker.length는 marker라는 배열의 원소의 개수입니다.
		// 만약 이 개수가 0이 아니라면 marker를 map에 표시되지 않게 합니다.
		// 이는 다른 지점을 클릭할 때 기존의 마커를 제거하기 위함입니다.
		
		marker = [];
		marker.length = 0;
		
		// marker를 빈 배열로 만들고, marker 배열의 개수를 0개로 만들어 marker 배열을 초기화합니다.

		marker.push(new google.maps.Marker({
			position : latLng,
			map : map,
			animation: google.maps.Animation.DROP
		}));
		
		// marker 배열에 새 marker object를 push 함수로 추가합니다.
	}

	/*클릭한 지점에 마커를 표시하는 함수입니다.
	 그런데 이 함수를 잘 봐야 하는 것이 바로 marker를 생성하지 않고 marker라는 배열 안에 Marker 
	 obejct 원소를 계속 추가하고 있습니다. 이는 매번 클릭할 때마다 새로운 마커를 생성하기 위함입니다. */

	//입력 받은 주소를 지오코딩 요청하고 결과를 마커로 지도에 표시합니다.
	function codeAddress(event) {
		//deleteMarkers2();
		if (geocodemarker.length > 0) {
			for (var i = 0; i < geocodemarker.length; i++) {
				geocodemarker[i].setMap(null);
			}
			geocodemarker = [];
			geocodemarker.length = 0;
		}

		if(marker.length > 0) {
			for(var i =0; i<marker.length; i++){
				marker[i].setMap(null);
			}
			marker = [];
			marker.length = 0;
		}
	
		//이 부분도 위와 같이 주소를 입력할 때 표시되는 marker가 매번 새로 나타나게 하기 위함입니다.

		var address = document.getElementById("addr1").value;
		//아래의 주소 입력창에서 받은 정보를 address 변수에 저장합니다.
		//지오코딩하는 부분입니다.

		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) { //Geocoding이 성공적이라면,
				//alert(results.length + "개의 결과를 찾았습니다.");
				//결과의 개수를 표시하는 창을 띄웁니다. alert 함수는 알림창 함수입니다.

				for (var i = 0; i < results.length; i++) {
					map.setCenter(results[i].geometry.location);
					geocodemarker.push(new google.maps.Marker({
						center : results[i].geometry.location,
						position : results[i].geometry.location,
						//icon : GreenIcon,
						map : map
					}));
					
					infowin.setContent(address);
					infowin.open(map, geocodemarker[0]);
					$('#add1').val(address);
				}
				//결과가 여러 개일 수 있기 때문에 모든 결과를 지도에 marker에 표시합니다.
			} else {
				alert("올바르지 않습니다. "); //+ status); 오류 처리 
			}
		}); 
	}

	//클릭 이벤트 발생 시 그 좌표를 주소로 변환하는 함수입니다.
	function codeCoordinate(event) {
		Setmarker(event.latLng);
		//이벤트 발생 시 그 좌표에 마커를 생성합니다.
		// 좌표를 받아 reverse geocoding(좌표를 주소로 바꾸기)를 실행합니다.
		//deleteMarkers();
		geocoder.geocode({
			'latLng' : event.latLng
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				//$('#address').html(results[0].formatted_address);
				$('#add1').val(results[0].formatted_address);
				//$('#lat').html(results[0].geometry.location.lat());
				$('#lat1').val(results[0].geometry.location.lat());
				//$('#lng').html(results[0].geometry.location.lng());
				$('#lng1').val(results[0].geometry.location.lng());
				//address는 주소값 lat lng은 위도 경도 값
				if (results[1]) {
					infowindow.setContent(results[0].formatted_address);
					infowindow.open(map, marker[0]);
					//infowindow로 주소를 표시합니다.
				}
			}
		});
	}
	
	function toggleBounce() {
        if (marker.getAnimation() !== null) {
			marker.setAnimation(null);
        } else {
			marker.setAnimation(google.maps.Animation.BOUNCE);
        }
	}
	
	function setMapOnAll(map) {
		for(var i = 0; i<geocodemarker.length; i++) {
			geocodemarker[i].setMap(map);
		}
	}
	
	function clearMarkers() {
		setMapOnAll(null);
	}
	
	function deleteMarkers() {
		clearMarkers();
		geocodemaker = [];
	}
	/* 구글 맵 끝 */
	
	</script>
	
	<script>
	/* 구글 맵 모달창 띄우기 */
	$(function() {
		$("#button_mapAPI").click(function(e) {
			e.preventDefault();
			$(".ui.modal").modal({
				closable: false,
				selector: {
					keyboardShortcuts : false,
					approve : '.ok',
					deny : '.cancel'
				},
			}).modal("show").modal("refresh");
		});
		
	});
	
	/* 구글 맵 좌표 입력하기 */
	function formchk() {
		if($('#add').val().length <1 ) $('#add').text("null");
		if($('#lat').val().length <1 ) $('#lat').text("null");
		if($('#lng').val().length <1 ) $('#lng').text("null");
		document.feedform.submit();
	};
	
	function setMaps(){
		$('#add').val($('#add1').val());
		$('#lat').val($('#lat1').val());
		$('#lng').val($('#lng1').val());
		document.getElementById('address_input').innerHTML = $('#add1').val();
	};
	
	$(function() {
		$("#btn_search").on("click", function() {
			codeAddress();
			return false;
		});
		
		$("#addr1").keypress(function(e) {
			if(e.keyCode === 13) {
				$("#btn_search").click();
			}
		});
		
	});
	
	</script>
	
</head>
 	<body onload="initialize()">  <!-- initialize(): Google Map API 구동 시 필요  -->
	
	<!-- 내비게이션(메뉴) 바 시작 -->
    <jsp:include page="navbar.jsp"/>
    <!-- 내비게이션(메뉴) 바 끝 -->
    
    <!-- 페이지 전체 컨테이너 시작 -->
	<div class="ui container">
		
		<form action="modifynewsfeed.do" name="feedform" method="post" class="ui form" onSubmit="formchk()">
			<div class="ui container">
	            <h1>작성하기</h1>
	            
	           	<div>
					<a class="ui small basic button violet" id="button_mapAPI">위치 추가</a>
	           		<div id="div-address">
<!-- 	           			주소 : <span id="address_input"></span> -->
	           		<span id="address_input"><%=dto.getAddress() %></span>
	           		        	           			
	           		</div>
	            </div>
	            
            	<div class="ui divider"></div>
            	
				<div class="bordered image centered-and-cropped" id="dimmer_pic">
					<div class="ui dimmer">
						<input type="hidden" id="add" name="address" value="<%=dto.getAddress() %>">
						<input type="hidden" id="lat" name="latitude" value="<%=dto.getLatitude() %>">
						<input type="hidden" id="lng" name="longitude" value="<%=dto.getLongitude() %>"> 
						<input type="hidden" name="feed_id" value="<%=feed_id %>">				
					</div>
					<img class="ui centered image dim_pic" src="feed_image/<%=dto.getImage_path() %>">
				</div>
	        </div>
	        
	        <div class="ui container div-text-input">
				<textarea name="contents" rows="4" value="<%=dto.getContents() %>"><%=dto.getContents() %></textarea>
			</div>
			<div class="column">
	            <div class="ui right aligned container">
	                <button type="reset" class="ui button">취소하기</button>
	                <button type="submit" class="ui button violet">수정하기</button>
	            </div>
	        </div>
		</form>
	</div>
	<!-- 페이지 전체 컨테이너 끝 -->
	
	<!-- mapAPI 모달 시작 -->
	<div class="ui modal">
		<div class="content">
			<div id="map_canvas"></div>
		
		<form action="" method="post" class="ui form">
			<div>
				<input type="text" id="addr1" name="address" autofocus placeholder="주소 검색" style="width: 500px;"> 
				<button type="button" id="btn_search" class="ui button basic violet">찾기</button>
				
			</div>
			<div>
				<input type="text" id="add1" name="add" placeholder="결과" style=" width: 500px;">
				<input type="hidden" id="lat1" name="lat" value="<%=dto.getLatitude() %>">
				<input type="hidden" id="lng1" name="lng" value="<%=dto.getLongitude() %>">
				<button type="button" onclick='setMaps()' class="ui button violet ok" id="map_submit">위치 추가</button> 
				<button type="reset" class="ui button cancel">취소</button>
			</div>
		</form>
		
		<!--입력받는 부분을 통해 주소를 입력받고, 버튼을 누르면 codeAddress() 함수를 실행하는 부분입니다.
	
		// <input type="text"는 텍스트 입력을 <input type="submit"은 버튼 입력을 의미합니다. 
	 	//<p>와 </p> 사이에 있는 부분이 한 문단을 구성합니다. -->
		</div>
	</div>
	<!-- mapAPI 모달 끝 -->
	
	<jsp:include page="footer.jsp"/>