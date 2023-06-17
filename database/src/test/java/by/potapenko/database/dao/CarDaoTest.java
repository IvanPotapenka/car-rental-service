package by.potapenko.database.dao;

import by.potapenko.database.ImporterCarDataTest;
import by.potapenko.database.dto.CarFilter;
import by.potapenko.database.entity.BodyCar;
import by.potapenko.database.entity.CarEntity;
import by.potapenko.database.entity.EngineCar;
import by.potapenko.database.entity.NoElectricCarEntity;
import by.potapenko.database.entity.enam.ColorCar;
import by.potapenko.database.entity.enam.FuelType;
import by.potapenko.database.entity.enam.TransmissionType;
import by.potapenko.database.hibernate.SessionBuilding;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarDaoTest {

    private static final CarDao carDao = CarDao.getInstance();
    private static final SessionBuilding sessionFactory = SessionBuilding.getInstance();

    @BeforeAll
    static void beforeAll() {
        try (var session = sessionFactory.getSession()) {
            var transaction = session.beginTransaction();
            ImporterCarDataTest.carDataTestImport(session);
            transaction.commit();
        }
    }

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllTheCarsAreReturned() {

        @Cleanup Session session = sessionFactory.getSession();
        int limit = 4;
        int page = 0;
        String[] actual = carDao.findAll(limit, page, session)
                .stream()
                .map(CarEntity::getBrand)
                .toArray(String[]::new);
        String[] expected = List.of("Audy", "BMW", "Mercedes")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void whenFindById_ThenAllTheFilteredReturnsValidCar() {
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        Optional<CarEntity> actual = carDao.findById(id, session);
        assertTrue(actual.isPresent());
        assertEquals("Audy", actual.get().getBrand());
    }

    @Test
    @Order(3)
    void whenCreatedInvokedWithCar_ThenCarIsSaved() {
        NoElectricCarEntity carTest = NoElectricCarEntity.builder()
                .brand("Citroen")
                .model("C5")
                .year(2022)
                .price(105)
                .body(BodyCar.builder()
                        .color(ColorCar.WHITE)
                        .doorQuantity(2)
                        .placeQuantity(4)
                        .trunkVolume(235)
                        .vinCode("12090")
                        .number("54098")
                        .build())
                .engine(EngineCar.builder()
                        .engineCapacity(2.0)
                        .horsePower(140)
                        .fuelType(FuelType.DIESEL)
                        .transmission(TransmissionType.MANUAL)
                        .build())
                .fuelConsumption(7.0)
                .build();

        @Cleanup Session session = sessionFactory.getSession();
        var transaction = session.beginTransaction();
        carDao.create(carTest, session);
        transaction.commit();
        int limit = 4;
        int page = 1;

        List<String> allBrand = carDao.findAll(limit, page, session).stream()
                .map(CarEntity::getBrand)
                .toList();
        assertTrue(allBrand.contains(carTest.getBrand()));
    }

    @Test
    @Order(4)
    void whenDeleteById_ThenNotFindById() {
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        Optional<CarEntity> car = carDao.findById(id, session);
        carDao.delete(car.get().getId(), session);
        Optional<CarEntity> carDeleted = carDao.findById(car.get().getId(), session);
        assertTrue(carDeleted.isEmpty());
    }

    @Test
    @Order(5)
    void whenUpdateById_ThenSavedCarUpdate() {
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        Optional<CarEntity> car = carDao.findById(id, session);
        car.get().setBrand("Volvo");
        carDao.update(car.get(), session);
        assertEquals("Volvo", car.get().getBrand());
    }

    @Test
    @Order(6)
    void whenFindAllByFilterContainsOnlyBrandsAndModelCarsInvoked_ThenAllTheFilteredByBrandsCarsAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        CarFilter filter = CarFilter.builder()
                .fuelType(FuelType.DIESEL)
                .fuelConsumption(6.0)
                .build();
        String[] actual = carDao.findByFilter(session, filter)
                .stream()
                .map(CarEntity::getBrand)
                .toArray(String[]::new);
        String[] expected = List.of("Audy", "BMW")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }
}