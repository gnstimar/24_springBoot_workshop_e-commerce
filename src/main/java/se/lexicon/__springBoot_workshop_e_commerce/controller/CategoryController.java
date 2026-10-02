package se.lexicon.__springBoot_workshop_e_commerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CategoryResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Validated

@Tag(name = "Category Controller", description = "APIs for managing categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Operation(summary = "Create a new category.", description = "Add name and create a new category.")
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid String name) {
        CategoryResponseDTO categoryResponseDTO = categoryService.create(name);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDTO);
    }

    @GetMapping
    @Operation(summary = "List all categories.")
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

}
