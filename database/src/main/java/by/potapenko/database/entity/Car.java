package by.potapenko.database.entites;

import by.potapenko.database.enums.TransmissionType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode(of = "id")
public class Car {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private TransmissionType transmission;
    private String fuelConsumption;
    private double price;
}

