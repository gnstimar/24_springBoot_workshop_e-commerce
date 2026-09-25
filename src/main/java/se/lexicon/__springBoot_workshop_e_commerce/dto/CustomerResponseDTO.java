package se.lexicon.__springBoot_workshop_e_commerce.dto;

public record CustomerResponseDTO(
        Long id,
        String fullName,
        String email,
        String addressResponse)
{ }
