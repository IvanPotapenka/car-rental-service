package by.potapenko.service;

import by.potapenko.database.dto.CarFilter;
import by.potapenko.database.entity.CarEntity;
import by.potapenko.database.repositpry.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    public Optional<CarEntity> create(CarEntity car) {
        carRepository.save(car);
        return Optional.of(car);
    }

    public List<CarEntity> findAll(int limit, int page) {
        return (List<CarEntity>) carRepository.findAll(Pageable.ofSize(limit).withPage(page));
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    public List<CarEntity> findByFilter(CarFilter filter) {
        return carRepository.findByFilter(filter);
    }

    public Optional<CarEntity> findById(Long id) {
        return carRepository.findById(id);
    }

    public Integer getCount(Double limit) {
        return(Integer) (int) Math.ceil(carRepository.count() / limit);
    }
}

