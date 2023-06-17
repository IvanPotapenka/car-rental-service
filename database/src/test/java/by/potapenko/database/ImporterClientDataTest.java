package by.potapenko.database;

import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.entity.ContactClient;
import by.potapenko.database.entity.DocumentEntity;
import by.potapenko.database.entity.enam.SeriesPassport;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.Arrays;

@UtilityClass
public class ImporterClientDataTest {

    public void clientDataTestImport(Session session) {

        var ivan = ClientEntity.builder()
                .firstName("Ivan")
                .lastName("Potapenko")
                .dateOfBirthday(LocalDate.parse("1986-05-02"))
                .contact(ContactClient.builder()
                        .phone("+375447347750")
                        .address("Gomel, Mazurova 23")
                        .build())
                .document(DocumentEntity.builder()
                        .series(SeriesPassport.AB)
                                .number(12234)
                                .driverLicense("12345679")
                                .build())
                .build();
        var anna = ClientEntity.builder()
                .firstName("Anna")
                .lastName("Kopp")
                .dateOfBirthday(LocalDate.parse("2000-06-02"))
                .contact(ContactClient.builder()
                        .phone("+375447658934")
                        .address("Gomel, Mazurova 21")
                        .build())
                .document(DocumentEntity.builder()
                                .series(SeriesPassport.AB)
                                .number(98765)
                                .driverLicense("09876543")
                                .build())
                .build();
        var egor = ClientEntity.builder()
                .firstName("Egor")
                .lastName("Siercov")
                .dateOfBirthday(LocalDate.parse("1988-04-17"))
                .contact(ContactClient.builder()
                        .phone("+375447659867")
                        .address("Gomel, Mazurova 20")
                        .build())
                .document(DocumentEntity.builder()
                                .series(SeriesPassport.AB)
                                .number(675443)
                                .driverLicense("34765767")
                                .build())
                .build();
        for (ClientEntity clientEntity : Arrays.asList(ivan, anna, egor)) {
            session.persist(clientEntity);
        }
    }
}


