package se.lexicon.__springBoot_workshop_e_commerce.service;

import se.lexicon.__springBoot_workshop_e_commerce.dto.OrderRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO);
}
