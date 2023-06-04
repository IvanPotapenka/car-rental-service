package by.potapenko.database;

import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.entity.PassportEntity;
import by.potapenko.database.entity.UserEntity;
import by.potapenko.database.entity.enam.SeriesPassport;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.Arrays;

@UtilityClass
public class ImporterClientDataTest {

    public void clientDataTestImport(Session session) {

        var ivan = ClientEntity.builder()
                .user(UserEntity.builder()
                        .id(1L)
                        .build())
                .firstName("Ivan")
                .lastName("Potapenko")
                .middleName("Yrevich")
                .dateOfBirthday(LocalDate.parse("1986-05-02"))
                .contact(ClientEntity.Contact.builder()
                        .phone("+375447347750")
                        .address("Gomel, Mazurova 23")
                        .build())
                .document(ClientEntity.Document.builder()
                        .passport(PassportEntity.builder()
                                .series(SeriesPassport.AB)
                                .issued("ROVD")
                                .dateOfIssue(LocalDate.parse("2023-01-01"))
                                .number(12234)
                                .passportID("123456789")
                                .build())
                        .driverLicense("12345")
                        .build())

                .build();
        var anna = ClientEntity.builder()
                .user(UserEntity.builder()
                        .id(2L)
                        .build())
                .firstName("Anna")
                .lastName("Kopp")
                .middleName("")
                .dateOfBirthday(LocalDate.parse("2000-06-02"))
                .contact(ClientEntity.Contact.builder()
                        .phone("+375447658934")
                        .address("Gomel, Mazurova 21")
                        .build())
                .document(ClientEntity.Document.builder()
                        .passport(PassportEntity.builder()
                                .series(SeriesPassport.AB)
                                .issued("ROVD")
                                .dateOfIssue(LocalDate.parse("2023-01-01"))
                                .number(98765)
                                .passportID("0987654321")
                                .build())
                        .driverLicense("54321")
                        .build())
                .build();
        var egor = ClientEntity.builder()
                .user(UserEntity.builder()
                        .id(3L)
                        .build())
                .firstName("Egor")
                .lastName("Siercov")
                .middleName("Nikolaevich")
                .dateOfBirthday(LocalDate.parse("1988-04-17"))
                .contact(ClientEntity.Contact.builder()
                        .phone("+375447659867")
                        .address("Gomel, Mazurova 20")
                        .build())
                .document(ClientEntity.Document.builder()
                        .passport(PassportEntity.builder()
                                .series(SeriesPassport.AB)
                                .issued("ROVD")
                                .dateOfIssue(LocalDate.parse("2023-01-01"))
                                .number(675443)
                                .passportID("3476576709")
                                .build())
                        .driverLicense("098987")
                        .build())
                .build();

        for (ClientEntity clientEntity : Arrays.asList(ivan, anna, egor)) {
            session.persist(clientEntity);
        }
    }
}


