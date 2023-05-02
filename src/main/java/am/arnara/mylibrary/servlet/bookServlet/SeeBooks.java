package am.arnara.mylibrary.servlet.bookServlet;

import am.arnara.mylibrary.managear.BookManager;
import am.arnara.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/books")
public class SeeBooks extends HttpServlet {
    private static final BookManager BOOk_MANAGER = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchParam = req.getParameter("searchParam");
        List<Book> foundBooks;

        if (searchParam == null || searchParam.equals("")) {
            foundBooks = BOOk_MANAGER.getBooks();

        } else {
            foundBooks = BOOk_MANAGER.searchBook(searchParam);
        }

        req.setAttribute("foundBooks", foundBooks);
        req.getRequestDispatcher("WEB-INF/bookPages/seeBooks.jsp").forward(req, resp);

    }

}
