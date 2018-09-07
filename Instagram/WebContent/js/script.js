$(document).ready(function() {
	// navbar 버튼
    $('.ui .item').on('click', function() {
        $('.ui .item').removeClass('active');
        $(this).addClass('active');
    });
    
    // 마우스 hover 시 이미지 dimmer
    $('#dimmer_pic.image, .dim-card .image').dimmer({
        on: 'hover'
    });
    
	// 팔로우한 유저 모달창 띄우기 
    $('#show_modal_follow').click(function(){
        $('.ui.modal')
        .modal({
            closable: false,
            transition: 'fade'
        })
        .modal('show');
    });
    
    
    // navbar 검색 기능
    $.ajax(
    	{
    		url : "searchid.do",
    		type: "POST",
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
    	}
    )
    ;
//    	,
//    $.ajax(
//    // 좋아요 feed
//		{
//	        url: "likesfeed.html",
//	        type: "GET",
//	        dataType: "html",
//	        success: function(data) {
//	            $("#liked").popup({
//	                popup: $(".custom.popup").html(data),
//	                on: 'click'
//	            });
//	        },
//	        error: function(err, val, msg) {
//	            alert(err.responseText);
//	        }
//	    }
//    );
	
    $("#liked").click(function(){
    	$.ajax({
    		url: "likesfeed.html",
	        type: "GET",
	        dataType: "html",
	        success: function(data) {
	        	console.log("sibal");
	            $("#liked").popup({
	            	popup: $(".custom.popup").html(data),
	            	on: 'click'
	            })        
	        },
	    	error: function(err, val, msg) {
	            alert(err.responseText);
	    	}
    	})
    });
   
    
	$("#searchid").on("keyup", function(){
		var value = $(this).val().toLowerCase();
		$("table tr").filter(function(){
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
		});
	});
});

function readURL(input){ 
    if (input.files && input.files[0]) { 
        var reader = new FileReader(); 
        reader.onload = function (e) { 
            $('.dim_pic').attr('src', e.target.result); 
        } 
        reader.readAsDataURL(input.files[0]);
    } 
}

//function ajaxSearch() {
//	var deferred = $.Deferred();
//	
//	XMLHttpRequest xhr = new XMLHttpRequest();
//	xhr.open("POST", "result", true);
//	
//	xhr.addEventListener('load', function(){
//		if(xhr.status == 200) {
//			deferred.resolve(xhr.response);
//		} else {
//			deferred.reject("HTTP error: " + xhr.status);
//		}
//	}, false)
//	
//	xhr.send();
//	return deferred.promise();
//};
//
//function ajaxLikeFeed() {
//	
//}