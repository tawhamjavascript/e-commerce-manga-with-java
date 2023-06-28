package br.edu.ifpb.tawham.ecommerce.DTO;

public record LoginDTO(String email, String password) {
    public LoginDTO {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }
    
}
