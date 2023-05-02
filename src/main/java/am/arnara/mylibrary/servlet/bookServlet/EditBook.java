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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet("/editBook")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100,
        fileSizeThreshold = 4096
)
public class EditBook extends HttpServlet {
    private static final String UPLOAD_FILES = "/home/radik/IdeaProjects/jakartaProjects/myLibrary/images/";

    private static final AuthorManager AUTHOR_MANAGER = new AuthorManager();
    private static final BookManager BOOK_MANAGER = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object strId = (req.getParameter("bookId") != null) ? req.getParameter("bookId") :
                req.getSession().getAttribute("bookId");
        int bookId = Integer.parseInt((String) strId);
        Book bookByID = BOOK_MANAGER.getBookByID(bookId);
        req.setAttribute("bookByID", bookByID);

        List authors = AUTHOR_MANAGER.getAuthors();
        req.setAttribute("setAuthors", authors);

        req.getRequestDispatcher("WEB-INF/bookPages/editBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String price = req.getParameter("price");
        String picName = null;

        Part faceBookImg = req.getPart("faceBookImg");

        if (faceBookImg != null && faceBookImg.getSize() > 0) {
            picName = System.nanoTime() + "_" + faceBookImg.getSubmittedFileName();
            faceBookImg.write(UPLOAD_FILES + picName);
        }

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
            BOOK_MANAGER.updateBookData(
                    Book.builder()
                            .id(Integer.parseInt(req.getParameter("id")))
                            .title(req.getParameter("title"))
                            .description(req.getParameter("description"))
                            .price(Double.parseDouble(price))
                            .authorId(authorByID)
                            .bookImg(picName)
                            .build()
            );

            req.getSession().setAttribute("userAddBooks", BOOK_MANAGER.showBooksByUserId(user.getId()));
            resp.sendRedirect("/");
        } else {
            req.getSession().setAttribute("msg", "Book's price cannot be a string. Please enter a number.");
            req.getSession().setAttribute("id", req.getParameter("id"));

            resp.sendRedirect("/editBook");
        }

    }

}
