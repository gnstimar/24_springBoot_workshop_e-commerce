package se.lexicon.__springBoot_workshop_e_commerce.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.__springBoot_workshop_e_commerce.dto.ProductRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.ProductResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@Validated
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.create(productRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> findByName(@RequestParam @NotBlank String name) {
        List<ProductResponseDTO> productResponseDTOs = productService.searchByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOs);
    }
}
