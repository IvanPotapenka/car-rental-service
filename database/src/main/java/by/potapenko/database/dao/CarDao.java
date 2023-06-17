package by.potapenko.database.dao;

import by.potapenko.database.dto.CarFilter;
import by.potapenko.database.entity.CarEntity;
import by.potapenko.database.entity.CarEntity_;
import by.potapenko.database.entity.NoElectricCarEntity_;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.util.ArrayList;
import java.util.List;


public final class CarDao extends Dao<Long, CarEntity> {
    private static final CarDao INSTANCE = new CarDao();

    private CarDao() {
        super(CarEntity.class);
    }

    public List<CarEntity> findByFilter(Session session, CarFilter filter) {
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        JpaCriteriaQuery<CarEntity> query = builder.createQuery(CarEntity.class);
        JpaRoot<CarEntity> carRoot = query.from(CarEntity.class);
        query.select(carRoot);
        query.where(collectPredicates(filter, builder, carRoot).toArray(Predicate[]::new));
        return session.createQuery(query)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getPage())
                .getResultList();
    }

    private static List<Predicate> collectPredicates(CarFilter filter, HibernateCriteriaBuilder builder, JpaRoot<CarEntity> carRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getBrand() != null) {
            predicates.add(builder.equal(carRoot.get(CarEntity_.BRAND), filter.getBrand()));
        }
        if (filter.getModel() != null) {
            predicates.add(builder.equal(carRoot.get(CarEntity_.MODEL), filter.getModel()));
        }
        if (filter.getColor() != null) {
            predicates.add(builder.equal(carRoot.get(CarEntity_.BODY).get("color"), filter.getColor()));
        }
        if (filter.getFuelType() != null) {
            predicates.add(builder.equal(carRoot.get(CarEntity_.ENGINE).get("fuelType"), filter.getFuelType()));
        }
        if (filter.getTransmission() != null) {
            predicates.add(builder.equal(carRoot.get(CarEntity_.ENGINE).get("transmission"), filter.getTransmission()));
        }
        if (filter.getFuelConsumption() != null) {
            predicates.add(builder.equal(carRoot.get(NoElectricCarEntity_.FUEL_CONSUMPTION), filter.getFuelConsumption()));
        }
        return predicates;
    }


    public static CarDao getInstance() {
        return INSTANCE;
    }
}
