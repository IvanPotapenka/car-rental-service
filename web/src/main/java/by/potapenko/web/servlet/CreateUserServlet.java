package by.potapenko.web.servlet;

import by.potapenko.database.enam.UserRole;
import by.potapenko.database.entity.UserEntity;
import by.potapenko.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        if (userService.findByEmail(req.getParameter("email"))) {
            UserEntity userCreate = UserEntity.builder()
                    .login(req.getParameter("name"))
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .role(UserRole.valueOf(req.getParameter("role")))
                    .build();
            userService.create(userCreate);
            req.setAttribute("user", userCreate);
            req.setAttribute("create_user", true);
            req.getRequestDispatcher(USER_ADMIN).forward(req, resp);
        } else {
            req.setAttribute("create_user", false);
            req.getRequestDispatcher(CREATE_USER).forward(req, resp);
        }
    }
}



