package by.potapenko.database.dto;

import lombok.Builder;

@Builder
public record CarFilter(String brand,
                        String model,
                        String color,
                        String fuelType,
                        String transmission,
                        double fuelConsumption,
                        double price,
                        int limit,
                        int page) {
}
