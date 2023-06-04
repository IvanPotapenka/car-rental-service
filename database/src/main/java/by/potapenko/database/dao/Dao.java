package by.potapenko.database.dao;

import by.potapenko.database.entity.BaseIdEntity;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class Dao<K extends Serializable, E extends BaseIdEntity<K>> {

    private Class<E> aClass;

    public Optional<E> create(E entity, Session session) {
        session.persist(entity);
        return Optional.ofNullable(entity);
    }

    public List<E> findAll(int limit, int page, Session session) {
        var query = session.getCriteriaBuilder().createQuery(aClass);
        query.from(aClass);
        return session.createQuery(query)
                .setMaxResults(limit)
                .setFirstResult(page)
                .getResultList();
    }

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

    public Long getCount(Session session) {
        var criteria = session.getCriteriaBuilder();
        JpaCriteriaQuery<Long> query = criteria.createQuery(Long.class);
        query.select(criteria.count(query.from(aClass)));
        return session.createQuery(query).getSingleResult();
    }

    public Optional<E> findByEmailAndPassword(String email, String password, Session session) {
        var cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<E> query = cb.createQuery(aClass);
        JpaRoot<E> userRoot = query.from(aClass);
        query.select(userRoot)
                .where(cb.and(cb.equal(userRoot.get("email"), email),
                        cb.equal(userRoot.get("password"), password)));
        return session.createQuery(query).uniqueResultOptional();
    }

    public boolean findByEmail(String email, Session session) {
        var cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<E> query = cb.createQuery(aClass);
        JpaRoot<E> userRoot = query.from(aClass);
        query.select(userRoot).where(cb.equal(userRoot.get("email"), email));
        return session.createQuery(query)
                .getResultList().isEmpty();
    }
}
