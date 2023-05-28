package by.potapenko.web.servlet;

import by.potapenko.database.entity.UserEntity;
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
        if (userService.findByEmail(req.getParameter("email"))) {
            UserEntity userCreate = UserEntity.builder()
                    .login(req.getParameter("name"))
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .build();
            userService.create(userCreate);
            req.setAttribute("create_user", true);
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
        } else {
            req.setAttribute("email_error", true);
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
        }

    }
//        if (userService.findByEmail(req.getParameter("email")).isEmpty()) {
//            User userCreate = User.builder()
//                    .name(req.getParameter("name"))
//                    .surname(req.getParameter("surname"))
//                    .email(req.getParameter("email"))
//                    .password(req.getParameter("password"))
//                    .build();
//            userService.create(userCreate).ifPresentOrElse(
//                    user -> successCreateUser(req, resp, user),
//                    () -> faultCreateUser(req, resp));
//        } else {
//            req.setAttribute("email_error", true);
//            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
//        }
//    }
//
//    @SneakyThrows
//    private static void successCreateUser(HttpServletRequest req, HttpServletResponse resp, User user) {
//        req.setAttribute("user", user);
//        req.setAttribute("create_user", true);
//        req.getRequestDispatcher(REGISTRATION).forward(req, resp);
//    }
//
//    @SneakyThrows
//    private static void faultCreateUser(HttpServletRequest req, HttpServletResponse resp) {
//        req.setAttribute("create_user", false);
//        req.getRequestDispatcher(REGISTRATION).forward(req, resp);
//    }
}
