package by.potapenko.service;

import by.potapenko.database.dao.CarDao;
import by.potapenko.database.dto.CarFilter;
import by.potapenko.database.entity.Car;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CarService {

    private static final CarService INSTANCE = new CarService();

    private final CarDao carDao = CarDao.getInstance();

    public Optional<Car> create(Car car) {
        return carDao.create(car);
    }

    public List<Car> findAll(int limit, int page) {

        return carDao.findAll(limit, page);
    }

    public Optional<Car> update(Car car) {
        return carDao.update(car);
    }

    public void deleteById(Long id) {
        carDao.deleteById(id);
    }

    public List<Car> findByFilter(CarFilter filter) {

        return carDao.findByFilter(filter);
    }

    public Optional<Car> findById(Long id) {
        return carDao.findById(id);
    }

    public int getSizeCarFilter(CarFilter carFilter) {

        return carDao.getSizeCarFilter(carFilter);
    }

    public int getSizeCarTable() {

        return carDao.getSizeCarTable();
    }

    public static CarService getInstance() {
        return INSTANCE;
    }
}
