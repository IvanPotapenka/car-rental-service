package by.potapenko.database.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertiesApi {
    private final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    @SneakyThrows
    private static void loadProperties() {
        try (InputStream inputStream = PropertiesApi.class.getClassLoader().getResourceAsStream(
                "application.properties")) {
            PROPERTIES.load(inputStream);
        }
    }
}
