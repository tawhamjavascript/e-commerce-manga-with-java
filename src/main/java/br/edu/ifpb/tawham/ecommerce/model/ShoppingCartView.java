package br.edu.ifpb.tawham.ecommerce.model;

import java.util.ArrayList;

public class ShoppingCartView {
    private Long id;
    ArrayList<ProductView> products = new ArrayList<ProductView>();

    public ShoppingCartView() { /* TODO document why this constructor is empty */ }
    public ShoppingCartView(Long id, ArrayList<ProductView> products) {
        this.id = id;
        this.products = products;
    }
    

    
}
