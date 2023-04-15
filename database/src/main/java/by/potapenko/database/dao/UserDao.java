package by.potapenko.database.dao;

import by.potapenko.database.DummyDatabase;
import by.potapenko.database.entites.User;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    private final DummyDatabase db = DummyDatabase.getInstance();

    public Optional<User> getBy(String email, String password) {
        return db.getUsers().values().stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findAny();
    }

    public Optional<User> getByEmail(String email) {
        return db.getUsers().values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    public List<User> getAll() {
        return new ArrayList<>(db.getUsers().values());
    }

    public Optional<User> getById(Long id) {
        return Optional.ofNullable(db.getUsers().get(id));
    }

    public User create(User user) {
        Long dummyID = db.getUsers().keySet().size() + 1L;
        user.setId(dummyID);
        return db.getUsers().put(dummyID, user);
    }

    public User delete(Long id) {
        return Optional.ofNullable(db.getUsers().remove(id)).orElseThrow();
    }

    public static UserDao getINSTANCE() {
        return INSTANCE;
    }
}


