package am.arnara.mylibrary.servlet.authorServlet;

import am.arnara.mylibrary.managear.AuthorManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/authors")
public class SeeAuthors extends HttpServlet {
    private static final AuthorManager AUTHOR_MANAGER = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List authors = AUTHOR_MANAGER.getAuthors();
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("WEB-INF/authorPages/seeAuthors.jsp").forward(req, resp);
    }

}
