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

import static by.potapenko.web.util.PagesUtil.CREATE_USER;
import static by.potapenko.web.util.PagesUtil.USER_ADMIN;

@WebServlet("/admin/users/create_user")
public class CreateUserServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_USER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userCreate = User.builder()
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();
        userService.create(userCreate).ifPresentOrElse(
                user -> successCreateUser(req, resp, user),
                () -> faultCreateUser(req, resp));
    }
    @SneakyThrows
    private static void successCreateUser(HttpServletRequest req, HttpServletResponse resp, User user) {
        req.setAttribute("user", user);
        req.setAttribute("create_user", true);
        req.getRequestDispatcher(USER_ADMIN).forward(req, resp);
    }

    @SneakyThrows
    private static void faultCreateUser(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("create_user", false);
        req.getRequestDispatcher(CREATE_USER).forward(req, resp);
    }
}


