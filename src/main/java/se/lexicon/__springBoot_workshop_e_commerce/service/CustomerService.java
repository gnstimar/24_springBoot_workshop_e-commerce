package se.lexicon.__springBoot_workshop_e_commerce.service;

import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.exception.ResourceNotFoundException;

import java.util.Optional;

public interface CustomerService {

    CustomerResponseDTO register(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO findById(Long id) throws ResourceNotFoundException;

    CustomerResponseDTO update(Long id, CustomerRequestDTO customerRequestDTO);

}
