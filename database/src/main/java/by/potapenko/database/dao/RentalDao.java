package by.potapenko.database.dao;

import by.potapenko.database.entity.CarEntity;
import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.entity.RentalEntity;
import org.hibernate.Session;

import java.util.List;


public class RentalDao extends Dao<Long, RentalEntity> {
    private static final RentalDao INSTANCE = new RentalDao();

    private RentalDao() {
        super(RentalEntity.class);
    }

    public List<RentalEntity> findOrdersOfClient(Long id, Session session) {
        ClientEntity clientEntity = session.find(ClientEntity.class, id);
        return clientEntity.getOrders();
    }

    public List<RentalEntity> findOrdersOfCar(Long id, Session session) {
        CarEntity carParentEntity = session.find(CarEntity.class, id);
        return carParentEntity.getOrders();
    }

    public static RentalDao getInstance() {
        return INSTANCE;
    }
}


