package se.lexicon.__springBoot_workshop_e_commerce.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OrderRequestDTO(
        @NotNull(message = "Customer ID is required.")
        Long customerId,

        @NotEmpty(message = "Order items list cannot be empty.")
        @Size(min = 1)
        List<OrderItemRequestDTO> items
) {
}
