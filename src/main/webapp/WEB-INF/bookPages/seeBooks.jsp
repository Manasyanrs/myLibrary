<%@ page import="java.util.List" %>
<%@ page import="am.arnara.mylibrary.models.Book" %><%--
  Created by IntelliJ IDEA.
  User: radik
  Date: 28.04.23
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Book> books = (List<Book>) session.getAttribute("books");
    List<Book> foundBooks = (List<Book>) session.getAttribute("foundBooks");
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

<% if (foundBooks != null) {%>
<h2>Search results</h2>
<table>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
        <th>Author</th>
        <th>Action</th>
    </tr>

    <%
        for (Book book : foundBooks) {%>
    <tr>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td><%=book.getAuthor_id().getName()%>
        </td>
        <td>
            <form method="post">
                <button formmethod="get" formaction="/editBook" name="id" value="<%=book.getId()%>">Edit</button>
                <button formaction="/deleteBook?id=<%=book.getId()%>">Delete</button>
            </form>
        </td>
    </tr>
    <%}%>

</table>

<%} else {%>
<h3> Search result = 0</h3>

<% if (books != null && !books.isEmpty()) {%>
<h1>We have <%=books.size()%> books in our database.</h1>
<table>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
        <th>Author</th>
        <th>Action</th>
    </tr>

    <%
        for (Book book : books) {%>
    <tr>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td><%=book.getAuthor_id().getName()%>
        </td>
        <td>
            <form method="post">
                <button formmethod="get" formaction="/editBook" name="id" value="<%=book.getId()%>">Edit</button>
                <button formaction="/deleteBook?id=<%=book.getId()%>">Delete</button>
            </form>
        </td>
    </tr>
    <%}%>

</table>
<%} else {%>
<h2>Sorry we don't have any books right now</h2>
<%}%>
<%}%>

</body>
</html>
