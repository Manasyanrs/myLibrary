<%--
  Created by IntelliJ IDEA.
  User: radik
  Date: 28.04.23
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String msg = (String) session.getAttribute("msg");%>
<html>
<head>
    <title>Add author</title>
</head>
<body>
<h2>Form for register author data</h2>
<div>
    <a href="/">Home page</a><br>
</div>
<br>
<% if (msg != null) {%>
<h1><%=msg%></h1>
<%}%>

<div>
    <form method="post" action="/addAuthor">
        <table>
            <tr>
                <td>Author name:</td>
                <td>
                    <input type="text" name="name" placeholder="Author name:" required>
                </td>
            </tr>
            <tr>
                <td>Author surname:</td>
                <td>
                    <input type="text" name="surname" placeholder="Author surname:" required>
                </td>
            </tr>
            <tr>
                <td>Author email:</td>
                <td>
                    <input type="text" name="email" placeholder="Author email:" required>
                </td>
            </tr>

            <tr>
                <td>Author age:</td>
                <td>
                    <input type="text" name="age" placeholder="Author age:" required>
                </td>
            </tr>
        </table>
        <br>

        <button type="submit">Add Author</button>
    </form>

</div>

</body>
</html>
