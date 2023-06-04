package by.potapenko.database.entity;

import by.potapenko.database.entity.enam.ColorCar;
import by.potapenko.database.entity.enam.FuelType;
import by.potapenko.database.entity.enam.TransmissionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Entity
@SuperBuilder
@Table(name = "car")
public abstract class CarEntity implements BaseIdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", length = 20, nullable = false)
    private String brand;

    @Column(name = "model", length = 20, nullable = false)
    private String model;

    @Column(name = "year_of_release", nullable = false)
    private int year;

    @Column(name = "price", nullable = false)
    private double price;

    @Builder.Default
    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private List<RentalEntity> orders = new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "cars")
    private List<ClientEntity> clients = new ArrayList<>();

    @Embedded
    private Engine engine;

    @Embedded
    private Body body;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Embeddable
    public static class Engine {

        @Column(name = "engine_capacity", nullable = false)
        private double engineCapacity;

        @Column(name = "horse_power", nullable = false)
        private int horsePower;

        @Column(name = "fuel", length = 20, nullable = false)
        @Enumerated(EnumType.STRING)
        private FuelType fuelType;

        @Column(name = "transmission", length = 20, nullable = false)
        @Enumerated(EnumType.STRING)
        private TransmissionType transmission;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Embeddable
    public static class Body {

        @Column(name = "quantity_places", nullable = false)
        private int placeQuantity;

        @Column(name = "quantity_doors", nullable = false)
        private int doorQuantity;

        @Column(name = "trunk_volume", nullable = false)
        private int trunkVolume;

        @Column(name = "vin_code", length = 20, nullable = false, unique = true)
        private String vinCode;

        @Column(name = "numbers", length = 20, nullable = false, unique = true)
        private String number;

        @Column(name = "color", length = 20, nullable = false)
        @Enumerated(EnumType.STRING)
        private ColorCar color;
    }
}

