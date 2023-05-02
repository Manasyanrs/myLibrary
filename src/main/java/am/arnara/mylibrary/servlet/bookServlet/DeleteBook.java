package am.arnara.mylibrary.servlet.bookServlet;

import am.arnara.mylibrary.managear.BookManager;
import am.arnara.mylibrary.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBook")
public class DeleteBook extends HttpServlet {
    private static final BookManager BOOK_MANAGER = new BookManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int bookId = Integer.parseInt(req.getParameter("id"));

        BOOK_MANAGER.deleteBookById(bookId);
        req.getSession().setAttribute("userAddBooks", BOOK_MANAGER.showBooksByUserId(user.getId()));

        resp.sendRedirect("/");
    }
}
