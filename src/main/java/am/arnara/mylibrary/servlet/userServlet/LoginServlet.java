package am.arnara.mylibrary.servlet.userServlet;

import am.arnara.mylibrary.managear.BookManager;
import am.arnara.mylibrary.managear.UserManager;
import am.arnara.mylibrary.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginUser")
public class LoginServlet extends HttpServlet {
    private final UserManager USER_MANAGER = new UserManager();
    private final BookManager BOOK_MANAGER = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/userPages/loginUser.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = USER_MANAGER.loginUser(email, password);

        HttpSession session = req.getSession();
        if (user != null) {
            session.setAttribute("user", user);
            if (user.getUserType().name().equals("ADMIN")){
                session.setAttribute("userAddBooks", BOOK_MANAGER.getBooks());
            }else {
                session.setAttribute("userAddBooks", BOOK_MANAGER.showBooksByUserId(user.getId()));
            }
            resp.sendRedirect("/");

        } else {
            session.setAttribute("msg", "Username or Password is incorrect");
            resp.sendRedirect("/loginUser");
        }
    }
}
