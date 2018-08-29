<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.member.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	int state = (int)request.getAttribute("state");
	String id = (String)request.getAttribute("id");
	String user_id = (String)request.getAttribute("user_id");
	System.out.println(user_id);
%>

<%
	if(state == 0) {
%>
		<script language="javascript">
			alert("비밀번호가 틀립니다.");
			document.location.href="login.html";
		</script>
<%
		} else if(state == -1){
		
%>
			<script language="javascript">
				alert("아이디가 틀립니다.");
				document.location.href="login.html";
			</script>
<%
		} else {
%>
		
<%
		session.setAttribute("id",user_id);
		session.setAttribute("ValidMem", "yes");
		//response.sendRedirect("main.jsp");
%>		
		<script language="javascript">
			document.location.href="viewnewsfeed.do?user_id=<%=user_id%>";
		</script>	
	
<% }%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>