package am.arnara.mylibrary.servlets.bookServlet;

import am.arnara.mylibrary.managears.BookManager;
import am.arnara.mylibrary.models.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/searchBook")
public class SearchBook extends HttpServlet {
    private final BookManager BOOk_MANAGER = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/bookPages/foundBooks.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchParam = req.getParameter("searchParam");
        List<Book> foundBooks  = BOOk_MANAGER.searchBook(searchParam);

        if (foundBooks != null && !foundBooks.isEmpty()){
            req.getSession().setAttribute("foundBooks", foundBooks);
            resp.sendRedirect("/books");

        }else {
            req.getSession().setAttribute("foundBooks", null);
            req.getSession().setAttribute("books", BOOk_MANAGER.getBooks());
            resp.sendRedirect("/books");
        }
    }
}
