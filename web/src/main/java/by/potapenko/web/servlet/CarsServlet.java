package by.potapenko.web.servlet;

import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CARS;
import static by.potapenko.web.util.PagesUtil.CAR_ADMIN;

@WebServlet("/admin/cars")
public final class CarsServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
        int limit = req.getParameter("limit") != null ? Integer.parseInt(req.getParameter("limit")) : 3;

        if (id == null) {
            req.setAttribute("limit", limit);
            req.setAttribute("page", page);
            req.setAttribute("count", (int) Math.ceil(carService.getSizeCarTable() / (limit * 1.0)));
            req.setAttribute("cars", carService.findAll(limit, page));
            req.getRequestDispatcher(CARS).forward(req, resp);
        } else {
            req.setAttribute("car", carService.findById(Long.parseLong(id)));
            req.getRequestDispatcher(CAR_ADMIN).forward(req, resp);
        }
    }
}



