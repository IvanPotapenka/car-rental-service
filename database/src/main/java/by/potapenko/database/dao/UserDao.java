package by.potapenko.database.dao;

import by.potapenko.database.entity.AdminEntity;
import by.potapenko.database.entity.UserEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public final class UserDao extends Dao<Long, UserEntity> {
    private static final UserDao INSTANCE = new UserDao();
    private UserDao() {
        super(UserEntity.class);
    }
    public List<UserEntity> findAll(int limit, int page, Session session) {
        return session.createQuery("FROM UserEntity ORDER BY login", UserEntity.class)
                .setFirstResult(getOffset(limit, page))
                .setMaxResults(limit)
                .getResultList();
    }
     public boolean findByEmail(String email, Session session) {
        return session.createQuery("FROM UserEntity WHERE email = :email")
                .setParameter("email", email)
                .getResultList().isEmpty();
    }

    public Optional<UserEntity> findByEmailAndPassword(String email, String password, Session session) {
        return session.createQuery("FROM UserEntity WHERE email = :email AND password = :password", UserEntity.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResultOptional();
    }

    public Optional<AdminEntity> findByAdmin(String login, String password, Session session) {
        return session.createQuery("FROM AdminEntity WHERE login = :login AND password = :password", AdminEntity.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .uniqueResultOptional();
    }

    public Long getCount(Session session) {
        return   (Long) session.createQuery("SELECT COUNT (*) FROM UserEntity")
                .getSingleResult();
    }
    public int getOffset(int limit, int page){
        return limit * (page - 1);
    }
    public static UserDao getInstance() {
        return INSTANCE;
    }
}


