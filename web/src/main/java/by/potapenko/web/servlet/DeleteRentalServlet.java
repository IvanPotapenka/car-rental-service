package by.potapenko.web.servlet;

import by.potapenko.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CLIENT_ADMIN;
import static by.potapenko.web.util.PagesUtil.RENTALS;
@WebServlet("/admin/rentals/rental/delete_rental")
public class DeleteRentalServlet extends HttpServlet {
    private final RentalService rentalService = RentalService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.getRequestDispatcher(CLIENT_ADMIN).forward(req, resp);
        } else {
            rentalService.deleteById(Long.parseLong(id));
            req.setAttribute("rental_delete_success", true);
            req.getRequestDispatcher(RENTALS).forward(req, resp);
        }
    }
}
