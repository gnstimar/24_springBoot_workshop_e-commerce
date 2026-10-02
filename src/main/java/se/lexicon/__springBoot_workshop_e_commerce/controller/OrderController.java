package se.lexicon.__springBoot_workshop_e_commerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.__springBoot_workshop_e_commerce.dto.OrderRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.OrderResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@Valid

@Tag(name = "Order Controller", description = "APIs for managing orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(summary = "Place a new order.", description = "Create a new order with a customer ID and with a list of items.")
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.placeOrder(orderRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }
}
