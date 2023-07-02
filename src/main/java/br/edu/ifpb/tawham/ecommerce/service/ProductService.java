package br.edu.ifpb.tawham.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.tawham.ecommerce.DTO.ProductDTO;
import br.edu.ifpb.tawham.ecommerce.model.Product;
import br.edu.ifpb.tawham.ecommerce.repositories.ProductRepositoty;
import jakarta.transaction.Transactional;

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

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).get();
        return product.toDTO();
    }

    @Transactional
    public void editProduct(ProductDTO productDTO){
        Product product = productRepository.findById(productDTO.id()).get();
        product.setName(productDTO.name());
        product.setChapter(productDTO.chapter());
        product.setDescription(productDTO.description());
        product.setGenre(productDTO.genre());
        product.setAuthor(productDTO.author());
        product.setPublication(productDTO.publication());
        product.setPrice(productDTO.price());
        product.setImage(productDTO.image());

    }

    @Transactional
    public void deleteProduct(Long id){
        productRepository.findById(id).ifPresent(product -> {
            product.getVendor().removeProduct(product);
            product.setVendor(null);
            product.getShoppingCarts().forEach(shoppingCart -> {
                shoppingCart.removeProduct(product);
            });
            product.removeAllShoppingCarts();
            productRepository.delete(product);
        });
    }
}
