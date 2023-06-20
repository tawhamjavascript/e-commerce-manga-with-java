package br.edu.ifpb.tawham.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.tawham.ecommerce.model.Product;
import br.edu.ifpb.tawham.ecommerce.model.ProductView;
import br.edu.ifpb.tawham.ecommerce.repositories.ProductRepositoty;

@Service
public class ProductService {

    @Autowired
    private ProductRepositoty productRepository;

    public List<ProductView> getAllProducts() {
        List<ProductView> products = new ArrayList<>();
        for(Product product : productRepository.findAll()) {
            products.add(new ProductView(
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
        return products;
    } 
}
