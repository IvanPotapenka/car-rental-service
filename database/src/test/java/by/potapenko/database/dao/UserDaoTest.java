package by.potapenko.database.dao;

import by.potapenko.database.ImporterUserDataTest;
import by.potapenko.database.entity.UserEntity;
import by.potapenko.database.entity.enam.UserRole;
import by.potapenko.database.hibernate.SessionBuilding;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDaoTest {
    private static final UserDao userDao = UserDao.getInstance();
    private static final SessionBuilding sessionFactory = SessionBuilding.getInstance();

    @BeforeAll
    static void beforeAll() {
        try (var session = sessionFactory.getSession()) {
            var transaction = session.beginTransaction();
            ImporterUserDataTest.userDataTestImport(session);
            transaction.commit();
        }
    }

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllTheClientsAreReturned() {

        @Cleanup Session session = sessionFactory.getSession();
        int limit = 3;
        int page = 0;
        String[] actual = userDao.findAll(limit, page, session)
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
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        Optional<UserEntity> actual = userDao.findById(id, session);
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

        @Cleanup Session session = sessionFactory.getSession();
        var transaction = session.beginTransaction();
        userDao.create(userTest, session);
        transaction.commit();
        int limit = 4;
        int page = 1;

        List<String> allUserLogin = userDao.findAll(limit, page, session).stream()
                .map(UserEntity::getLogin)
                .toList();
        assertTrue(allUserLogin.contains(userTest.getLogin()));
    }

    @Test
    @Order(4)
    void whenDeleteById_ThenNotFindById() {
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        Optional<UserEntity> user = userDao.findById(id, session);
        userDao.delete(user.get().getId(), session);
        Optional<UserEntity> userDeleted = userDao.findById(user.get().getId(), session);
        assertTrue(userDeleted.isEmpty());
    }

    @Test
    @Order(5)
    void whenUpdateById_ThenSavedUserUpdate() {
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        Optional<UserEntity> user = userDao.findById(id, session);
        user.get().setLogin("Jhonny86");
        userDao.update(user.get(), session);
        assertEquals("Jhonny86", user.get().getLogin());
    }
}