package by.potapenko.web.servlet;

import by.potapenko.database.dto.CarFilter;
import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CARS;
import static by.potapenko.web.util.PagesUtil.CAR_ADMIN;

//@WebServlet("/admin/cars")
public final class CarsServlet extends HttpServlet {
    private final CarService carService;

    public CarsServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CarFilter filter = CarFilter.builder()
                .limit(req.getParameter("limit"))
                .page(req.getParameter("page"))
                .build();

        Integer limit = filter.getLimit();
        Integer page = filter.getPage();

        req.setAttribute("limit", limit);
        req.setAttribute("page", page);
        req.setAttribute("count", carService.getCount(Double.valueOf(limit)));

        if (id == null) {
            req.setAttribute("cars", carService.findAll(limit, page));
            req.getRequestDispatcher(CARS).forward(req, resp);
        } else {
            req.setAttribute("car", carService.findById(Long.parseLong(id)));
            req.getRequestDispatcher(CAR_ADMIN).forward(req, resp);
        }
    }
}




