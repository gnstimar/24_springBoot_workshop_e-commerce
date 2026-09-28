package se.lexicon.__springBoot_workshop_e_commerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequestDTO(
        @NotBlank(message = "First name is required.")
        @Size(max = 25, message = "First name cannot be longer than 25 characters.")
        String firstName,

        @NotBlank(message = "Last name is required.")
        @Size(max = 25, message = "Last name cannot be longer than 25 characters.")
        String lastName,

        @NotBlank(message = "Email is required.")
        @Size(max = 50, message = "Email cannot be longer than 50 characters.")
        @Email(
                regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
                message = "Email must be valid email format."
        )
        String email,

/*        @NotBlank(message = "Password is required.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d).{8,20}$",
                message = "Password needs at least one small letter, at least one capital letter, at least one digit and the minimum length of 8 characters and the maximum length of 20 characters."
        )
        String password,*/

        @NotBlank(message = "Street is required.")
        String street,

        @NotBlank(message = "City is required.")
        String city,

        @NotBlank(message = "Zip code is required.")
        @Size(max = 5, message = "Zip code cannot be longer than 5 characters in Sweden.")
        String zipCode)
{ }
