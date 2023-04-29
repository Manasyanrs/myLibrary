<%@ page import="am.arnara.mylibrary.models.Author" %><%--
  Created by IntelliJ IDEA.
  User: radik
  Date: 28.04.23
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit author</title>
</head>
<%Author authorByID = (Author) request.getAttribute("authorByID");%>
<%String msg = (String) session.getAttribute("msg");%>
<body>
<h1> Edit author page </h1>
<form method="get" action="/">
    <button>Home page</button>
</form>
<%if (msg != null) {%>
<h3><%=msg%>
</h3>

<% session.removeAttribute("msg");}%>
<div>
    <form method="post" action="/editAuthor">
        <table>
            <tr>
                <td>Author name:</td>
                <td>
                    <input type="text" name="name" placeholder="Author name:" required
                           value="<%=authorByID.getName()%>">
                </td>
            </tr>
            <tr>
                <td>Author surname:</td>
                <td>
                    <input type="text" name="surname" placeholder="Author surname:" required
                           value="<%=authorByID.getSurname()%>">
                </td>
            </tr>
            <tr>
                <td>Author email:</td>
                <td>
                    <input type="text" name="email" placeholder="Author email:" required
                           value="<%=authorByID.getEmail()%>">
                </td>
            </tr>

            <tr>
                <td>Author age:</td>
                <td>
                    <input type="text" name="age" placeholder="Author age:" required value="<%=authorByID.getAge()%>">
                </td>
            </tr>
        </table>
        <br>

        <button type="submit" name="id" value="<%=authorByID.getId()%>">Change author date</button>
    </form>

</div>

</body>
</html>
