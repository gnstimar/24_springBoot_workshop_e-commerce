package se.lexicon.__springBoot_workshop_e_commerce.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.__springBoot_workshop_e_commerce.dto.ProductRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.ProductResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Category;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Product;

@Component
public class ProductMapper {
    public ProductResponseDTO toResponse(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory() != null ? product.getCategory().getName() : null
        );
    }

    public Product toEntity(ProductRequestDTO productRequestDTO, Category category) {
        if (productRequestDTO == null) {
            return null;
        }
        Product product = new Product();
        product.setName(productRequestDTO.name());
        product.setPrice(productRequestDTO.price());
        product.setCategory(category);

        return product;
    }
}
