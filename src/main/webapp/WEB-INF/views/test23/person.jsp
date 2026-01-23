<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Person (사용자 목록)</h1>
    <%-- 클릭하면 regist에 get요청하세요   --%>
    <a href="/regist">회원가입창으로</a> <br>
    <%
        var persons = (HashMap)request.getAttribute("persons");
        for(var person : persons.values()){
    %>
        <span><%=person%></span><br>
    <%
        }
    %>

</body>
</html>
