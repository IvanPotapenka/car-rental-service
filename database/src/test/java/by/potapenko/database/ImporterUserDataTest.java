package by.potapenko.database;

import by.potapenko.database.entity.UserEntity;
import by.potapenko.database.entity.enam.UserRole;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;

import java.util.Arrays;

@UtilityClass
public class ImporterUserDataTest {

    public void userDataTestImport(Session session) {

        var Bob = UserEntity.builder()
                .login("Bob")
                .email("bob@mail.ru")
                .password("0000")
                .role(UserRole.USER)
                .build();

        var Tom = UserEntity.builder()
                .login("Tom")
                .email("tom@mail.ru")
                .password("0000")
                .role(UserRole.USER)
                .build();

        var Rick = UserEntity.builder()
                .login("Rick")
                .email("rick@mail.ru")
                .password("0000")
                .role(UserRole.USER)
                .build();

        for (UserEntity userEntity : Arrays.asList(Bob, Tom, Rick)) {
            session.persist(userEntity);
        }
    }
}


