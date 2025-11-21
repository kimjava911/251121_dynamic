<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시물 작성</title>
</head>
<body>
    <nav>
        <a href="<%=request.getContextPath()%>/">메인</a>
    </nav>
    <%
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
    %>
    <div>
        <p><%= msg %></p>
    </div>
    <% } %>

    <form action="<%=request.getContextPath()%>/board" method="post">
        <input name="title" placeholder="제목 입력"><br>
        <input name="content" placeholder="내용 입력"><br>
        <input name="writer" placeholder="작성자 입력"><br>
        <input type="submit" value="작성">
    </form>
</body>
</html>