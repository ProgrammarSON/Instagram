<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.member.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	int state = (int)request.getAttribute("state");
	String user_id = (String)request.getAttribute("user_id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	if(state == 0) {
%>
		<script>
			alert("비밀번호가 틀립니다.");
			document.location.href="index.html";
		</script>
<%
	} else if(state == -1){
%>
		<script>
			alert("아이디가 틀립니다.");
			document.location.href="index.html";
		</script>
<%
	} else {
		session.setAttribute("id",user_id);
		session.setAttribute("ValidMem", "yes");
		//response.sendRedirect("main.jsp");
%>		
		<script>
			document.location.href="viewnewsfeed.do";
		</script>
<% } %>
<body>
	
</body>
</html>