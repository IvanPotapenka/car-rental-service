package by.potapenko.database.dao;

import by.potapenko.database.config.DataBaseConfig;
import by.potapenko.database.entity.UserEntity;
import by.potapenko.database.entity.enam.UserRole;
import by.potapenko.database.repositpry.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataBaseConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql("classpath:test-data-user.sql")
@Sql(value = "classpath:purge-data-user.sql", executionPhase = AFTER_TEST_METHOD)
class UserDaoTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllTheClientsAreReturned() {
        String[] actual = userRepository.findAll()
                .stream()
                .map(UserEntity::getLogin)
                .toArray(String[]::new);
        String[] expected = List.of("Bob", "Tom", "Rick")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void whenFindById_ThenAllTheFilteredReturnsValidUser() {
        Long id = 4L;
        Optional<UserEntity> actual = userRepository.findById(id);
        assertTrue(actual.isPresent());
        assertEquals("Bob", actual.get().getLogin());
    }

    @Test
    @Order(3)
    void whenCreatedInvokedWithUser_ThenUserIsSaved() {
        var userTest = UserEntity.builder()
                .login("Jhon")
                .email("jhon@mail.ru")
                .phone("+3756789345")
                .password("1234")
                .role(UserRole.USER)
                .build();
        userRepository.save(userTest);
        List<String> allUserLogin = userRepository.findAll().stream()
                .map(UserEntity::getLogin)
                .toList();
        assertTrue(allUserLogin.contains(userTest.getLogin()));
    }

    @Test
    @Order(4)
    void whenDeleteById_ThenNotFindById() {
        Long id = 11L;
        Optional<UserEntity> user = userRepository.findById(id);
        userRepository.deleteById(user.get().getId());
        Optional<UserEntity> userDeleted = userRepository.findById(user.get().getId());
        assertTrue(userDeleted.isEmpty());
    }

    @Test
    @Order(5)
    void whenUpdateById_ThenSavedUserUpdate() {
        Long id = 14L;
        Optional<UserEntity> user = userRepository.findById(id);
        user.get().setLogin("Jhonny86");
        userRepository.save(user.get());
        assertEquals("Jhonny86", user.get().getLogin());
    }
}