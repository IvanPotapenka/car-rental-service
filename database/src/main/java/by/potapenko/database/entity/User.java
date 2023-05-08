package by.potapenko.database.entity;


import by.potapenko.database.enam.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private Contact contact;
}

