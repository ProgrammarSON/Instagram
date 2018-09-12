<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.member.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	int state = (int)request.getAttribute("state");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	if(state >0) {
%>
		<script>
			alert("회원가입 완료");
			document.location.href="index.html";
		</script>
<%
		} else {	
%>
		<script>
			alert("회원가입에 실패했습니다.");
			history.goback();
		</script>
<%
		}
%>
<body>

</body>
</html>