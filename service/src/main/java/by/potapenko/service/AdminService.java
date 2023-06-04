package by.potapenko.service;

import by.potapenko.database.dao.AdminDao;
import by.potapenko.database.entity.AdminEntity;
import by.potapenko.database.hibernate.SessionBuilding;
import org.hibernate.Session;

import java.util.Optional;

public class AdminService {
    private static final AdminService INSTANCE = new AdminService();
    private final AdminDao adminDao = AdminDao.getInstance();

    private static final SessionBuilding sessionBuilding = SessionBuilding.getInstance();

    public Optional<AdminEntity> findByEmailAndPassword(String login, String password) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Optional<AdminEntity> admin = adminDao.findByEmailAndPassword(login, password, session);
            session.getTransaction().commit();
            return admin;
        }
    }

    public static AdminService getInstance() {
        return INSTANCE;
    }
}
