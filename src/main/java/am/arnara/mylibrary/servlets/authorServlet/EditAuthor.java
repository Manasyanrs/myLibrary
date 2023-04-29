package am.arnara.mylibrary.servlets.authorServlet;

import am.arnara.mylibrary.managears.AuthorManager;
import am.arnara.mylibrary.models.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editAuthor")
public class EditAuthor extends HttpServlet {
    private final AuthorManager AUTHOR_MANAGER = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object strId = (req.getParameter("id") != null) ? req.getParameter("id") :
                req.getSession().getAttribute("id");

        int authorId = Integer.parseInt((String) strId);
        Author authorByID = AUTHOR_MANAGER.getAuthorByID(authorId);
        req.setAttribute("authorByID", authorByID);
        req.getRequestDispatcher("WEB-INF/authorPages/editAuthor.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String age = req.getParameter("age");
        if (AUTHOR_MANAGER.inputAgeType(age)) {
            AUTHOR_MANAGER.updateAuthorData(
                    Author.builder()
                            .id(Integer.parseInt(req.getParameter("id")))
                            .name(req.getParameter("name"))
                            .surname(req.getParameter("surname"))
                            .email(req.getParameter("email"))
                            .age(Integer.parseInt(age))
                            .build()
            );
            resp.sendRedirect("/");
        } else {
            req.getSession().setAttribute("msg", "Author's age cannot be a string. Please enter a number.");
            req.getSession().setAttribute("id", req.getParameter("id"));

            resp.sendRedirect("/editAuthor");
        }

    }

}
