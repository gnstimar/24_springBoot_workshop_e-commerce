package se.lexicon.__springBoot_workshop_e_commerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDTO(
        @NotNull(message = "Customer ID is required.")
        Long customerId,

        @NotEmpty(message = "Order items list cannot be empty.")
        @Min(1)
        List<OrderItemRequestDTO> items
) {
}
