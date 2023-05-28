package by.potapenko.web.servlet;

import by.potapenko.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.RENTAL_ADMIN;

@WebServlet("/admin/rentals/rental")
public class RentalServlet extends HttpServlet {
    private final RentalService rentalService = RentalService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("rental_id", false);
            req.getRequestDispatcher(RENTAL_ADMIN).forward(req, resp);
        } else {
            req.setAttribute("rental_id", true);
            req.setAttribute("rental", rentalService.findById(Long.parseLong(id)).get());
            req.getRequestDispatcher(RENTAL_ADMIN).forward(req, resp);
        }
    }
}
