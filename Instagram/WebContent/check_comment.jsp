<% String feed_id = (String) request.getAttribute("feed_id"); %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
		alert("등록 완료");
		document.location.href="viewcomment.do?feed_id=<%=feed_id%>";
	</script>
</head>
<body>

</body>
</html>