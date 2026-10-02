package se.lexicon.__springBoot_workshop_e_commerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Customer Controller", description = "APIs for managing customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Register a new customer.", description = "Register a new customer if the email is not already taken.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body provided")
    })
    public ResponseEntity<CustomerResponseDTO> register(@RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.register(customerRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a customer by ID.")
    @Parameter(description = "ID of the customer to be retrieved", example = "1")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable @Positive Long id) {
        CustomerResponseDTO customerResponseDTO = customerService.findById(id);

        return ResponseEntity.ok(customerResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a customer by ID.", description = "It is possible to change the name for example.")
    @Parameter(description = "ID of the customer to be updated", example = "1")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable @Positive Long id, @RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.update(id, customerRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(customerResponseDTO);
    }
}
