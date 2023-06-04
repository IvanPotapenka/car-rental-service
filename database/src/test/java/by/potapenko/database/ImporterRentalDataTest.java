package by.potapenko.database;

import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.entity.NoElectricCarEntity;
import by.potapenko.database.entity.RentalEntity;
import by.potapenko.database.entity.UserEntity;
import by.potapenko.database.entity.enam.Status;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.Arrays;

@UtilityClass
public class ImporterRentalDataTest {

    public void rentalDataTestImport(Session session) {

        var orderOne = RentalEntity.builder()
                .car(NoElectricCarEntity.builder()
                        .id(1L)
                        .build())
                .client(ClientEntity.builder()
                        .user(UserEntity.builder()
                                .id(1L)
                                .build())
                        .build())
                .rentalDate(LocalDate.parse("2023-05-29"))
                .returnDate(LocalDate.parse("2023-05-31"))
                .rentalDays(2)
                .price(100)
                .status(Status.REJECTED)
                .build();
        var orderTwo = RentalEntity.builder()
                .car(NoElectricCarEntity.builder()
                        .id(2L)
                        .build())
                .client(ClientEntity.builder()
                        .user(UserEntity.builder()
                                .id(1L)
                                .build())
                        .build())
                .rentalDate(LocalDate.parse("2023-12-27"))
                .returnDate(LocalDate.parse("2023-12-31"))
                .rentalDays(4)
                .price(440)
                .status(Status.CHECK)
                .build();
        var orderThree = RentalEntity.builder()
                .car(NoElectricCarEntity.builder()
                        .id(3L)
                        .build())
                .client(ClientEntity.builder()
                        .user(UserEntity.builder()
                                .id(1L)
                                .build())
                        .build())
                .rentalDate(LocalDate.parse("2022-02-25"))
                .returnDate(LocalDate.parse("2022-02-28"))
                .rentalDays(3)
                .price(110)
                .status(Status.ALLOWED)
                .build();

        for (RentalEntity rentalEntity : Arrays.asList(orderOne, orderTwo, orderThree)) {
            session.persist(rentalEntity);
        }
    }
}


