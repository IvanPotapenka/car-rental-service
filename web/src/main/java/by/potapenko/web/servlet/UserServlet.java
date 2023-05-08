package by.potapenko.web.servlet;

import by.potapenko.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.USER_ADMIN;

@WebServlet("/admin/users/user")
public class UserServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("user_id", false);
            req.getRequestDispatcher(USER_ADMIN).forward(req, resp);
        } else {
            req.setAttribute("user_id", true);
            req.setAttribute("user", userService.findById(Long.parseLong(id)).get());
            req.getRequestDispatcher(USER_ADMIN).forward(req, resp);
        }
    }
}
