package by.potapenko.web.servlet;

import by.potapenko.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CLIENT_ORDERS_ADMIN;

@WebServlet("/admin/orders/client")
public class ClientOrdersAdminServlet extends HttpServlet {
    RentalService rentalService = RentalService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("rentals", rentalService.findAllOrdersOfClient(Long.parseLong(id)));
        req.getRequestDispatcher(CLIENT_ORDERS_ADMIN).forward(req, resp);
    }
}
