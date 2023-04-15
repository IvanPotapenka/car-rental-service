package by.potapenko.service;

import by.potapenko.database.dao.UserDao;
import by.potapenko.database.entites.User;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getINSTANCE();

    public Optional<User> getBy(String email, String password) {
        return userDao.getBy(email, password);
    }

    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public User create(User user) {
        return userDao.create(user);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}

