package by.potapenko.database.dao;

import by.potapenko.database.entity.CarEntity;
import org.hibernate.Session;

import java.util.List;


public final class CarDao extends Dao<Long, CarEntity> {
    private static final CarDao INSTANCE = new CarDao();

    private CarDao() {
        super(CarEntity.class);
    }
    public List<CarEntity> findAll(int limit, int page, Session session) {
        return session.createQuery("FROM CarEntity ORDER BY id", CarEntity.class)
                .setFirstResult(getOffset(limit, page))
                .setMaxResults(limit)
                .getResultList();
    }

    public Long getCount(Session session) {
        return (Long) session.createQuery("SELECT COUNT (*) FROM CarEntity")
                .getSingleResult();
    }

    public int getOffset(int limit, int page) {
        return limit * (page - 1);
    }

    public static CarDao getInstance() {
        return INSTANCE;
    }
}
