package se.lexicon.__springBoot_workshop_e_commerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderItemRequestDTO(
        @NotNull(message = "Quantity is required.")
        @Positive
        int quantity,

        @Positive
        BigDecimal priceAtPurchase,

        @NotNull(message = "Customer ID is required.")
        Long orderId,

        @NotNull(message = "Product ID is required.")
        Long productId
) { }
