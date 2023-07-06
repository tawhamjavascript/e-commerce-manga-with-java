package br.edu.ifpb.tawham.ecommerce.DTO;

import org.hibernate.validator.constraints.URL;

import br.edu.ifpb.tawham.ecommerce.model.Product;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDTO(
    Long id, 
    @NotBlank(message = "name cannot be empty") @NotNull(message = "name is necessary to register product") String name,
    @Positive(message = "Chapter need be bigger than zero") int chapter,
    @NotBlank(message = "description cannot be empty") @NotNull(message = "description is necessary to register product") String description,
    @NotBlank(message = "genre cannot be empty") @NotNull(message = "genre is necessary to register product")String genre,
    @NotBlank(message = "author cannot be empty") @NotNull(message = "author is necessary to register product")String author,
    @NotBlank(message = "publication cannot be empty") @NotNull(message = "publication is necessary to register product")String publication,
    @Positive(message = "price need be bigger than zero") double price,
    @URL String image) {
    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getChapter(), 
            product.getDescription(), product.getGenre(), product.getAuthor(), 
            product.getPublication(), product.getPrice(), product.getImage()
        );
    }  
}
