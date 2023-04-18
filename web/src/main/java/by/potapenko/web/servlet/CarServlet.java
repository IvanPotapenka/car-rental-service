package by.potapenko.web.servlet;

import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CAR;
import static by.potapenko.web.util.PagesUtil.CARS;

@WebServlet("/cars")
public final class CarServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("cars", carService.getAll());
            req.getRequestDispatcher(CARS).forward(req, resp);
        } else {
            req.setAttribute("car", carService.getById(Long.parseLong(id)));
            req.getRequestDispatcher(CAR).forward(req, resp);
        }
    }
}



