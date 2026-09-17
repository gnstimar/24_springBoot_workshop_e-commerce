package se.lexicon.__springBoot_workshop_e_commerce.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"customer", "items"})
@EqualsAndHashCode(exclude = {"customer", "items"})

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date", nullable = false, updatable = false)
    private Instant orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    // no JoinColumn because there is no column in Orders table in the db
    private List<OrderItem> items = new ArrayList<>();

    // Helper methods for the business logic
    public void addItem(OrderItem item) {
        items.add(item);
        // we need to set in the item which order it belongs to
        item.setOrder(this);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
    }

    // Business logic: check before saving
    @PrePersist
    @PreUpdate
    public void validateOrderItems() {
        if (items == null || items.isEmpty()) {
            throw new IllegalStateException("An Order must contain at least one OrderItem before it is saved.");
        }
        if (this.orderDate == null) {
            this.orderDate = Instant.now();
        }
    }
}
