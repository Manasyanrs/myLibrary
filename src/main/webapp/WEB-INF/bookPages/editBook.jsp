<%@ page import="am.arnara.mylibrary.model.Author" %>
<%@ page import="am.arnara.mylibrary.model.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: radik
  Date: 28.04.23
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit book</title>
</head>
<%
    Book bookByID = (Book) request.getAttribute("bookByID");
    List<Author> setAuthors = (List<Author>) request.getAttribute("setAuthors");

%>
<%String msg = (String) session.getAttribute("msg");%>
<body>
<h1> Edit book page </h1>
<form method="get" action="/">
    <button>Home page</button>
</form>
<%if (msg != null) {%>
<h3><%=msg%>
</h3>

<% session.removeAttribute("msg");
}%>
<div>
    <form method="post" action="/editBook" enctype="multipart/form-data">
        <table>
            <tr>
                <td>Facebook img:</td>
                <img src="/img?picName=<%=bookByID.getBookImg()%>" width="150px"/>


                <td>
                    <input type="file" name="faceBookImg">
                </td>
            </tr>
            <tr>
                <td>Book title:</td>
                <td>
                    <input type="text" name="title" placeholder="Book title:" required value="<%=bookByID.getTitle()%>"
                           selected>
                </td>
            </tr>
            <tr>
                <td>Book description:</td>
                <td>
                    <input type="text" name="description" placeholder="Book description:" required
                           value="<%=bookByID.getDescription()%>">
                </td>
            </tr>
            <tr>
                <td>Book price:</td>
                <td>
                    <input type="text" name="price" placeholder="Book price:" required value="<%=bookByID.getPrice()%>">
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

        <button type="submit" name="id" value="<%=bookByID.getId()%>">Change book data</button>
    </form>

</div>

</body>
</html>
