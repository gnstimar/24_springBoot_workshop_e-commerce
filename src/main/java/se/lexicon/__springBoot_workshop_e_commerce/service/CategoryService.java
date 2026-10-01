package se.lexicon.__springBoot_workshop_e_commerce.service;

import se.lexicon.__springBoot_workshop_e_commerce.dto.CategoryRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO create(String name);

    List<CategoryResponseDTO> findAll();
}
