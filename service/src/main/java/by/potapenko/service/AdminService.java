package by.potapenko.service;

import by.potapenko.database.dao.AdminDao;
import by.potapenko.database.entity.UserEntity;
import by.potapenko.database.hibernate.SessionBuilding;
import org.hibernate.Session;

import java.util.Optional;

public class AdminService {

    private static final AdminService INSTANCE = new AdminService();
    private final AdminDao adminDao = AdminDao.getInstance();

    private static final SessionBuilding sessionBuilding = SessionBuilding.getInstance();

    public Optional<UserEntity> findByEmailAndPassword(String login, String password) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Optional<UserEntity> admin = adminDao.findByEmailAndPassword(login, password, session);
            session.getTransaction().commit();
            return admin;
        }
    }

    public static AdminService getInstance() {
        return INSTANCE;
    }
}
