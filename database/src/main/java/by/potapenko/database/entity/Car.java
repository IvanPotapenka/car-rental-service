package by.potapenko.database.entity;

import by.potapenko.database.enam.ColorCar;
import by.potapenko.database.enam.FuelType;
import by.potapenko.database.enam.TransmissionType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Car {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private Engine engine;
    private Body body;
    private double price;
    @Data
    @Builder
    public static class Engine {
        private double engineCapacity;
        private int horsePower;
        private FuelType fuelType;
        private TransmissionType transmission;
        private double fuelConsumption;

    }
    @Data
    @Builder

    public static class Body {
        private ColorCar color;
        private int placeQuantity;
        private int doorQuantity;
        private int trunkVolume;
        private String vinCode;
        private String number;
    }
}

