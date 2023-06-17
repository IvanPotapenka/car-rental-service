package by.potapenko.database.dao;

import by.potapenko.database.ImporterClientDataTest;
import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.entity.ContactClient;
import by.potapenko.database.entity.DocumentEntity;
import by.potapenko.database.entity.enam.SeriesPassport;
import by.potapenko.database.hibernate.SessionBuilding;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientDaoTest {
    private static final ClientDao clientDao = ClientDao.getInstance();
    private static final SessionBuilding sessionFactory = SessionBuilding.getInstance();

    @BeforeAll
    static void beforeAll() {
        try (var session = sessionFactory.getSession()) {
            var transaction = session.beginTransaction();
            ImporterClientDataTest.clientDataTestImport(session);
            transaction.commit();
        }
    }

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllTheClientsAreReturned() {

        @Cleanup Session session = sessionFactory.getSession();
        int limit = 3;
        int page = 0;
        String[] actual = clientDao.findAll(limit, page, session)
                .stream()
                .map(ClientEntity::getFirstName)
                .toArray(String[]::new);
        String[] expected = List.of("Ivan", "Anna", "Egor")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void whenFindById_ThenAllTheFilteredReturnsValidClient() {
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        Optional<ClientEntity> actual = clientDao.findById(id, session);
        assertTrue(actual.isPresent());
        assertEquals("Ivan", actual.get().getFirstName());
    }

    @Test
    @Order(3)
    void whenCreatedInvokedWithClient_ThenClientIsSaved() {
        var clientTest = ClientEntity.builder()
                .firstName("Maksim")
                .lastName("Grishaev")
                .dateOfBirthday(LocalDate.parse("1981-12-02"))
                .contact(ContactClient.builder()
                        .phone("+375297659812")
                        .address("Gomel, Minskaia 18")
                        .build())
                .document(DocumentEntity.builder()
                        .series(SeriesPassport.AB)
                        .number(67547)
                        .driverLicense("347679")
                        .build())
                .build();

        @Cleanup Session session = sessionFactory.getSession();
        var transaction = session.beginTransaction();
        clientDao.create(clientTest, session);
        transaction.commit();
        int limit = 4;
        int page = 1;

        List<String> allBrand = clientDao.findAll(limit, page, session).stream()
                .map(ClientEntity::getFirstName)
                .toList();
        assertTrue(allBrand.contains(clientTest.getFirstName()));
    }

    @Test
    @Order(4)
    void whenDeleteById_ThenNotFindById() {
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        Optional<ClientEntity> client = clientDao.findById(id, session);
        clientDao.delete(client.get().getId(), session);
        Optional<ClientEntity> clientDeleted = clientDao.findById(client.get().getId(), session);
        assertTrue(clientDeleted.isEmpty());
    }

    @Test
    @Order(5)
    void whenUpdateById_ThenSavedClientUpdate() {
        Long id = 1L;
        @Cleanup Session session = sessionFactory.getSession();
        Optional<ClientEntity> client = clientDao.findById(id, session);
        client.get()
                .getContact()
                .setAddress("Gomel, Markelova, 26");
        clientDao.update(client.get(), session);
        assertEquals("Gomel, Markelova, 26", client.get().getContact().getAddress());
    }
}