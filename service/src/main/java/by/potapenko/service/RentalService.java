package by.potapenko.service;

import by.potapenko.database.entity.RentalEntity;
import by.potapenko.database.repositpry.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RentalService {
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Transactional
    public Optional<RentalEntity> create(RentalEntity rental) {
        rental.setPrice(rental.getPrice() * rental.getRentalDays());
        rentalRepository.save(rental);
        return Optional.of(rental);
    }

    public List<RentalEntity> findAll(int limit, int page) {
        return (List<RentalEntity>) rentalRepository.findAll(Pageable.ofSize(limit).withPage(page));
    }

    public void deleteById(Long id) {
        rentalRepository.deleteById(id);
    }

    public Optional<RentalEntity> findById(Long id) {
        return rentalRepository.findById(id);
    }

    public Integer getCount(Double limit) {
        return (Integer) (int) Math.ceil(rentalRepository.count() / limit);
    }

    public List<RentalEntity> findAllOrdersOfClient(Long id) {
        return rentalRepository.findByClient_Orders(id);
    }

    public List<RentalEntity> findAllOrdersOfCar(Long id) {
        return rentalRepository.findByCar_Orders(id);
    }
}

