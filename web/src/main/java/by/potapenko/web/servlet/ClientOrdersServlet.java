package by.potapenko.web.servlet;

import by.potapenko.database.entity.UserEntity;
import by.potapenko.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CLIENT_ORDERS;

//@WebServlet("/account/orders")
public class ClientOrdersServlet extends HttpServlet {
    private  final RentalService rentalService;

    public ClientOrdersServlet(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher(CLIENT_ORDERS).forward(req, resp);
        } else {
            req.setAttribute("rentals", rentalService.findAllOrdersOfClient(user.getId()));
            req.getRequestDispatcher(CLIENT_ORDERS).forward(req, resp);
        }
    }
}
