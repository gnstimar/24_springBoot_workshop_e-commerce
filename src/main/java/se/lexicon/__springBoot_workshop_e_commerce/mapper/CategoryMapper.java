package se.lexicon.__springBoot_workshop_e_commerce.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CategoryResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Category;

@Component
public class CategoryMapper {
    public CategoryResponseDTO toResponse(Category category) {
        if (category == null) {
            return null;
        }
         return new CategoryResponseDTO(
                 category.getId(),
                 category.getName()
         );
    }
}
