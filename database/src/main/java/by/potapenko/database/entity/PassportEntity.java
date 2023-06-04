package by.potapenko.database.entity;

import by.potapenko.database.entity.enam.SeriesPassport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Builder
@ToString(exclude = "client")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passport")
public class PassportEntity implements BaseIdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series", length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
    private SeriesPassport series;

    @Column(name = "numbers", length = 7, nullable = false, unique = true)
    private Integer number;

    @Column(name = "date_of_issue", nullable = false)
    private LocalDate dateOfIssue;

    @Column(name = "issued", length = 20, nullable = false)
    private String issued;

    @Column(name = "passport_id", length = 13, nullable = false, unique = true)
    private String passportID;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "user_id")
    private ClientEntity client;

}
