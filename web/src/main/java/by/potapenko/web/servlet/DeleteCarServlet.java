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

@WebServlet("/admin/cars/car/delete_car")
public class DeleteCarServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.getRequestDispatcher(CAR_ADMIN).forward(req, resp);
        } else {
            carService.deleteById(Long.parseLong(id));
            req.setAttribute("car_delete_success", true);
            req.getRequestDispatcher(CARS).forward(req, resp);
        }
    }
}
