package se.lexicon.__springBoot_workshop_e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository {

    @Query("SELECT p FROM Promotion p WHERE :date BETWEEN p.startDate AND p.endDate")
    List<Promotion> findActivePromotionsOnDate(@Param("date") LocalDate date);

    // === Optional ===
    Optional<Promotion> findByCode(String code);

    List<Promotion> findByStartDateAfter(LocalDate date);

    List<Promotion> findByEndDateBefore(LocalDate date);

    List<Promotion> findByEndDateIsNull();

    @Query("SELECT p FROM Promotion p WHERE CURRENT_DATE BETWEEN p.startDate AND p.endDate")
    List<Promotion> findAllActiveToday();
}
