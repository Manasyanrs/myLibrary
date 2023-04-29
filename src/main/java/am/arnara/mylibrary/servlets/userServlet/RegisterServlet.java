package am.arnara.mylibrary.servlets.userServlet;

import am.arnara.mylibrary.managears.UserManager;
import am.arnara.mylibrary.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/registerUser")
public class RegisterServlet extends HttpServlet {
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/userPages/registerUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userManager.loginUser(email, password);
        if (user == null) {
            User userBuild = User.builder()
                    .name(req.getParameter("name"))
                    .surname(req.getParameter("surname"))
                    .email(email)
                    .password(password)
                    .build();
            userManager.registerUser(userBuild);

            req.getSession().setAttribute("user", userBuild);
            resp.sendRedirect("/");

        }else {
            HttpSession session = req.getSession();
            session.setAttribute("msg", "User by email or password exists");
            resp.sendRedirect("/registerUser");

        }
    }
}
