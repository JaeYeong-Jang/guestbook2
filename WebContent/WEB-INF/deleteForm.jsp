<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
int gid = Integer.parseInt(request.getParameter("id"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/guestbook2/gbc" method="get">
비밀번호<input type="text" name="pw" value="">
<input type="text" name="id" value="<%=gid %>">
<input type="hidden" name="action" value="delete">
<button type="submit">[확인]</button> <br>
</form>
<a href="/guestbook2/gbc?action=list">메인으로 돌아가기</a>

</body>
</html>