package br.edu.ifpb.tawham.ecommerce.DTO;

import java.util.List;

public record ShoppingCartDTO(
    Long id,
    List<ProductDTO> products
) {
    
}
