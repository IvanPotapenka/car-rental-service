package by.potapenko.database.entity;


import by.potapenko.database.enam.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString(exclude = "client")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends CreatableEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "logins", length = 50, nullable = false)
    private String login;


    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;


    @Column(name = "passwords", length = 50, nullable = false)
    private String password;

    @Builder.Default
    @Column(name = "roles", length = 7, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @OneToOne(mappedBy = "user")
    private ClientEntity client;

//    @Builder.Default
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<RentalEntity> orders = new ArrayList<>();

}

