package by.potapenko.database.dao;

import by.potapenko.database.config.DataBaseConfig;
import by.potapenko.database.repositpry.RentalRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataBaseConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql("classpath:test-data-rental.sql")
@Sql(value = "classpath:purge-data-rental.sql", executionPhase = AFTER_TEST_METHOD)
class RentalRepositoryTest {
    @Autowired
    private RentalRepository rentalRepository;

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllTheRentalsAreReturned() {
        String[] actual = rentalRepository.findAll()
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
        String[] actual = rentalRepository.findByClient_Orders(id)
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
        String[] actual = rentalRepository.findByCar_Orders(id)
                .stream()
                .map(rental -> rental.getClient().getFirstName())
                .toArray(String[]::new);
        String[] expected = List.of("Ivan")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }
}