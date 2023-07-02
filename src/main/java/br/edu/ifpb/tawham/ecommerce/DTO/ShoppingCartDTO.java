package br.edu.ifpb.tawham.ecommerce.DTO;

import java.util.List;

import br.edu.ifpb.tawham.ecommerce.model.ShoppingCart;

public record ShoppingCartDTO(
    Long id,
    List<ProductDTO> products
) {
    public ShoppingCartDTO (ShoppingCart shoppingCart){
        this(shoppingCart.getId(), shoppingCart.getProducts().stream().map(ProductDTO::new).toList());

    }
    
}
