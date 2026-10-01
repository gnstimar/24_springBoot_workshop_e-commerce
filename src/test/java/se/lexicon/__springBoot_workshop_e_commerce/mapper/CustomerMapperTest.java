package se.lexicon.__springBoot_workshop_e_commerce.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Address;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Customer;
import org.assertj.core.api.Assertions.*;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.UserProfile;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMapperTest {

    CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = new CustomerMapper();
    }

    @Test
    @DisplayName("toResponse should give null when Customer is also null")
    void toResponseNullTest() {
        // Arrange
        Customer customerNull = null;

        // Act
        CustomerResponseDTO customerResponseDTONull = customerMapper.toResponse(customerNull);

        // Assert
        assertThat(customerResponseDTONull).isNull();
    }

    @Test
    @DisplayName("toResponse should correctly map a Customer to a CustomerResponseDTO")
    void toResponseTest() {
        // Arrange
        Customer customer = new Customer(1L, "Maria", "Larsson", "email@email.com", Instant.now(), null, null);
        Address address = new Address();
        address.setStreet("Storgatan 10.");
        address.setCity("Malmö");
        address.setZipCode("12345");
        customer.setAddress(address);

        // Act
        CustomerResponseDTO customerResponseDTO = customerMapper.toResponse(customer);

        // Assert
        assertThat(customerResponseDTO).isNotNull();
        assertThat(customerResponseDTO.id()).isEqualTo(1L);
        assertThat(customerResponseDTO.fullName()).isEqualTo("Maria Larsson");
        assertThat(customerResponseDTO.email()).isEqualTo("email@email.com");
        assertThat(customerResponseDTO.addressResponse()).isEqualTo("Storgatan 10., Malmö, 12345");
    }

    @Test
    @DisplayName("toEntity should give null when CustomerRequestDTO is also null")
    void toEntityNullTest() {
        // Arrange
        CustomerRequestDTO customerRequestDTONull = null;

        // Act
        Customer customerNull = customerMapper.toEntity(customerRequestDTONull);

        // Assert
        assertThat(customerNull).isNull();
    }

    @Test
    @DisplayName("toEntity should give correctly map CustomerRequestDTO to Customer")
    void toEntityTest() {
        // Arrange
        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO(
                "Maria",
                "Larsson",
                "email@email.com",
                "Storgatan 10.",
                "Malmö",
                "12345"
        );

        // Act
        Customer customer = customerMapper.toEntity(customerRequestDTO);

        // Assert
        assertThat(customer).isNotNull();
        assertThat(customer.getFirstName()).isEqualTo("Maria");
        assertThat(customer.getLastName()).isEqualTo("Larsson");
        assertThat(customer.getEmail()).isEqualTo("email@email.com");
        assertThat(customer.getAddress().getStreet()).isEqualTo("Storgatan 10.");
        assertThat(customer.getAddress().getCity()).isEqualTo("Malmö");
        assertThat(customer.getAddress().getZipCode()).isEqualTo("12345");
    }
}
