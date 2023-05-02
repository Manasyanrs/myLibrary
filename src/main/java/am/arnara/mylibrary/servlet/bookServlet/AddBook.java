package am.arnara.mylibrary.servlet.bookServlet;

import am.arnara.mylibrary.managear.AuthorManager;
import am.arnara.mylibrary.managear.BookManager;
import am.arnara.mylibrary.model.Author;
import am.arnara.mylibrary.model.Book;
import am.arnara.mylibrary.model.User;
import am.arnara.mylibrary.utils.IsDigit;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/addBook")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100,
        fileSizeThreshold = 4096
)
public class AddBook extends HttpServlet {
    private static final BookManager BOOK_MANAGER = new BookManager();
    private static final AuthorManager AUTHOR_MANAGER = new AuthorManager();
    private static final String UPLOAD_FILES = "/home/radik/IdeaProjects/jakartaProjects/myLibrary/images/";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = AUTHOR_MANAGER.getAuthors();
        req.setAttribute("setAuthors", authors);
        req.getRequestDispatcher("WEB-INF/bookPages/addBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String price = req.getParameter("price");
        String picName = null;

        IsDigit isDigit = (digit) -> {
            try {
                Double.parseDouble(digit);
                return true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return false;
        };

        if (isDigit.isDigit(price)) {
            Author authorByID = AUTHOR_MANAGER.getAuthorByID(Integer.parseInt(req.getParameter("authorId")));
            User user = (User) req.getSession().getAttribute("user");
            Part faceBookImg = req.getPart("faceBookImg");

            if (faceBookImg != null && faceBookImg.getSize() > 0) {
                picName = System.nanoTime() + "_" + faceBookImg.getSubmittedFileName();
                faceBookImg.write(UPLOAD_FILES + picName);

            }

            Book buildBook = Book.builder()
                    .title(req.getParameter("title"))
                    .description(req.getParameter("description"))
                    .price(Double.parseDouble(price))
                    .authorId(authorByID)
                    .userId(user)
                    .bookImg(picName)
                    .build();
            BOOK_MANAGER.addBook(buildBook);
            if (user.getUserType().name().equals("ADMIN")) {
                req.getSession().setAttribute("userAddBooks", BOOK_MANAGER.getBooks());
            } else {
                req.getSession().setAttribute("userAddBooks", BOOK_MANAGER.showBooksByUserId(user.getId()));
            }
            resp.sendRedirect("/");


        } else {
            HttpSession session = req.getSession();
            session.setAttribute("msg", "Book's price cannot be a string. Please enter a number.");
            resp.sendRedirect("/addBook");
        }
    }
}
