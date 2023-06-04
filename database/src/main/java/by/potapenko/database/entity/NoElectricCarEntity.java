package by.potapenko.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("no_electric")
public class NoElectricCarEntity extends CarEntity {

    @Column(name = "fuel_consumption")
    private Double fuelConsumption;

    @Builder
    public NoElectricCarEntity(Long id, String brand, String model, int year, double price, List<RentalEntity> orders, List<ClientEntity> clients, Engine engine, Body body, Double fuelConsumption) {
        super(id, brand, model, year, price, orders, clients, engine, body);
        this.fuelConsumption = fuelConsumption;
    }
}
