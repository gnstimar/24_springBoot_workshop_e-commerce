package se.lexicon.__springBoot_workshop_e_commerce;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Category;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Product;
import se.lexicon.__springBoot_workshop_e_commerce.repository.CategoryRepository;
import se.lexicon.__springBoot_workshop_e_commerce.repository.ProductRepository;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataInitializer(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            seedData();
        }
    }

    private void seedData() {
        Category electronics = new Category();
        electronics.setName("Electronics");

        Category books = new Category();
        books.setName("Books");

        categoryRepository.save(electronics);
        categoryRepository.save(books);

        Product laptop = new Product();
        laptop.setName("Lenovo Laptop");
        laptop.setPrice(new BigDecimal("12000.00"));
        laptop.setCategory(electronics);

        Product recipeBook = new Product();
        recipeBook.setName("Nordic recipes");
        recipeBook.setPrice(new BigDecimal("45.00"));
        recipeBook.setCategory(books);

        productRepository.save(laptop);
        productRepository.save(recipeBook);

        IO.println("Data seeding is successfully done.");
    }
}
