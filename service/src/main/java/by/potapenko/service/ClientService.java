package by.potapenko.service;

import by.potapenko.database.dao.ClientDao;
import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.hibernate.SessionBuilding;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private static final ClientService INSTANCE = new ClientService();
    private final ClientDao clientDao = ClientDao.getInstance();
    private static final SessionBuilding sessionBuilding = SessionBuilding.getInstance();

    public Optional<ClientEntity> create(ClientEntity client) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            clientDao.create(client, session);
            session.getTransaction().commit();
            return Optional.of(client);
        }
    }

    public List<ClientEntity> findAll(int limit, int page) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            List<ClientEntity> clients = clientDao.findAll(limit, page, session);
            session.getTransaction().commit();
            return clients;
        }
    }

    public Optional<ClientEntity> update(ClientEntity client) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            clientDao.update(client, session);
            session.getTransaction().commit();
            return Optional.ofNullable(client);
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            clientDao.delete(id, session);
            session.getTransaction().commit();
        }
    }

    public Optional<ClientEntity> findById(Long id) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Optional<ClientEntity> client = clientDao.findById(id, session);
            session.getTransaction().commit();
            return client;
        }
    }

    public Integer getCount(Double limit) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Integer count = (int) Math.ceil(clientDao.getCount(session) / limit);
            session.getTransaction().commit();
            return count;
        }
    }

    public static ClientService getInstance() {
        return INSTANCE;
    }
}

