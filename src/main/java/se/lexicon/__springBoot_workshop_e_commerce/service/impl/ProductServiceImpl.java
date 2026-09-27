package se.lexicon.__springBoot_workshop_e_commerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.__springBoot_workshop_e_commerce.dto.ProductRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.ProductResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Category;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Product;
import se.lexicon.__springBoot_workshop_e_commerce.exception.ResourceNotFoundException;
import se.lexicon.__springBoot_workshop_e_commerce.mapper.ProductMapper;
import se.lexicon.__springBoot_workshop_e_commerce.repository.CategoryRepository;
import se.lexicon.__springBoot_workshop_e_commerce.repository.ProductRepository;
import se.lexicon.__springBoot_workshop_e_commerce.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(CategoryRepository categoryRepository, ProductMapper productMapper, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO == null) {
            throw new IllegalArgumentException("Product request cannot be null.");
        }

        Category category = categoryRepository.findById(productRequestDTO.categoryId()).orElseThrow(()->new ResourceNotFoundException("Category is not found with ID: " + productRequestDTO.categoryId()));

        Product product = productMapper.toEntity(productRequestDTO, category);

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> productMapper.toResponse(product))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return List.of();
        }

        return productRepository.findByNameContaining(name)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
