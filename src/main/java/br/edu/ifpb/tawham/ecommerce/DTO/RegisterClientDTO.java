package br.edu.ifpb.tawham.ecommerce.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterClientDTO(
    @NotBlank(message = "name cannot be empty") @NotNull(message = "name is necessary to register product") String name,
    @Email String email,
    @NotBlank(message = "password cannot be empty") @NotNull(message = "password is necessary to register product") String password,
    @NotBlank(message = "Confirm password cannot be empty") @NotNull(message = "Confirm password is necessary to register product") String confirmPassword
) {
    public RegisterClientDTO {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password and confirm password must be equals");
        }
    }
    
}
