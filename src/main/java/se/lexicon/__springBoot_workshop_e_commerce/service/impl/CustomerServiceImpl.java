package se.lexicon.__springBoot_workshop_e_commerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Address;
import se.lexicon.__springBoot_workshop_e_commerce.entitiy.Customer;
import se.lexicon.__springBoot_workshop_e_commerce.exception.CustomerNotFoundException;
import se.lexicon.__springBoot_workshop_e_commerce.exception.DuplicateEntryException;
import se.lexicon.__springBoot_workshop_e_commerce.mapper.CustomerMapper;
import se.lexicon.__springBoot_workshop_e_commerce.repository.CustomerRepository;
import se.lexicon.__springBoot_workshop_e_commerce.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public CustomerResponseDTO register(CustomerRequestDTO customerRequestDTO) {
        if (customerRequestDTO == null) {
            throw new IllegalArgumentException("Customer request cannot be null.");
        }

        if (customerRepository.findByEmail(customerRequestDTO.email()).isPresent()) {
            throw new DuplicateEntryException("Email is already registered.");
        }

        Customer customer = customerMapper.toEntity(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO findById(Long id) throws CustomerNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }

        //return customerRepository.findById(id).map(customerMapper::toResponse);
        return customerRepository.findById(id)
                .map(customer -> customerMapper.toResponse(customer))
                .orElseThrow(()->new CustomerNotFoundException("Customer not found with ID: " + id));
    }

    @Override
    @Transactional
    public CustomerResponseDTO update(Long id, CustomerRequestDTO customerRequestDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Customer ID cannot be null for update.");
        }
        if (customerRequestDTO == null) {
            throw new IllegalArgumentException("Customer Request cannot be null for update.");
        }

        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer is not found with ID: "+ id));

        customer.setFirstName(customerRequestDTO.firstName());
        customer.setLastName(customerRequestDTO.lastName());
        customer.setEmail(customerRequestDTO.email());
        Address address = new Address();
        address.setStreet(customerRequestDTO.street());
        address.setCity(customerRequestDTO.city());
        address.setZipCode(customerRequestDTO.zipCode());
        customer.setAddress(address);

        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(updatedCustomer);
    }
}
