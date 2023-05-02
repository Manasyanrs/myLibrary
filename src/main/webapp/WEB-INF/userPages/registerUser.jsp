<%--
  Created by IntelliJ IDEA.
  User: radik
  Date: 27.04.23
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register User</title>
</head>
<%String msg = (String) session.getAttribute("msg");%>

<body>
<h2>Form for register employee data</h2>
<div>
    <a href="/">Home page</a><br>
</div>
<br>
<div>

    <% if (msg != null) {%>
    <span style="color: red"><%=msg%></span><br>
    <%
            session.removeAttribute("msg");
        }
    %>
    <form method="post" action="/registerUser">
        <table>
            <tr>
                <td> User name:</td>
                <td>
                    <input type="text" name="name" placeholder="Name:" required>
                </td>
            </tr>
            <tr>
                <td> User surname:</td>
                <td>
                    <input type="text" name="surname" placeholder="Surname:" required>
                </td>
            </tr>
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

            <tr>
                <td> User type:</td>
                <td>
                    <select name="userType">
                        <option value="USER">User</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                </td>
            </tr>

        </table>
        <br>

        <button type="submit"> Create</button>
    </form>

</div>
</body>
</html>
