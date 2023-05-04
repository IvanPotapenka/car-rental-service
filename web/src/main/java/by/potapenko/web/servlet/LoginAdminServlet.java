package by.potapenko.web.servlet;

import by.potapenko.database.entity.User;
import by.potapenko.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.ADMIN;
import static by.potapenko.web.util.PagesUtil.LOGIN_ADMIN;

@WebServlet("/login_admin")
public class LoginAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_ADMIN).forward(req, resp);
    }

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        userService.findByAdmin(login, password).ifPresentOrElse(
                user -> successSingIn(req, resp, user),
                () -> faultSingIn(req, resp));
    }

    @SneakyThrows
    private static void successSingIn(HttpServletRequest req, HttpServletResponse resp, User user) {
        req.getSession().setAttribute("admin", user);
        req.getRequestDispatcher(ADMIN).forward(req, resp);
    }

    @SneakyThrows
    private static void faultSingIn(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("find_admin_error", true);
        req.getRequestDispatcher(LOGIN_ADMIN).forward(req, resp);
    }
}

