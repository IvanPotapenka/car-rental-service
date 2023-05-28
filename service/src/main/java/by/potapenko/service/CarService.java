package by.potapenko.service;

import by.potapenko.database.dao.CarDao;
import by.potapenko.database.entity.CarEntity;
import by.potapenko.database.hibernate.SessionBuilding;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CarService {

    private static final CarService INSTANCE = new CarService();
    private static final SessionBuilding sessionBuilding = SessionBuilding.getInstance();
    private final CarDao carDao = CarDao.getInstance();

    public Optional<CarEntity> create(CarEntity car) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            carDao.create(car, session);
            session.getTransaction().commit();
            return Optional.ofNullable(car);
        }
    }

    public List<CarEntity> findAll(int limit, int page) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            List<CarEntity> cars = carDao.findAll(limit, page, session);
            session.getTransaction().commit();
            return cars;
        }
    }

    public Optional<CarEntity> update(CarEntity car) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            carDao.update(car, session);
            session.getTransaction().commit();
            return Optional.ofNullable(car);
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            carDao.delete(id, session);
            session.getTransaction().commit();
        }
    }

//    public List<Car> findByFilter(CarFilter filter) {
//
//        return carDao.findByFilter(filter);
//    }

    public Optional<CarEntity> findById(Long id) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Optional<CarEntity> car = carDao.findById(id, session);
            session.getTransaction().commit();
            return car;
        }
    }

    public Integer getCount(Double limit) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Integer count = (int) Math.ceil(carDao.getCount(session) / limit);
            session.getTransaction().commit();
            return count;
        }
    }

    public static CarService getInstance() {
        return INSTANCE;
    }
}
