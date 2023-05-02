<%@ page import="java.util.List" %>
<%@ page import="am.arnara.mylibrary.model.Book" %>
<%@ page import="am.arnara.mylibrary.model.User" %><%--
  Created by IntelliJ IDEA.
  User: radik
  Date: 28.04.23
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Book> foundBooks = (List<Book>) request.getAttribute("foundBooks");
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <link rel="stylesheet" href="../../styles/homepageStyles.css">
    <title>Books</title>
</head>
<body>

<form method="get" action="/">
    <button type="submit"> Home page</button>
</form>

<span><h2>Input book's search parameters.</h2></span>

<form method="get">
    <input name="searchParam" placeholder="Search book">
    <button type="submit">Search</button>
</form>
<table>
    <tr>
        <th>FaceBookImg</th>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
        <th>Author</th>
        <th>Action</th>
    </tr>

    <%
        for (Book book : foundBooks) {%>
    <tr>
        <td> <img src="/img?picName=<%=book.getBookImg()%>" width="150px"/></td>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td><%=book.getAuthorId().getName()%>
        </td>
        <%if (book.getUserId() != null && user.getId() == book.getUserId().getId() || user.getUserType().name().equals("ADMIN")) {%>
            <td>
                <form method="post">
                    <button formmethod="get" formaction="/editBook?id="<%=book.getId()%>">Edit</button>
                    <button formaction="/deleteBook?id=<%=book.getId()%>">Delete</button>
                </form>
            </td>
        <%}%>
    </tr>
    <%}%>

</table>

</body>
</html>
