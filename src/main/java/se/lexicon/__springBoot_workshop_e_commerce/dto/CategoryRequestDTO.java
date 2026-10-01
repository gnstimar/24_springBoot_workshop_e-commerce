package se.lexicon.__springBoot_workshop_e_commerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {

    @NotBlank(message = "Category name is required.")
    @Size(max = 100, message = "Category name cannot be longer than 100 characters.")
    String name;
}
