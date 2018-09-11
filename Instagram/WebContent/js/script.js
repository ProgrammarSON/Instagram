$(document).ready(function() {  // $(function() 과 같다.
	//var temp=[];
	
	// navbar 고정
	$('.main.menu').visibility({
        type: 'fixed'
    });
	$('.overlay').visibility({
        type: 'fixed',
        offset: 30
	});
	
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
    
    
    
    /*$('#show_modal_following').click(function(){
        var id = document.getElementById('header_userid').innerText;
       
        //$(this).find('i').hasClass('outline')
        $.ajax({
    		url : "viewmodalfollow.do?user_id="+id+"&state=following",
    		success : function(result){
    			var modalData = JSON.parse(result);
    			$('.ui.very.relaxed.list').html("");
    			for(var i=0; i<modalData.length; i++){
    				console.log(modalData[i].user_id);
    				$('.ui.very.relaxed.list').append(
    					"<div class='item'>"+
    					 "<div class='left floated content'>"+
    					 	  	"<img class='ui avatar image' src='profile_image/"+ modalData[i].profile_img+"'>"+
    		             "</div>"+
    		              "<div class='right floated content'>"+
    		                	//"<button type='button'class='ui tiny button violet btn_follow' id="+modalData[i].user_id+"_followingjung_btn>팔로잉</button>"+
    		              "<button type='button'class='ui tiny button violet basic btn_follow'  id="+modalData[i].user_id+"_followingjung_btn onclick=\"followingjung('"+id+"','"+modalData[i].user_id+"');\">팔로잉</button>"+
    		              "<button type='button'class='ui tiny button violet btn_follow'  id="+modalData[i].user_id+"_following_btn onclick=\"following('"+id+"','"+modalData[i].user_id+"');\">팔로우</button>"+
    		              "</div>"+
    					   "<div class='content'>"+
    							"<a class='header'>"+modalData[i].user_id+"</a>"+
    						"<div class='description'>저는 이런 사람입니다.</div>"+
    						"</div>"+
    					"</div>"
    				);
    				//setModalData(modalData[i].user_id);
    				$('#'+modalData[i].user_id+'_following_btn').hide();
    			}
    			
    		},
    		error : function(xhr, status, error) {
    			alert("ERROR!!!");
    		}
      	})        
                
        $('.ui.modal')
        .modal({
            closable: false,
            transition: 'fade'
        })
        .modal('show');
    	
    });*/
    
    
   /* $('#show_modal_follower').click(function(){
        var id = document.getElementById('header_userid').innerText;
       
        //$(this).find('i').hasClass('outline')
        $.ajax({
    		url : "viewmodalfollow.do?user_id="+id+"&state=follower",
    		success : function(result){
    			var modalData = JSON.parse(result);
    			$('.ui.very.relaxed.list').html("");
    			for(var i=0; i<modalData.length; i++){
    				console.log(modalData[i].user_id);
    				$('.ui.very.relaxed.list').append(
    					"<div class='item'>"+
    					 "<div class='left floated content'>"+
    					 	  	"<img class='ui avatar image' src='profile_image/"+ modalData[i].profile_img+"'>"+
    		             "</div>"+
    		              "<div class='right floated content'>"+
    		                	//"<button type='button'class='ui tiny button violet btn_follow' id="+modalData[i].user_id+"_followingjung_btn>팔로잉</button>"+
    		              "<button type='button'class='ui tiny button violet btn_follow'  id="+modalData[i].user_id+"_followingjung_btn onclick=\"followingjung('"+id+"','"+modalData[i].user_id+"');\">팔로잉</button>"+
    		              "<button type='button'class='ui tiny button violet btn_follow'  id="+modalData[i].user_id+"_following_btn onclick=\"following('"+id+"','"+modalData[i].user_id+"');\">팔로우</button>"+
    		              "</div>"+
    					   "<div class='content'>"+
    							"<a class='header'>"+modalData[i].user_id+"</a>"+
    						"<div class='description'>저는 이런 사람입니다.</div>"+
    						"</div>"+
    					"</div>"
    				);
    				//setModalData(modalData[i].user_id);
    				$('#'+modalData[i].user_id+'_followingjung_btn').hide();
    			}
    			
    		},
    		error : function(xhr, status, error) {
    			alert("ERROR!!!");
    		}
      	})        
                
        $('.ui.modal')
        .modal({
            closable: false,
            transition: 'fade'
        })
        .modal('show');
    	
    });*/
    
    
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
    );
   
    
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

function followingjung(id,follow){
	$.ajax({
    	url: "following.do?user_id="+id+"&follow_id="+follow+"&check=unfollow",
    	success : function(result){
        	var check = JSON.parse(result);
			console.log(check);                
    	}
	})
	$('#'+follow+'_followingjung_btn').hide();
	$('#'+follow+'_following_btn').show();
	
}

function following(id,follow){
	$.ajax({
    	url: "following.do?user_id="+id+"&follow_id="+follow+"&check=follow",
    	success : function(result){
        	var check = JSON.parse(result);
			console.log(check);                
    	}
	})
	
	
	$('#'+follow+'_followingjung_btn').show();
	$('#'+follow+'_following_btn').hide();
	
}


