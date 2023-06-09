package am.arnara.mylibrary.servlet.authorServlet;

import am.arnara.mylibrary.managear.AuthorManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAuthor")
public class DeleteAuthor extends HttpServlet {
    private static final AuthorManager AUTHOR_MANAGER = new AuthorManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("id"));
        AUTHOR_MANAGER.deleteAuthorById(authorId);
        resp.sendRedirect("/authors");
    }
}
