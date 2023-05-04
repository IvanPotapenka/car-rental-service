package by.potapenko.database.dao;

import by.potapenko.database.connection.ConnectionPool;
import by.potapenko.database.entity.User;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserDao {
    private static final UserDao INSTANCE = new UserDao();
    private static final String INSERT_USER = "INSERT INTO users(username, surname, email, password) VALUES (?,?,?,?)";

    public static final String SELECT_ALL = "SELECT * FROM users ORDER BY username LIMIT ? OFFSET ?";

    private static final String UPDATE_USER = "UPDATE users SET username=?, surname=?, email=?, password=? WHERE user_id=?";

    private static final String DELETE_BY_ID = "DELETE FROM users WHERE user_id=?";
    private static final String SELECT_BY_EMAIL_AND_PASSWORD = "SELECT users.email, users.password FROM users WHERE email=? AND password=?";
    public static final String SELECT_BY_ADMIN = "SELECT admins.login, admins.password FROM admins WHERE login=? AND password=?";

    private static final String SELECT_BY_EMAIL = "SELECT users.email FROM users WHERE email=?";

    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE user_id=?";
    private static final String SELECT_COUNT = "SELECT COUNT(*) AS count FROM users";

    public Optional<User> create(User user) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("user_id"));
            }
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static List<User> findAll(int limit, int page) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, limit * (page - 1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(
                        User.builder()
                                .id(resultSet.getLong("user_id"))
                                .name(resultSet.getString("username"))
                                .surname(resultSet.getString("surname"))
                                .email(resultSet.getString("email"))
                                .password(resultSet.getString("password"))
                                .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public Optional<User> update(User user) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Optional<User> findById(Long id) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ?
                    Optional.of(User.builder()
                            .id(resultSet.getLong("user_id"))
                            .name(resultSet.getString("username"))
                            .surname(resultSet.getString("surname"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .build()) : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<User> findByEmail(String email) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ?
                    Optional.of(User.builder()
                            .email(resultSet.getString("email"))
                            .build()) : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ?
                    Optional.of(User.builder()
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .build()) : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<User> findByAdmin(String login, String password) {
        try (Connection connection = ConnectionPool.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ADMIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ?
                    Optional.of(User.builder()
                            .email(resultSet.getString("login"))
                            .password(resultSet.getString("password"))
                            .build()) : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public int getSizeUserTable() {
        try (Connection connection = ConnectionPool.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_COUNT);
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static UserDao getINSTANCE() {
        return INSTANCE;
    }
}


