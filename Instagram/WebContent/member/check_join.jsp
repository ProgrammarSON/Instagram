<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.member.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<%
	int state = (int)request.getAttribute("state");
%>

<%
	if(state >0) {
%>
		<script language="javascript">
			alert("회원가입 완료");
			document.location.href="login.html";
		</script>
<%
		} else {	
%>
		<script language="javascript">
			alert("회원가입에 실패했습니다.");
			history.goback();
		</script>
<%
		}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>