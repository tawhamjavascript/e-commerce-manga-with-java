package br.edu.ifpb.tawham.ecommerce.DTO;

public record RegisterClientDTO(
    String name,
    String email,
    String password,
    String confirmPassword
) {
    public RegisterClientDTO {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (confirmPassword == null || confirmPassword.isBlank()) {
            throw new IllegalArgumentException("Confirm password cannot be null or blank");
        }
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password and confirm password must be equals");
        }
    }
    
}
