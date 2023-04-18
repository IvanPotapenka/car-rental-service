package by.potapenko.web.servlet;

import by.potapenko.database.entites.User;
import by.potapenko.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.REGISTRATION;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (userService.getByEmail(req.getParameter("email")).isEmpty()) {
            userService.create(User.builder()
                    .name(req.getParameter("name"))
                    .surname(req.getParameter("surname"))
                    .email(req.getParameter("email"))
                    .phoneNumber(req.getParameter("phone"))
                    .password(req.getParameter("password"))
                    .build());
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/registration?email=true");
        }
    }
}
