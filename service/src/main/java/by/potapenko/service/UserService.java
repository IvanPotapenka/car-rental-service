package by.potapenko.service;

import by.potapenko.database.dao.UserDao;
import by.potapenko.database.entity.AdminEntity;
import by.potapenko.database.entity.UserEntity;
import by.potapenko.database.hibernate.SessionBuilding;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    private static final SessionBuilding sessionBuilding = SessionBuilding.getInstance();

    public Optional<UserEntity> create(UserEntity user) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            userDao.create(user, session);
            session.getTransaction().commit();
            return Optional.ofNullable(user);
        }
    }

    public List<UserEntity> findAll(int limit, int page) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            List<UserEntity> users = userDao.findAll(limit, page, session);
            session.getTransaction().commit();
            return users;
        }
    }

    public Optional<UserEntity> update(UserEntity user) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            userDao.update(user, session);
            session.getTransaction().commit();
            return Optional.ofNullable(user);
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            userDao.delete(id, session);
            session.getTransaction().commit();
        }
    }

    public Optional<UserEntity> findById(Long id) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Optional<UserEntity> user = userDao.findById(id, session);
            session.getTransaction().commit();
            return user;
        }
    }

    public boolean findByEmail(String email) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            boolean user = userDao.findByEmail(email, session);
            session.getTransaction().commit();
            return user;
        }
    }

    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Optional<UserEntity> user = userDao.findByEmailAndPassword(email, password, session);
            session.getTransaction().commit();
            return user;
        }
    }

    public Optional<AdminEntity> findByAdmin(String login, String password) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Optional<AdminEntity> admin = userDao.findByAdmin(login, password, session);
            session.getTransaction().commit();
            return admin;
        }
    }

    public Integer getCount(Double limit) {
        try (Session session = sessionBuilding.getSession()) {
            session.beginTransaction();
            Integer count = (int) Math.ceil(userDao.getCount(session) / limit);
            session.getTransaction().commit();
            return count;
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}

