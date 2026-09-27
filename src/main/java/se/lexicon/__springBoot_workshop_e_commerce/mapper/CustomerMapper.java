package se.lexicon.__springBoot_workshop_e_commerce.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Address;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Customer;

@Component
public class CustomerMapper {
    public CustomerResponseDTO toResponse(Customer customer) {
        if (customer == null) {
            return null;
        }

        String addressResponse = null;
        if (customer.getAddress() != null) {
            addressResponse = String.format("%s, %s, %s",
                    customer.getAddress().getStreet(),
                    customer.getAddress().getCity(),
                    customer.getAddress().getZipCode());
        }

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstName() + " " + customer.getLastName(),
                customer.getEmail(),
                addressResponse
        );
    }

    public Customer toEntity(CustomerRequestDTO customerRequestDTO) {
        if (customerRequestDTO == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setFirstName(customerRequestDTO.firstName());
        customer.setLastName(customerRequestDTO.lastName());
        customer.setEmail(customerRequestDTO.email());

        Address address = new Address();
        address.setStreet(customerRequestDTO.street());
        address.setCity(customerRequestDTO.city());
        address.setZipCode(customerRequestDTO.zipCode());
        customer.setAddress(address);

        return customer;
    }
}
