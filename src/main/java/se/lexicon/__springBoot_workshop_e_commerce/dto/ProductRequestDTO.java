package se.lexicon.__springBoot_workshop_e_commerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank(message = "Name is required.")
        @Size(max = 100, message = "Name cannot be longer than 100 characters.")
        String name,

        @NotNull(message = "Price is required.")
        @Positive(message = "Price must be positive.")
        BigDecimal price,

        @NotNull(message = "Category ID is required.")
        Long categoryId
) {
}
