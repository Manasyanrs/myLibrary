<%@ page import="am.arnara.mylibrary.models.Author" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: radik
  Date: 29.04.23
  Time: 00:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<%List<Author> setAuthors = (List<Author>) request.getAttribute("setAuthors");%>
<%String msg = (String) session.getAttribute("msg");%>
<body>
<h2>Form for add book</h2>
<div>
    <a href="/">Home page</a><br>
</div>
<br>
<% if (msg != null) {%>
<h1><%=msg%>
</h1>
<%}%>

<div>
    <form method="post" action="/addBook">
        <table>
            <tr>
                <td>Book title:</td>
                <td>
                    <input type="text" name="title" placeholder="Book title:" required>
                </td>
            </tr>
            <tr>
                <td>Book description:</td>
                <td>
                    <input type="text" name="description" placeholder="Book description:" required>
                </td>
            </tr>
            <tr>
                <td>Book price:</td>
                <td>
                    <input type="text" name="price" placeholder="Book price:" required>
                </td>
            </tr>
            <tr>
                <td>Book author:</td>
                <td>
                    <select name="authorId">
                        <%if (setAuthors != null && !setAuthors.isEmpty()) {%>
                        <% for (Author setAuthor : setAuthors) {%>
                        <option value="<%=setAuthor.getId()%>">
                            <%=setAuthor.getName()%>
                        </option>

                        <%}%>
                        <%}%>
                    </select>
                </td>
            </tr>

        </table>
        <br>

        <button type="submit">Add Book</button>
    </form>

</div>
</body>
</html>
