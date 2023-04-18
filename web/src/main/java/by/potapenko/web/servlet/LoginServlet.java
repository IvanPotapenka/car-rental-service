package by.potapenko.web.servlet;

import by.potapenko.database.entites.User;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN).forward(req, resp);
    }

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.getBy(email, password).ifPresentOrElse(
                user -> successSingIn(req, resp, user),
                () -> faultSingIn(req, resp));
    }

    @SneakyThrows
    private static void successSingIn(HttpServletRequest req, HttpServletResponse resp, User user) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/cars");
    }

    @SneakyThrows
    private static void faultSingIn(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error=true");
    }

}
