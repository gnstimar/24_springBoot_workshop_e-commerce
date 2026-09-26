package se.lexicon.__springBoot_workshop_e_commerce.dto;

import se.lexicon.__springBoot_workshop_e_commerce.entitiy.OrderItem;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        Instant orderDate,
        OrderStatus orderStatus,
        Long customerId,
        List<OrderItemResponseDTO> items
) { }
