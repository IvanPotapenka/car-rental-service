package by.potapenko.database;

import by.potapenko.database.entity.CarEntity;
import by.potapenko.database.entity.NoElectricCarEntity;
import by.potapenko.database.entity.enam.ColorCar;
import by.potapenko.database.entity.enam.FuelType;
import by.potapenko.database.entity.enam.TransmissionType;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;

import java.util.Arrays;

@UtilityClass
public class ImporterCarDataTest {

    public void carDataTestImport(Session session) {

        CarEntity audy = NoElectricCarEntity.builder()
                .brand("Audy")
                .model("A8")
                .year(2020)
                .price(100)
                .body(CarEntity.Body.builder()
                        .color(ColorCar.GREEN)
                        .doorQuantity(2)
                        .placeQuantity(4)
                        .trunkVolume(235)
                        .vinCode("12345")
                        .number("54321")
                        .build())
                .engine(CarEntity.Engine.builder()
                        .engineCapacity(2.0)
                        .horsePower(140)
                        .fuelType(FuelType.DIESEL)
                        .transmission(TransmissionType.MANUAL)
                        .build())
                .fuelConsumption(6.0)
                .build();
        session.persist(audy);

        CarEntity bmw = NoElectricCarEntity.builder()
                .brand("BMW")
                .model("X6")
                .year(2020)
                .price(34)
                .body(CarEntity.Body.builder()
                        .color(ColorCar.WHITE)
                        .doorQuantity(5)
                        .placeQuantity(5)
                        .trunkVolume(345)
                        .vinCode("67890")
                        .number("098765")
                        .build())
                .engine(CarEntity.Engine.builder()
                        .engineCapacity(2.5)
                        .horsePower(170)
                        .fuelType(FuelType.DIESEL)
                        .transmission(TransmissionType.MANUAL)
                        .build())
                .fuelConsumption(6.0)
                .build();

        CarEntity mercedes = NoElectricCarEntity.builder()
                .brand("Mercedes")
                .model("F1")
                .year(2020)
                .price(90)
                .body(CarEntity.Body.builder()
                        .color(ColorCar.BLACK)
                        .doorQuantity(4)
                        .placeQuantity(5)
                        .trunkVolume(455)
                        .vinCode("54321")
                        .number("67890")
                        .build())
                .engine(CarEntity.Engine.builder()
                        .engineCapacity(1.8)
                        .horsePower(150)
                        .fuelType(FuelType.GASOLINE)
                        .transmission(TransmissionType.AUTOMATIC)
                        .build())
                .fuelConsumption(6.0)
                .build();

        for (CarEntity carParentEntity : Arrays.asList(audy, bmw, mercedes)) {
            session.persist(carParentEntity);
        }
    }
}


