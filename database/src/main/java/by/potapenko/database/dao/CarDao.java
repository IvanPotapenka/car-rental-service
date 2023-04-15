package by.potapenko.database.dao;

import by.potapenko.database.DummyDatabase;
import by.potapenko.database.entites.Car;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CarDao {

    private static final CarDao INSTANCE = new CarDao();
    private DummyDatabase db = DummyDatabase.getInstance();

    public List<Car> getAll() {
        return new ArrayList<>(db.getCars().values());
    }

    public Optional<Car> getById(Long id) {
        return Optional.ofNullable(db.getCars().get(id));
    }

    public Car create(Car car) {
        return db.getCars().put(car.getId(), car);
    }

    public Car delete(Long id) {
        return Optional.ofNullable(db.getCars().remove(id)).orElseThrow();
    }

    public static CarDao getInstance() {
        return INSTANCE;
    }
}