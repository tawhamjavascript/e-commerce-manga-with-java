package br.edu.ifpb.tawham.ecommerce.DTO;

import br.edu.ifpb.tawham.ecommerce.model.Product;

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
    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getChapter(), 
            product.getDescription(), product.getGenre(), product.getAuthor(), 
            product.getPublication(), product.getPrice(), product.getImage()
        );
    }  
}
