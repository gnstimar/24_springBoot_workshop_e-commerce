package se.lexicon.__springBoot_workshop_e_commerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Product Controller", description = "APIs for managing products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Create a new product.")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.create(productRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }

    @GetMapping
    @Operation(summary = "List all products.")
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search")
    @Operation(summary = "Find a user by name.", description = "This will give back all products that contains this word.")
    @Parameter(description = "Piece of name of the product to be found", example = "recipe")
    public ResponseEntity<List<ProductResponseDTO>> findByName(@RequestParam @NotBlank String name) {
        List<ProductResponseDTO> productResponseDTOs = productService.searchByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOs);
    }
}
