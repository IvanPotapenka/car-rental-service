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

import static by.potapenko.web.util.PagesUtil.UPDATE_USER;

@WebServlet("/admin/users/user/update_user")
public class UpdateUserServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("user", userService.findById(Long.parseLong(id)).get());
        req.getRequestDispatcher(UPDATE_USER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User updateUser = User.builder()
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .id(Long.parseLong(req.getParameter("id")))
                .build();
        userService.update(updateUser).ifPresentOrElse(
                user -> successCreateUser(req, resp, user),
                () -> faultCreateCar(req, resp));
    }

    @SneakyThrows
    private static void successCreateUser(HttpServletRequest req, HttpServletResponse resp, User user) {
        req.setAttribute("update_user_error", false);
        req.setAttribute("user", user);
        req.getRequestDispatcher(UPDATE_USER).forward(req, resp);
    }

    @SneakyThrows
    private static void faultCreateCar(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("update_user_error", true);
        req.getRequestDispatcher(UPDATE_USER).forward(req, resp);
    }
}


