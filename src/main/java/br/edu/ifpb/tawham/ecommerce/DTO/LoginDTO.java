package br.edu.ifpb.tawham.ecommerce.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
    @NotBlank(message = "Email is mandatory") @Email(message = "Email is not valid") String email, 
    @Size(min = 8, max = 50, message = "About Me must be between 10 and 200 characters") String password
    ) {

 
}
