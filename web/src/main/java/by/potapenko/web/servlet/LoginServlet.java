package by.potapenko.web.servlet;

import by.potapenko.database.entity.UserEntity;
import by.potapenko.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.LOGIN;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.findByEmailAndPassword(email, password).ifPresentOrElse(
                user -> successSingIn(req, resp, user),
                () -> faultSingIn(req, resp));
    }

    @SneakyThrows
    private static void successSingIn(HttpServletRequest req, HttpServletResponse resp, UserEntity user) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/catalog");
    }

    @SneakyThrows
    private static void faultSingIn(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("find_user_error", true);
        req.getRequestDispatcher(LOGIN).forward(req, resp);
    }
}
