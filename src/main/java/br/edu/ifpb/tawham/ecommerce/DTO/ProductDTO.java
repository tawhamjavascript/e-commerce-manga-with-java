package br.edu.ifpb.tawham.ecommerce.DTO;

public record ProductDTO(
    Long id, 
    String name,
    int chapter,
    String description,
    String genre,
    String author,
    String publication,
    double price,
    String image) {
    
}
