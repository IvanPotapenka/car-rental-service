package by.potapenko.database.dao;

import by.potapenko.database.entity.BaseIdEntity;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Optional;
@AllArgsConstructor
public abstract class Dao<K extends Serializable, E extends BaseIdEntity<K>> {

    private Class<E> aClass;


    public Optional<E> create(E entity, Session session) {
        session.persist(entity);
        return Optional.ofNullable(entity);
    }

//    public List<E> findAll(int limit, int page, Session session) {
//        var criteria = session.getCriteriaBuilder().createQuery(aClass);
//        criteria.from(aClass);
//        return session.createQuery(criteria)
//                .setFirstResult(limit)
//                .setMaxResults(page)
//                .getResultList();
//    }
    public Optional<E> update(E entity, Session session) {
        session.merge(entity);
        return Optional.ofNullable(entity);
    }

    public void delete(Long id, Session session) {
        session.remove(session.find(aClass, id));
    }

    public Optional<E> findById(Long id, Session session) {
        return Optional.ofNullable(session.find(aClass, id));
    }
}
