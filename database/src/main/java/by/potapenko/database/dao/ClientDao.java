package by.potapenko.database.dao;

import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.entity.UserEntity;
import org.hibernate.Session;

import java.util.List;

public class ClientDao extends Dao<UserEntity, ClientEntity> {
    private static final ClientDao INSTANCE = new ClientDao();

    private ClientDao() {
        super(ClientEntity.class);
    }

    public List<ClientEntity> findAll(int limit, int page, Session session) {
        return session.createQuery("FROM ClientEntity ORDER BY lastName", ClientEntity.class)
                .setFirstResult(getOffset(limit, page))
                .setMaxResults(limit)
                .getResultList();
    }

    public Long getCount(Session session) {
        return (Long) session.createQuery("SELECT COUNT (*) FROM ClientEntity ")
                .getSingleResult();
    }

    public List<ClientEntity> getSearchResult(String search, Session session) {
        return session.createQuery("SELECT client FROM ClientEntity client WHERE client.firstName LIKE :search", ClientEntity.class)
                .setParameter("search", '%' + search + '%')
                .list();
    }

    public int getOffset(int limit, int page) {
        return limit * (page - 1);
    }

    public static ClientDao getInstance() {
        return INSTANCE;
    }
}
