package by.potapenko.database.dao;

import by.potapenko.database.ImporterDataTest;
import by.potapenko.database.entity.CarEntity;
import by.potapenko.database.hibernate.SessionBuilding;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarDaoTest {

    private static final CarDao carDao = CarDao.getInstance();
    private static final SessionBuilding sessionFactory = SessionBuilding.getInstance();

    @BeforeAll
    static void beforeAll() {
        try (var session = sessionFactory.getSession()) {
            var transaction = session.beginTransaction();
            ImporterDataTest.dataTestImport(session);
            transaction.commit();
        }
    }
    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllTheCarsAreReturned() {

        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = carDao.findAll(3, 1, session)
                .stream()
                .map(CarEntity::getBrand)
                .toArray(String[]::new);
        String[] expected = List.of("Audy", "Bmw", "Mercedes")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }
}