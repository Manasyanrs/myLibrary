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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/addBook")
public class AddBook extends HttpServlet {
    private final BookManager BOOk_MANAGER = new BookManager();
    private final AuthorManager AUTHOR_MANAGER = new AuthorManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List authors = AUTHOR_MANAGER.getAuthors();
        req.setAttribute("setAuthors", authors);
        req.getRequestDispatcher("WEB-INF/bookPages/addBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String price = req.getParameter("price");

        if (BOOk_MANAGER.isCorrectPriceType(price)) {
            Author authorByID = AUTHOR_MANAGER.getAuthorByID(Integer.parseInt(req.getParameter("authorId")));

            Book buildBook = Book.builder()
                    .title(req.getParameter("title"))
                    .description(req.getParameter("description"))
                    .price(Double.parseDouble(price))
                    .author_id(authorByID)
                    .build();
            BOOk_MANAGER.addBook(buildBook);
            resp.sendRedirect("/");


        } else {
            HttpSession session = req.getSession();
            session.setAttribute("msg", "Book's price cannot be a string. Please enter a number.");
            resp.sendRedirect("/addBook");
        }


    }
}
