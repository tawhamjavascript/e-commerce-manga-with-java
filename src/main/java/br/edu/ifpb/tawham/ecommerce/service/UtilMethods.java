package br.edu.ifpb.tawham.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.tawham.ecommerce.model.Product;
import br.edu.ifpb.tawham.ecommerce.model.ProductView;

public class UtilMethods {

    public List<ProductView> convertProductForProductsView(List<Product> products) {
        List<ProductView> productsView = new ArrayList<>();
        for(Product product : products) {
            productsView.add(new ProductView(
                product.getId(),
                product.getName(),
                product.getChapter(),
                product.getDescription(),
                product.getGenre(),
                product.getAuthor(),
                product.getPublication(),
                product.getPrice(),
                product.getImage()
            ));
        }
        return productsView;
    }
    
}
