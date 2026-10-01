package se.lexicon.__springBoot_workshop_e_commerce.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerRequestDTO;
import se.lexicon.__springBoot_workshop_e_commerce.dto.CustomerResponseDTO;
import se.lexicon.__springBoot_workshop_e_commerce.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> register(@RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.register(customerRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable @Positive Long id) {
        CustomerResponseDTO customerResponseDTO = customerService.findById(id);

        return ResponseEntity.ok(customerResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable @Positive Long id, @RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.update(id, customerRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(customerResponseDTO);
    }
}
