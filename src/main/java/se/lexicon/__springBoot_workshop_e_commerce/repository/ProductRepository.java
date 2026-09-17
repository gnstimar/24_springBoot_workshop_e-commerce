package se.lexicon.__springBoot_workshop_e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Category;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String categoryName);

    List<Product> findByPriceIsBetween(BigDecimal min, BigDecimal max);

    // === Optional ===
    List<Product> findByNameContaining(String word);

    List<Product> findByPriceIsLessThan(BigDecimal max);

    List<Product> findAllByOrderByPriceAsc();

    List<Product> findAllByOrderByPriceDesc();

    long countByCategoryName(String categoryName);

    List<Product> findByCategoryId(Long id);
}
