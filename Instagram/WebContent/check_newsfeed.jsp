<% String user_id = (String)request.getAttribute("user_id"); %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
		alert("등록 완료");
		document.location.href="viewnewsfeed.do?user_id=<%=user_id%>";
	</script>
</head>
<body>

</body>
</html>