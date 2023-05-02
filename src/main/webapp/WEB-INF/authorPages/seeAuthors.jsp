<%@ page import="java.util.List" %>
<%@ page import="am.arnara.mylibrary.model.Author" %>
<%@ page import="am.arnara.mylibrary.model.User" %><%--
  Created by IntelliJ IDEA.
  User: radik
  Date: 28.04.23
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Author> authors = (List<Author>) request.getAttribute("authors");
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <link rel="stylesheet" href="../../styles/homepageStyles.css">
    <title>Authors</title>
</head>
<body>
<form method="get" action="/">
    <button type="submit"> Home page</button>
</form>

<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>age</th>
        <%if (!user.getUserType().name().equals("USER")) {%>
            <th>Action</th>
        <%}%>
    </tr>

    <%if (authors != null && !authors.isEmpty()) {%>
    <h1>We have <%=authors.size()%> authors in our database.</h1>

    <%
        for (Author author : authors) {%>
    <tr>
        <td><%=author.getName()%>
        </td>
        <td><%=author.getSurname()%>
        </td>
        <td><%=author.getEmail()%>
        </td>
        <td><%=author.getAge()%>
        </td>
        <%if (!user.getUserType().name().equals("USER")) {%>

            <td>
                <form method="post">
                    <button formmethod="get" formaction="/editAuthor" name="id" value="<%=author.getId()%>">Edit</button>
                    <button formaction="/deleteAuthor?id=<%=author.getId()%>">Delete</button>
                </form>
            </td>
        <%}%>

    </tr>
    <%}%>
    <%} else {%>
    <h2>Sorry, we don't have any contributors at the moment</h2>
    <%}%>
</table>
</body>
</html>
