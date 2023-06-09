package am.arnara.mylibrary.servlet.authorServlet;

import am.arnara.mylibrary.managear.AuthorManager;
import am.arnara.mylibrary.model.Author;
import am.arnara.mylibrary.utils.IsDigit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/addAuthor")
public class AddAuthor extends HttpServlet {
    private static final AuthorManager AUTHOR_MANAGER = new AuthorManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/authorPages/addAuthor.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        IsDigit isDigit = (digit) -> {
            try {
                Integer.parseInt(digit);
                return true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return false;
        };
        if (isDigit.isDigit(age)) {
            if (!AUTHOR_MANAGER.isAuthorInDB(email)) {
                Author buildAuthor = Author.builder()
                        .name(req.getParameter("name"))
                        .surname(req.getParameter("surname"))
                        .email(email)
                        .age(Integer.parseInt(age))
                        .build();
                AUTHOR_MANAGER.addAuthor(buildAuthor);
                resp.sendRedirect("/");
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("msg", "Please choose another email address.");
                resp.sendRedirect("/addAuthor");

            }
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("msg", "Author's age cannot be a string. Please enter a number.");
            resp.sendRedirect("/addAuthor");
        }
    }
}
