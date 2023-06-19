package by.potapenko.web.servlet;

import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CAR_ADMIN;

//@WebServlet("/admin/cars/car")
public class CarServlet extends HttpServlet {
    private final CarService carService;

    public CarServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("car_id", false);
            req.getRequestDispatcher(CAR_ADMIN).forward(req, resp);
        } else {
            req.setAttribute("car_id", true);
            req.setAttribute("car", carService.findById(Long.parseLong(id)).get());
            req.getRequestDispatcher(CAR_ADMIN).forward(req, resp);
        }
    }
}
