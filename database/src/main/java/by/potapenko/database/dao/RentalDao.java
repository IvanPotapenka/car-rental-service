package by.potapenko.database.dao;

import by.potapenko.database.entity.CarEntity;
import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.entity.RentalEntity;
import org.hibernate.Session;

import java.util.List;


public class RentalDao extends Dao<Long, RentalEntity>{
    private static final RentalDao INSTANCE = new RentalDao();

    private RentalDao() {
        super(RentalEntity.class);
    }


    public List<RentalEntity> findAll(int limit, int page, Session session) {
        return session.createQuery("FROM RentalEntity ORDER BY id", RentalEntity.class)
                .setFirstResult(getOffset(limit, page))
                .setMaxResults(limit)
                .getResultList();
    }
    public List<RentalEntity> findOrdersOfUser(Long id, Session session) {
        ClientEntity clientEntity = session.find(ClientEntity.class, id);
        return clientEntity.getOrders();
    }
    public List<RentalEntity> findOrdersOfClient(Long id, Session session) {
        ClientEntity clientEntity = session.find(ClientEntity.class, id);
        return clientEntity.getOrders();
    }
    public List<RentalEntity> findOrdersOfCar(Long id, Session session) {
        CarEntity carEntity = session.find(CarEntity.class, id);
        return carEntity.getOrders();
    }

    public static Long getCount(Session session) {
        return   (Long) session.createQuery("SELECT COUNT (*) FROM RentalEntity ")
                .getSingleResult();
    }

    public int getOffset(int limit, int page) {
        return limit * (page - 1);
    }

    public static RentalDao getInstance() {
        return INSTANCE;
    }
}


