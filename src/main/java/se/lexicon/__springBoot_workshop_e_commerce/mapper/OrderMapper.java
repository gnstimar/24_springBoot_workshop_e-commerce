package se.lexicon.__springBoot_workshop_e_commerce.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.__springBoot_workshop_e_commerce.dto.OrderItemResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.OrderResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Customer;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Order;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    public OrderResponseDTO toResponse(Order order) {
        if (order == null) {
            return null;
        }

        List<OrderItemResponseDTO> orderItemDTOs = new ArrayList<>();
        if (order.getItems() != null) {
            orderItemDTOs= order.getItems().stream().map(
                    item -> new OrderItemResponseDTO(
                            item.getId(),
                            item.getQuantity(),
                            item.getPriceAtPurchase(),
                            item.getOrder().getId(),
                            item.getProduct().getId()
                    )).toList();
        }

        return new OrderResponseDTO(
                order.getId(),
                order.getOrderDate(),
                order.getOrderStatus(),
                order.getCustomer() != null ? order.getCustomer().getId() : null,
                orderItemDTOs
        );
    }

    public Order toEntity(Customer customer, List<OrderItem> items) {
        Order order = new Order();
        order.setCustomer(customer);

        if (items != null) {
            for (OrderItem item : items) {
                order.addItem(item);
            }
        }
        return order;
    }
}
