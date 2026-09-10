package se.lexicon.__springBoot_workshop_e_commerce.entitiy;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "USER_PROFILES")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nickname", nullable = false, length = 100)
    private String nickname;

    @Column(name = "phone_number", nullable = false, length = 100)
    private String phoneNumber;

    @Column(length = 500)
    private String bio;

    @OneToOne(mappedBy = "userProfile")
    @ToString.Exclude
    private Customer customer;
}
