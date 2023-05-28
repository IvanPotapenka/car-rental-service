package by.potapenko.web.servlet;

import by.potapenko.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.RENTALS;
import static by.potapenko.web.util.PagesUtil.USER_ADMIN;
@WebServlet("/admin/rentals")
public class RentalsServlet extends HttpServlet {
    private final RentalService rentalService = RentalService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
        int limit = req.getParameter("limit") != null ? Integer.parseInt(req.getParameter("limit")) : 3;

        if (id == null) {
            req.setAttribute("rentals", rentalService.findAll(limit, page));
            req.setAttribute("limit", limit);
            req.setAttribute("page", page);
            req.setAttribute("count",rentalService.getCount(Double.valueOf(limit)));
            req.getRequestDispatcher(RENTALS).forward(req, resp);
        } else {
            req.setAttribute("rental", rentalService.findById(Long.parseLong(id)).get());
            req.getRequestDispatcher(USER_ADMIN).forward(req, resp);
        }
    }
}
