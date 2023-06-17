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
@Table(name = "documents")
public class DocumentEntity implements BaseIdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passport_serial", length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
    private SeriesPassport series;

    @Column(name = "passport_No", nullable = false, unique = true)
    private Integer number;

    @Column(name = "driver_license_No", nullable = false)
    private String driverLicense;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

}
