package by.potapenko.service;

import by.potapenko.database.dao.RentalDao;
import by.potapenko.database.entity.RentalEntity;
import by.potapenko.database.hibernate.SessionBuilding;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class RentalService {
    private static final RentalService INSTANCE = new RentalService();
    private final RentalDao rentalDao = RentalDao.getInstance();
    private static final SessionBuilding sessionBuilding = SessionBuilding.getInstance();


    public Optional<RentalEntity> create(RentalEntity rental) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            rental.setPrice(rental.getPrice() * rental.getRentalDays());
            rentalDao.create(rental, session);
            session.getTransaction().commit();
            return Optional.of(rental);
        }
    }

    public List<RentalEntity> findAll(int limit, int page) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            List<RentalEntity> rentals = rentalDao.findAll(limit, page, session);
            session.getTransaction().commit();
            return rentals;
        }
    }

    public Optional<RentalEntity> update(RentalEntity rental) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            rentalDao.update(rental, session);
            session.getTransaction().commit();
            return Optional.ofNullable(rental);
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            rentalDao.delete(id, session);
            session.getTransaction().commit();
        }
    }

        public Optional<RentalEntity> findById (Long id){
            try (Session session = sessionBuilding.getSession()) {
                session.beginTransaction();
                Optional<RentalEntity> rental = rentalDao.findById(id, session);
                session.getTransaction().commit();
                return rental;
            }
        }

    public Integer getCount(Double limit) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Integer count = (int) Math.ceil(RentalDao.getCount(session) / limit);
            session.getTransaction().commit();
            return count;
        }
    }

        public List<RentalEntity> findAllOrdersOfUser(Long id) {
            try (Session session = sessionBuilding.getSession()) {
                session.beginTransaction();
                List<RentalEntity> rental = rentalDao.findOrdersOfUser(id, session);
                session.getTransaction().commit();
                return rental;
            }
        }
    public List<RentalEntity> findAllOrdersOfClient(Long id) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            List<RentalEntity> rental = rentalDao.findOrdersOfClient(id, session);
            session.getTransaction().commit();
            return rental;
        }
    }
    public List<RentalEntity> findAllOrdersOfCar(Long id) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            List<RentalEntity> rental = rentalDao.findOrdersOfCar(id, session);
            session.getTransaction().commit();
            return rental;
        }
    }

        public static RentalService getInstance () {
            return INSTANCE;
        }
    }

