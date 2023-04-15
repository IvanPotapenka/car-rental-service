package by.potapenko.service;

import by.potapenko.database.dao.CarDao;
import by.potapenko.database.entites.Car;
import by.potapenko.database.enums.FuelType;
import by.potapenko.database.enums.TransmissionType;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CarService {

    private static final CarService INSTANCE = new CarService();

    private final CarDao carDao = CarDao.getInstance();

    public List<Car> getAll() {
        return carDao.getAll();
    }

    public Car getById(Long id) {
        final int year = 2000;
        return carDao.getById(id).orElse(Car.builder()
                .id(id)
                .model("Citroen C5")
                .fuel(FuelType.DIESEL)
                .engineCapacity(0.0)
                .transmission(TransmissionType.MANUAL)
                .year(year)
                .price(0.0).build());
    }

    public static CarService getInstance() {
        return INSTANCE;
    }
}
