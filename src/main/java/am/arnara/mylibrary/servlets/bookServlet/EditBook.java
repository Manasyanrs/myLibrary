package am.arnara.mylibrary.servlets.bookServlet;

import am.arnara.mylibrary.managears.AuthorManager;
import am.arnara.mylibrary.managears.BookManager;
import am.arnara.mylibrary.models.Author;
import am.arnara.mylibrary.models.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editBook")
public class EditBook extends HttpServlet {
    private final AuthorManager AUTHOR_MANAGER = new AuthorManager();
    private final BookManager BOOk_MANAGER = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object strId = (req.getParameter("id") != null) ? req.getParameter("id") :
                req.getSession().getAttribute("id");

        int bookId = Integer.parseInt((String) strId);
        Book bookByID = BOOk_MANAGER.getBookByID(bookId);
        req.setAttribute("bookByID", bookByID);

        List authors = AUTHOR_MANAGER.getAuthors();
        req.setAttribute("setAuthors", authors);

        req.getRequestDispatcher("WEB-INF/bookPages/editBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String price = req.getParameter("price");

        if (BOOk_MANAGER.isCorrectPriceType(price)) {
            Author authorByID = AUTHOR_MANAGER.getAuthorByID(Integer.parseInt(req.getParameter("authorId")));
            BOOk_MANAGER.updateBookData(
                    Book.builder()
                            .id(Integer.parseInt(req.getParameter("id")))
                            .title(req.getParameter("title"))
                            .description(req.getParameter("description"))
                            .price(Double.parseDouble(price))
                            .author_id(authorByID)
                            .build()
            );
            resp.sendRedirect("/");
        } else {
            req.getSession().setAttribute("msg", "Book's price cannot be a string. Please enter a number.");
            req.getSession().setAttribute("id", req.getParameter("id"));

            resp.sendRedirect("/editBook");
        }

    }

}
