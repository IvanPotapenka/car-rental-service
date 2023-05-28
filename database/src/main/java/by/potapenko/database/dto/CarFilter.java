package by.potapenko.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CarFilter {
    private String brand;
    private String model;
    private String color;
    private String fuelType;
    private String transmission;
    private double fuelConsumption;
    private double price;
    private String limit;
    private String page;


    public Integer getLimit(){
        return limit == null ? 3 : Integer.parseInt(limit);
    }
    public Integer getPage() {
        return page == null ? 1 : Integer.parseInt(page);
    }
}

