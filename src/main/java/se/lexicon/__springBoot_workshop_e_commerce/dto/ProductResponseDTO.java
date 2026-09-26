package se.lexicon.__springBoot_workshop_e_commerce.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        BigDecimal price,
        String categoryName
)
{ }
