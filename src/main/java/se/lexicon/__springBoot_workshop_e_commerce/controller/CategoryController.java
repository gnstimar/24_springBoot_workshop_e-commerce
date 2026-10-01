package se.lexicon.__springBoot_workshop_e_commerce.controller;

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
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid String name) {
        CategoryResponseDTO categoryResponseDTO = categoryService.create(name);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

}
