package am.arnara.mylibrary.servlets.bookServlet;

import am.arnara.mylibrary.managears.BookManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBook")
public class DeleteBook extends HttpServlet {
    private final BookManager BOOk_MANAGER = new BookManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("id"));
        BOOk_MANAGER.deleteBookById(bookId);
        resp.sendRedirect("/books");
    }
}
