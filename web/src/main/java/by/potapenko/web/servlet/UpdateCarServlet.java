package by.potapenko.web.servlet;

import by.potapenko.database.enam.ColorCar;
import by.potapenko.database.enam.FuelType;
import by.potapenko.database.enam.TransmissionType;
import by.potapenko.database.entity.CarEntity;
import by.potapenko.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.time.LocalDateTime;

import static by.potapenko.web.util.PagesUtil.UPDATE_CAR;

@WebServlet("/admin/cars/car/update_car")
public class UpdateCarServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("car", carService.findById(Long.parseLong(id)).get());
        req.getRequestDispatcher(UPDATE_CAR).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarEntity updateCar = CarEntity.builder()
                .id(Long.parseLong(req.getParameter("car_id")))
                .brand(req.getParameter("brand"))
                .model(req.getParameter("model"))
                .year(Integer.parseInt(req.getParameter("year_of_release")))
                .price(Double.parseDouble(req.getParameter("price")))
                .engine(CarEntity.Engine.builder().fuelType(FuelType.valueOf(req.getParameter("fuel")))
                        .engineCapacity(Double.parseDouble(req.getParameter("engine_capacity")))
                        .horsePower(Integer.parseInt(req.getParameter("horse_power")))
                        .transmission(TransmissionType.valueOf(req.getParameter("transmission")))
                        .fuelConsumption(Double.parseDouble(req.getParameter("fuel_consumption"))).build())
                .body(CarEntity.Body.builder().placeQuantity(Integer.parseInt(req.getParameter("quantity_places")))
                        .doorQuantity(Integer.parseInt(req.getParameter("quantity_doors")))
                        .trunkVolume(Integer.parseInt(req.getParameter("trunk_volume")))
                        .vinCode(req.getParameter("vin_code"))
                        .number(req.getParameter("number"))
                        .color(ColorCar.valueOf(req.getParameter("color"))).build())
                .build();
          updateCar.setDateOfCreation(LocalDateTime.parse(req.getParameter("date_of_creation")));
          carService.update(updateCar).ifPresentOrElse(
                    car -> successCreateCar(req, resp, car),
                    () -> faultCreateCar(req, resp));
    }

    @SneakyThrows
    private static void successCreateCar(HttpServletRequest req, HttpServletResponse resp, CarEntity car) {
        req.setAttribute("update_car_error", false);
        req.setAttribute("car", car);
        req.getRequestDispatcher(UPDATE_CAR).forward(req, resp);
    }

    @SneakyThrows
    private static void faultCreateCar(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("update_car_error", true);
        req.getRequestDispatcher(UPDATE_CAR).forward(req, resp);
    }
}


