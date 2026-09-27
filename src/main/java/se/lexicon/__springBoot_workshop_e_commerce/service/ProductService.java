package se.lexicon.__springBoot_workshop_e_commerce.service;

import se.lexicon.__springBoot_workshop_e_commerce.dto.ProductRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO create (ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> findAll();

    List<ProductResponseDTO> searchByName(String name);
}
