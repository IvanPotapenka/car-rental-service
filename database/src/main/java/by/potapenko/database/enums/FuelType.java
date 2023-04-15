package by.potapenko.database.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FuelType {
    BENZINE("Benzine AI95"),
    GAZ("Gaz"),
    DIESEL("Disel");

    private final String title;
}
