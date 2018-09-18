<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int check = (int)request.getAttribute("check"); 
	String user_id = (String)session.getAttribute("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	var check = <%=check%>;
	
	if(check > 0)
	{
		alert("수정 완료");
		document.location.href="viewmyfeed.do?user_id=<%=user_id%>";
		
	}else{
		alert("수정 에러")
		window.history.goback();
	}
	
		
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>