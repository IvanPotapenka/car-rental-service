package by.potapenko.web.servlet;

import by.potapenko.database.enam.ColorCar;
import by.potapenko.database.enam.FuelType;
import by.potapenko.database.enam.TransmissionType;
import by.potapenko.database.entity.Car;
import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

import static by.potapenko.web.util.PagesUtil.CAR_ADMIN;
import static by.potapenko.web.util.PagesUtil.CREATE_CAR;

@WebServlet("/admin/cars/create_car")
public class CreateCarServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_CAR).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car carCreate = Car.builder()
                .brand(req.getParameter("brand"))
                .model(req.getParameter("model"))
                .year(Integer.parseInt(req.getParameter("year_of_release")))
                .price(Double.parseDouble(req.getParameter("price")))
                .engine(Car.Engine.builder().fuelType(FuelType.valueOf(req.getParameter("fuel")))
                        .engineCapacity(Double.parseDouble(req.getParameter("engine_capacity")))
                        .horsePower(Integer.parseInt(req.getParameter("horse_power")))
                        .transmission(TransmissionType.valueOf(req.getParameter("transmission")))
                        .fuelConsumption(Double.parseDouble(req.getParameter("fuel_consumption"))).build())
                .body(Car.Body.builder().placeQuantity(Integer.parseInt(req.getParameter("quantity_places")))
                        .doorQuantity(Integer.parseInt(req.getParameter("quantity_doors")))
                        .trunkVolume(Integer.parseInt(req.getParameter("trunk_volume")))
                        .vinCode(req.getParameter("vin_code"))
                        .number(req.getParameter("number"))
                        .color(ColorCar.valueOf(req.getParameter("color"))).build())
                .build();
        carService.create(carCreate).ifPresentOrElse(
                car -> successCreateCar(req, resp, car),
                () -> faultCreateCar(req, resp));
    }

    @SneakyThrows
    private static void successCreateCar(HttpServletRequest req, HttpServletResponse resp, Car car) {
        req.setAttribute("car", car);
        req.setAttribute("create_car", true);
        req.getRequestDispatcher(CAR_ADMIN).forward(req, resp);
    }

    @SneakyThrows
    private static void faultCreateCar(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("create_car", false);
        req.getRequestDispatcher(CREATE_CAR).forward(req, resp);
    }
}

