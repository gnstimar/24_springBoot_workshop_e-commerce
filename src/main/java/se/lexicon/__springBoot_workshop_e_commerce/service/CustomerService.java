package se.lexicon.__springBoot_workshop_e_commerce.service;

import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.exception.CustomerNotFoundException;
import se.lexicon.__springBoot_workshop_e_commerce.exception.ResourceNotFoundException;

public interface CustomerService {

    CustomerResponseDTO register(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO findById(Long id) throws CustomerNotFoundException;

    CustomerResponseDTO update(Long id, CustomerRequestDTO customerRequestDTO);

}
