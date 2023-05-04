package by.potapenko.database.connection;

import by.potapenko.database.util.PropertiesApi;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;

@UtilityClass
public class ConnectionManager {
    private String user_key = "db_user";
    private String password_key = "db_password";
    private String url_key = "db_url";

    @SneakyThrows
    public Connection open() {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                PropertiesApi.get(url_key),
                PropertiesApi.get(user_key),
                PropertiesApi.get(password_key));
    }
}
