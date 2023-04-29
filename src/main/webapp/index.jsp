<%@ page import="am.arnara.mylibrary.models.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./styles/homepageStyles.css">
    <title>My Library</title>
</head>
<% User user = (User) session.getAttribute("user");%>
<body>

<% if (user == null) {%>
<div class="loginReg">
    <form method="get">
        <button type="submit" formaction="/loginUser">Login</button>
        <button type="submit" formaction="/registerUser" formmethod="get">Register</button>
    </form>
</div>

<%} else {%>

<div class="correctLoginHead">
    <div class="correctUser">
        <div>
            <h1>Welcome <%= user.getName() %>
            </h1>

        </div>
        <div>
            <form method="get" action="/logoutUser">
                <button type="submit">Logout</button>
            </form>
        </div>
    </div>
    <div>
        <form method="get">
            <button formaction="/authors">See all authors</button>
            <button formaction="/searchBook">See all books</button>
            <button formaction="/addBook">Add book</button>
            <button formaction="/addAuthor">Add author</button>
        </form>

    </div>
</div>
<%}%>


</body>
</html>