package by.potapenko.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@ToString(exclude = "orders")
@EqualsAndHashCode(exclude = "orders", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class ClientEntity extends CreatableEntity<UserEntity>{
    @Id
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @Column(name = "middle_name", length = 20)
    private String middleName;

    @Column(name = "date_of_birthday")
    private LocalDate dateOfBirthday;

    @Embedded
    private Contact contact;

    @Embedded
    private Document document;

    @Builder.Default
    @OneToMany(mappedBy = "client")
    private List<RentalEntity> orders = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "car_client",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<CarEntity> cars = new ArrayList<>();

    @Override
    public UserEntity getId() {
        return null;
    }

    @Override
    public void setId(UserEntity id) {

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Embeddable
    public static class Contact {

        @Column(name = "phone", length = 30, nullable = false, unique = true)
        private String phone;

        @Column(name = "address", length = 100)
        private String address;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Embeddable
    public static class Document {

        @Column(name = "driver_license", length = 30, nullable = false, unique = true)
        private String driverLicense;

        @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
        private PassportEntity passport;

    }

    public void setPassport(PassportEntity passport) {
        this.getDocument().passport = passport;
        passport.setClient(this);
    }

    public void addOrder(RentalEntity rental) {
        this.getOrders().add(rental);
        rental.setClient(this);
    }

    public void addCar(CarEntity car) {
        this.getCars().add(car);
        car.setClients(Collections.singletonList(this));
    }
}
