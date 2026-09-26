package se.lexicon.__springBoot_workshop_e_commerce.dto;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        int quantity,
        BigDecimal priceAtPurchase,
        Long orderId,
        Long productId
) { }
