package se.lexicon.__springBoot_workshop_e_commerce.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.lexicon.__springBoot_workshop_e_commerce.dto.OrderItemRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.OrderRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.OrderResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Customer;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Order;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.OrderItem;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Product;
import se.lexicon.__springBoot_workshop_e_commerce.exception.CustomerNotFoundException;
import se.lexicon.__springBoot_workshop_e_commerce.exception.ResourceNotFoundException;
import se.lexicon.__springBoot_workshop_e_commerce.mapper.OrderMapper;
import se.lexicon.__springBoot_workshop_e_commerce.repository.CustomerRepository;
import se.lexicon.__springBoot_workshop_e_commerce.repository.OrderRepository;
import se.lexicon.__springBoot_workshop_e_commerce.repository.ProductRepository;
import se.lexicon.__springBoot_workshop_e_commerce.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository, OrderMapper orderMapper, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {
        if (orderRequestDTO == null) {
            throw new IllegalArgumentException("Order Request is required.");
        }

        Customer customer = customerRepository.findById(orderRequestDTO.customerId()).orElseThrow(() -> new CustomerNotFoundException());

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemRequestDTO orderItemRequestDto : orderRequestDTO.items()) {
            OrderItem newItem = new OrderItem();
            newItem.setQuantity(orderItemRequestDto.quantity());
            Product product = productRepository.findById(orderItemRequestDto.productId()).orElseThrow(() -> new ResourceNotFoundException("Product is not found."));
            newItem.setPriceAtPurchase(product.getPrice());
            newItem.setProduct(product);
            items.add(newItem);
        }

        // TODO: activate Promotions - will implement later

        Order order = orderMapper.toEntity(customer, items);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
    }
}
