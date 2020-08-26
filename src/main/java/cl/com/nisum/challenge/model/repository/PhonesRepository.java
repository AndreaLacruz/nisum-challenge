package cl.com.nisum.challenge.model.repository;

import cl.com.nisum.challenge.model.entity.Phones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("PhonesRepository")
@Repository
public interface PhonesRepository extends JpaRepository<Phones, Long> {

    @Modifying
    @Query(value = "ALTER TABLE User AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
