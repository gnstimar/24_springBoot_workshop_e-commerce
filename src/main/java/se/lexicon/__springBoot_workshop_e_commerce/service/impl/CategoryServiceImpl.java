package se.lexicon.__springBoot_workshop_e_commerce.service.impl;

import org.springframework.stereotype.Service;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CategoryRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CategoryResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Category;
import se.lexicon.__springBoot_workshop_e_commerce.mapper.CategoryMapper;
import se.lexicon.__springBoot_workshop_e_commerce.repository.CategoryRepository;
import se.lexicon.__springBoot_workshop_e_commerce.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponseDTO create(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty.");
        }

        Category category = new Category();
        category.setName(name);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> categoryMapper.toResponse(category))
                .toList();
    }
}
