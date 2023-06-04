package by.potapenko.database.dao;

import by.potapenko.database.ImporterCarDataTest;
import by.potapenko.database.ImporterClientDataTest;
import by.potapenko.database.ImporterRentalDataTest;
import by.potapenko.database.ImporterUserDataTest;
import by.potapenko.database.hibernate.SessionBuilding;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RentalDaoTest {
    private static final RentalDao rentalDao = RentalDao.getInstance();

    private static final UserDao userDao = UserDao.getInstance();
    private static final SessionBuilding sessionFactory = SessionBuilding.getInstance();

    @BeforeAll
    static void beforeAll() {
        try (var session = sessionFactory.getSession()) {
            var transaction = session.beginTransaction();
            ImporterCarDataTest.carDataTestImport(session);
            ImporterUserDataTest.userDataTestImport(session);
            ImporterClientDataTest.clientDataTestImport(session);
            ImporterRentalDataTest.rentalDataTestImport(session);
            transaction.commit();
        }
    }

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllTheRentalsAreReturned() {

        @Cleanup Session session = sessionFactory.getSession();
        int limit = 4;
        int page = 1;
        String[] actual = rentalDao.findAll(limit, page, session)
                .stream()
                .map(rental -> rental.getStatus().name())
                .toArray(String[]::new);
        String[] expected = List.of("REJECTED", "CHECK", "ALLOWED")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void whenFindAllOrdersByClientInvoked_ThenAllTheOrdersOfClientAreReturned() {
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = rentalDao.findOrdersOfClient(id, session)
                .stream()
                .map(rental -> rental.getCar().getBrand())
                .toArray(String[]::new);
        String[] expected = List.of("Audy", "BMW", "Mercedes")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(3)
    void whenFindAllOrdersByCarInvoked_ThenAllTheOrdersOfCarAreReturned() {
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = rentalDao.findOrdersOfCar(id, session)
                .stream()
                .map(rental -> rental.getClient().getFirstName())
                .toArray(String[]::new);
        String[] expected = List.of("Ivan")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }
}