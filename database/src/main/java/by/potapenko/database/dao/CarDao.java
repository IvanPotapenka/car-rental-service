package by.potapenko.database.dao;

import by.potapenko.database.connection.ConnectionPool;
import by.potapenko.database.dto.CarFilter;
import by.potapenko.database.enam.ColorCar;
import by.potapenko.database.enam.FuelType;
import by.potapenko.database.enam.TransmissionType;
import by.potapenko.database.entity.Car;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CarDao {
    private static final CarDao INSTANCE = new CarDao();
    private static final String INSERT_CAR = "INSERT INTO car(brand, model, year_of_release, color, fuel, engine_capacity, horse_power, transmission, fuel_consumption,\n" +
            "quantity_places, quantity_doors, trunk_volume, vin_code, numbers, price)\n" +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String SELECT_ALL = "SELECT * FROM car ORDER BY price LIMIT ? OFFSET ?";
    private static final String UPDATE_CAR = "UPDATE car SET brand=?, model=?, year_of_release=?, color=?, fuel=?, engine_capacity=?, horse_power=?, transmission=?, fuel_consumption=?,\n" +
            "quantity_places=?, quantity_doors=?, trunk_volume=?, vin_code=?, numbers=?, price=? WHERE car_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM car WHERE car_id=?";
    private static final String SELECT_BY_ID = "SELECT * FROM car WHERE car_id=?";

    private static final String SELECT_BY_FILTER = "SELECT * FROM car WHERE brand=? OR model=? OR color=? OR fuel=? OR transmission=?  OR fuel_consumption=? LIMIT ? OFFSET ?";
    private static final String SELECT_COUNT = "SELECT COUNT(car_id) AS count FROM car";
    private static final String SELECT_COUNT_FILTER = "SELECT COUNT(car_id) AS count FROM car WHERE brand=? OR model=? OR color=? OR fuel=? OR transmission=?  OR fuel_consumption=?";

    public Optional<Car> create(Car car) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     INSERT_CAR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.setString(4, car.getBody().getColor().name());
            preparedStatement.setString(5, car.getEngine().getFuelType().name());
            preparedStatement.setDouble(6, car.getEngine().getEngineCapacity());
            preparedStatement.setInt(7, car.getEngine().getHorsePower());
            preparedStatement.setString(8, car.getEngine().getTransmission().name());
            preparedStatement.setDouble(9, car.getEngine().getFuelConsumption());
            preparedStatement.setInt(10, car.getBody().getPlaceQuantity());
            preparedStatement.setInt(11, car.getBody().getDoorQuantity());
            preparedStatement.setInt(12, car.getBody().getTrunkVolume());
            preparedStatement.setString(13, car.getBody().getVinCode());
            preparedStatement.setString(14, car.getBody().getNumber());
            preparedStatement.setDouble(15, car.getPrice());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getLong("car_id"));
            }
            return Optional.of(car);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Car> findAll(int limit, int page) {
        List<Car> carList = new ArrayList<>();
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, limit * (page - 1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                carList.add(Car.builder()
                        .brand(resultSet.getString("brand"))
                        .model(resultSet.getString("model"))
                        .year(resultSet.getInt("year_of_release"))
                        .price(resultSet.getDouble("price"))
                        .id(resultSet.getLong("car_id"))
                        .engine(Car.Engine.builder().fuelType(FuelType.valueOf(resultSet.getString("fuel")))
                                .engineCapacity(resultSet.getDouble("engine_capacity"))
                                .horsePower(resultSet.getInt("horse_power"))
                                .transmission(TransmissionType.valueOf(resultSet.getString("transmission")))
                                .fuelConsumption(resultSet.getDouble("fuel_consumption")).build())
                        .body(Car.Body.builder().placeQuantity(resultSet.getInt("quantity_places"))
                                .doorQuantity(resultSet.getInt("quantity_doors"))
                                .trunkVolume(resultSet.getInt("trunk_volume"))
                                .vinCode(resultSet.getString("vin_code"))
                                .number(resultSet.getString("numbers"))
                                .color(ColorCar.valueOf(resultSet.getString("color"))).build())
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public Optional<Car> update(Car car) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR)) {
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.setString(4, car.getBody().getColor().name());
            preparedStatement.setString(5, car.getEngine().getFuelType().name());
            preparedStatement.setDouble(6, car.getEngine().getEngineCapacity());
            preparedStatement.setInt(7, car.getEngine().getHorsePower());
            preparedStatement.setString(8, car.getEngine().getTransmission().name());
            preparedStatement.setDouble(9, car.getEngine().getFuelConsumption());
            preparedStatement.setInt(10, car.getBody().getPlaceQuantity());
            preparedStatement.setInt(11, car.getBody().getDoorQuantity());
            preparedStatement.setInt(12, car.getBody().getTrunkVolume());
            preparedStatement.setString(13, car.getBody().getVinCode());
            preparedStatement.setString(14, car.getBody().getNumber());
            preparedStatement.setDouble(15, car.getPrice());
            preparedStatement.setLong(16, car.getId());
            preparedStatement.executeUpdate();
            return Optional.of(car);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Car> findById(Long id) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ?
                    Optional.of(Car.builder()
                            .brand(resultSet.getString("brand"))
                            .model(resultSet.getString("model"))
                            .year(resultSet.getInt("year_of_release"))
                            .price(resultSet.getDouble("price"))
                            .id(resultSet.getLong("car_id"))
                            .engine(Car.Engine.builder().fuelType(FuelType.valueOf(resultSet.getString("fuel")))
                                    .engineCapacity(resultSet.getDouble("engine_capacity"))
                                    .horsePower(resultSet.getInt("horse_power"))
                                    .transmission(TransmissionType.valueOf(resultSet.getString("transmission")))
                                    .fuelConsumption(resultSet.getDouble("fuel_consumption")).build())
                            .body(Car.Body.builder().placeQuantity(resultSet.getInt("quantity_places"))
                                    .doorQuantity(resultSet.getInt("quantity_doors"))
                                    .trunkVolume(resultSet.getInt("trunk_volume"))
                                    .vinCode(resultSet.getString("vin_code"))
                                    .number(resultSet.getString("numbers"))
                                    .color(ColorCar.valueOf(resultSet.getString("color"))).build())
                            .build()) : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Car> findByFilter(CarFilter filter) {
        List<Car> carList = new ArrayList<>();
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_FILTER)) {
            preparedStatement.setString(1, filter.brand());
            preparedStatement.setString(2, filter.model());
            preparedStatement.setString(3, filter.color());
            preparedStatement.setString(4, filter.fuelType());
            preparedStatement.setString(5, filter.transmission());
            preparedStatement.setDouble(6, filter.fuelConsumption());
            preparedStatement.setInt(7, filter.limit());
            preparedStatement.setInt(8, filter.limit() * (filter.page() - 1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                carList.add(Car.builder()
                        .brand(resultSet.getString("brand"))
                        .model(resultSet.getString("model"))
                        .year(resultSet.getInt("year_of_release"))
                        .price(resultSet.getDouble("price"))
                        .id(resultSet.getLong("car_id"))
                        .engine(Car.Engine.builder().fuelType(FuelType.valueOf(resultSet.getString("fuel")))
                                .engineCapacity(resultSet.getDouble("engine_capacity"))
                                .horsePower(resultSet.getInt("horse_power"))
                                .transmission(TransmissionType.valueOf(resultSet.getString("transmission")))
                                .fuelConsumption(resultSet.getDouble("fuel_consumption")).build())
                        .body(Car.Body.builder().placeQuantity(resultSet.getInt("quantity_places"))
                                .doorQuantity(resultSet.getInt("quantity_doors"))
                                .trunkVolume(resultSet.getInt("trunk_volume"))
                                .vinCode(resultSet.getString("vin_code"))
                                .number(resultSet.getString("numbers"))
                                .color(ColorCar.valueOf(resultSet.getString("color"))).build())
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public int getSizeCarFilter(CarFilter filter) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_FILTER)) {
            preparedStatement.setString(1, filter.brand());
            preparedStatement.setString(2, filter.model());
            preparedStatement.setString(3, filter.color());
            preparedStatement.setString(4, filter.fuelType());
            preparedStatement.setString(5, filter.transmission());
            preparedStatement.setDouble(6, filter.fuelConsumption());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSizeCarTable() {
        try (Connection connection = ConnectionPool.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_COUNT);
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static CarDao getInstance() {
        return INSTANCE;
    }
}
