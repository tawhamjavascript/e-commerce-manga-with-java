package br.edu.ifpb.tawham.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.tawham.ecommerce.DTO.ProductDTO;
import br.edu.ifpb.tawham.ecommerce.model.Product;
import br.edu.ifpb.tawham.ecommerce.repositories.ProductRepositoty;

@Service
public class ProductService {

    @Autowired
    private ProductRepositoty productRepository;

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            products.add(product.toDTO());
        });
        return products;
    } 
}
