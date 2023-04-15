package by.potapenko.database;

import by.potapenko.database.entites.Car;
import by.potapenko.database.entites.User;
import by.potapenko.database.enums.FuelType;
import by.potapenko.database.enums.TransmissionType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public final class DummyDatabase {
    private static final DummyDatabase INSTANCE = new DummyDatabase();

    private final Map<Long, Car> cars = new HashMap<>() {{
       final double engineCapacity = 1.6;
       final int year = 2012;
        final double price = 50.0;
        put(1L, Car.builder()
                .id(1L)
                .model("Renault Logan")
                .fuel(FuelType.BENZINE)
                .engineCapacity(engineCapacity)
                .transmission(TransmissionType.AUTOMATIC)
                .year(year)
                .price(price).build());
        final double engineCapacity1 = 1.9;
        final int year1 = 2016;
        final double price1 = 60.0;
        final long id2 = 2L;
        put(id2, Car.builder()
                .id(id2)
                .model("Kia Rio")
                .fuel(FuelType.DIESEL)
                .engineCapacity(engineCapacity1)
                .transmission(TransmissionType.MANUAL)
                .year(year1)
                .price(price1)
                .build());
        final double engineCapacity2 = 2.0;
        final int year2 = 2017;
        final double price2 = 70.0;
        final long id1 = 3L;
        put(id1, Car.builder()
                .id(id1)
                .model("Kia Sportage")
                .fuel(FuelType.BENZINE)
                .engineCapacity(engineCapacity2)
                .transmission(TransmissionType.AUTOMATIC)
                .year(year2)
                .price(price2)
                .build());
        final int year3 = 2020;
        final double price3 = 80.0;
        final long id = 4L;
        put(id, Car.builder()
                .id(id)
                .model("Skoda Rapid II")
                .fuel(FuelType.DIESEL)
                .engineCapacity(engineCapacity2)
                .transmission(TransmissionType.MANUAL)
                .year(year3)
                .price(price3)
                .build());
    }};

    private final Map<Long, User> users = new HashMap<>() {{
        put(1L, User.builder()
                .id(1L)
                .name("Jhon")
                .surname("Kolin")
                .email("kolin@gmail.com")
                .phoneNumber("+37552365656")
                .password("kolin")
                .build());
        final long id = 2L;
        put(id, User.builder()
                .id(id)
                .name("Will")
                .surname("Smitt")
                .email("smitt@gmail.com")
                .phoneNumber("+375447108357")
                .password("smitt")
                .build());
    }};

    public static DummyDatabase getInstance() {
        return INSTANCE;
    }
}
