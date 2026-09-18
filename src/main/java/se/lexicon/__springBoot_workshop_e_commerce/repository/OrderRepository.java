package se.lexicon.__springBoot_workshop_e_commerce.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Order;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.OrderStatus;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer_Id(Long customerId);

    // TODO Find orders by status and use a strategy to avoid the N+1 problem (loading order items in the same query).
    // EntityGraph
    /*@EntityGraph(attributePaths = {"items"})
    List<Order> findByOrderStatus(OrderStatus orderStatus);*/
    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.items WHERE o.orderStatus = :status")
    List<Order> findByOrderStatusWithItems(@Param("status") OrderStatus orderStatus);

    // === Optional ===
    List<Order> findByOrderDateAfter(Instant date);

    List<Order> findByOrderDateBetween(Instant orderDateAfter, Instant orderDateBefore);

    // Find orders that contain a specific product.
    List<Order> findByItems_ProductId(Long productId);

    long countByOrderStatus(OrderStatus orderStatus);

    List<Order> findByCustomerIdAndOrderStatus(Long customerId, OrderStatus orderStatus);
}
