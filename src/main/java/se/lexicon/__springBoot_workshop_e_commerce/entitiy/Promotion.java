package se.lexicon.__springBoot_workshop_e_commerce.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"products"})
@EqualsAndHashCode(exclude = {"products"})

@Entity
@Table(name = "PROMOTIONS")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    /*If we want bidirectional relationship
    @ManyToMany(mappedBy = "promotions", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
     */
}
