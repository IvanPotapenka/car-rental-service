package by.potapenko.service;

import by.potapenko.database.dao.UserDao;
import by.potapenko.database.entity.User;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getINSTANCE();

    public Optional<User> create(User user) {
        return userDao.create(user);
    }

    public List<User> findAll(int limit, int page) {
        return UserDao.findAll(limit, page);
    }

    public Optional<User> update(User user) {
        return userDao.update(user);
    }

    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return UserDao.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    public Optional<User> findByAdmin(String login, String password) {
        return userDao.findByAdmin(login, password);
    }

    public int getSizeUserTable() {

        return userDao.getSizeUserTable();
    }
    public static UserService getInstance() {
        return INSTANCE;
    }
}

