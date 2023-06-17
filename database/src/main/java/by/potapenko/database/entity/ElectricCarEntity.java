package by.potapenko.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@DiscriminatorValue("electric")
public class ElectricCarEntity extends CarEntity {

    @Column(name = "ranges")
    public Integer rangeOfTravel;

    @Column(name = "battery")
    public Double batteryCapacity;

    @Builder
    public ElectricCarEntity(Long id, String brand, String model, int year, double price, List<RentalEntity> orders, List<ClientEntity> clients, EngineCar engine, BodyCar body, Integer rangeOfTravel, Double batteryCapacity) {
        super(id, brand, model, year, price, orders, clients, body, engine);
        this.rangeOfTravel = rangeOfTravel;
        this.batteryCapacity = batteryCapacity;
    }
}
