<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Selenium Sample</title>
</head>
<body>
<%
int num1=Integer.parseInt(request.getParameter("num1"));
int num2=Integer.parseInt(request.getParameter("num2"));
int answer = num1 + num2;
%>
答えは <span id="answer"><%= answer%></span> です。
<a id="back" href="#" onClick="history.back(); return false;">もう一度計算する。</a>
</body>
</html>