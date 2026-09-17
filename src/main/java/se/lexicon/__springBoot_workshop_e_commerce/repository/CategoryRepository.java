package se.lexicon.__springBoot_workshop_e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Optional<Category> findByNameIgnoreCase(String categoryName);

    boolean existsByName(String categoryName);

    // === Optional ===
    List<Category> findByNameContaining(String word);

    long count();
}
