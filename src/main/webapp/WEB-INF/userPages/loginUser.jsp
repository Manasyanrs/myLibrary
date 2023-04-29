<%--
  Created by IntelliJ IDEA.
  User: radik
  Date: 28.04.23
  Time: 02:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login user</title>
</head>
<%String msg = (String) session.getAttribute("msg");%>

<body>
<% if (msg != null) {%>
<span style="color: red"><%=msg%></span><br>
<%
        session.removeAttribute("msg");
    }
%>
<form method="post" action="/loginUser">
    <table>
        <tr>
            <td> User email:</td>
            <td>
                <input type="text" name="email" placeholder="Email:" required>
            </td>
        </tr>
        <tr>
            <td> User password:</td>
            <td>
                <input type="text" name="password" placeholder="Password" required>
            </td>
        </tr>

    </table>
    <br>

    <button type="submit"> Login</button>
</form>
</body>
</html>
