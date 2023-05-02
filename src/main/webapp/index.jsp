<%@ page import="am.arnara.mylibrary.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="am.arnara.mylibrary.model.Book" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./styles/homepageStyles.css">
    <title>My Library</title>
</head>
<% User user = (User) session.getAttribute("user");
    List<Book> currentUserAddBooks = (List<Book>) session.getAttribute("userAddBooks");
%>
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
            <button formaction="/books">See all books</button>
            <button formaction="/addBook">Add book</button>
            <%if (!user.getUserType().name().equals("USER")) {%>
            <button formaction="/addAuthor">Add author</button>
            <%}%>
        </form>

    </div>

    <div>
        <br>

        <%if (currentUserAddBooks == null || currentUserAddBooks.isEmpty() && !user.getUserType().name().equals("ADMIN")) {%>
        <span>
                    <h3><%=user.getName()%> you have no added books.</h3>
                </span>
        <form method="get" action="/addBook">
                    <span>
                        <h3>Do you want to add a book.
                            <button>Add</button>

                        </h3>
                    </span>
        </form>
        <%} else {%>
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
                for (Book book : currentUserAddBooks) {%>
            <tr>
                <td>
                    <img src="/img?picName=<%=book.getBookImg()%>" width="150px"/>
                </td>
                <td><%=book.getTitle()%>
                </td>
                <td><%=book.getDescription()%>
                </td>
                <td><%=book.getPrice()%>
                </td>
                <td><%=book.getAuthorId().getName()%>
                </td>
                <td>
                    <form method="post">
                        <button formmethod="get" formaction="/editBook" name="bookId" value="<%=book.getId()%>">Edit
                        </button>
                        <button formaction="/deleteBook?id=<%=book.getId()%>">Delete</button>
                    </form>
                </td>
            </tr>
            <%}%>

        </table>

        <%}%>

    </div>
</div>
<%}%>


</body>
</html>