package by.potapenko.database.repositpry;

import by.potapenko.database.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Long> {

   List<RentalEntity> findByClient_Orders(Long id);
   List<RentalEntity> findByCar_Orders(Long id);
}
