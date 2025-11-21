<%@ page import="kr.java.dynamic.model.domain.Board" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판</title>
</head>
<body>
    <nav>
        <a href="<%=request.getContextPath()%>/board">작성하기</a>
    </nav>

    <h3>step1 : if</h3>
    <form method="get" action="<%=request.getContextPath()%>/step1">
        <input name="title" placeholder="검색할 제목">
        <input name="content" placeholder="검색할 내용">
        <input name="writer" placeholder="검색할 작성자">
        <input name="minViewCount" placeholder="최소 조회수">
        <button>검색</button>
    </form>

    <%
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
    %>
    <div>
        <p><%= msg %></p>
    </div>
    <% } %>

    <div>
    <%
        List<Board> boards = (List<Board>) request.getAttribute("boards");
        if (boards.isEmpty()) {
    %>
        <p>게시물이 없습니다!</p>
    <% } else {
        for (Board b : boards) {
    %>
        <p> <%= b.getId() %> </p>
        <p> <%= b.getTitle() %> </p>
        <p> <%= b.getContent() %> </p>
        <p> <%= b.getWriter() %> </p>
        <p> <%= b.getViewCount() %> </p>
        <p> <%= b.getCreatedAt() %> </p>
        <p> <%= b.getUpdatedAt() %> </p>
    <% } } %>
    </div>
</body>
</html>