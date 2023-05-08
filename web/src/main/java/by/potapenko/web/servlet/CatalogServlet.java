package by.potapenko.web.servlet;

import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CATALOG;

@WebServlet("/catalog")
public final class CatalogServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
        int limit = req.getParameter("limit") != null ? Integer.parseInt(req.getParameter("limit")) : 9;

        if (carService.findAll(limit, page).isEmpty()) {
            req.setAttribute("find_car_error", true);
            req.getRequestDispatcher(CATALOG).forward(req, resp);

        } else {
            req.setAttribute("limit", limit);
            req.setAttribute("page", page);
            req.setAttribute("count", (int) Math.ceil(carService.getSizeCarTable() / (limit * 1.0)));

            req.setAttribute("find_car_error", false);
            req.setAttribute("cars", carService.findAll(limit, page));
            req.getRequestDispatcher(CATALOG).forward(req, resp);
        }
    }
}




