package by.potapenko.web.servlet;

import by.potapenko.database.entity.Car;
import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static by.potapenko.web.util.PagesUtil.ADD_CAR;

@WebServlet("/add_car")
public class AddCarServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADD_CAR).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (carService.findByVinCode(req.getParameter("vin_code")).isEmpty()) {
            carService.create(Car.builder()
                    .brand(req.getParameter("brand"))
                    .model(req.getParameter("model"))
                    .yearOfRelease(Integer.parseInt(req.getParameter("year")))
                    .vinCode(req.getParameter("vin_code"))
                    .engine(Double.parseDouble(req.getParameter("engine")))
                    .price(Double.parseDouble(req.getParameter("price")))
                    .build());
            resp.sendRedirect("/add_car?vin_code=false");
        } else {
            resp.sendRedirect("/add_car?vin_code=true");
        }
    }
}

