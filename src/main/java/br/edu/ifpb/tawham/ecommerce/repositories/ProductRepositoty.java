package br.edu.ifpb.tawham.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.tawham.ecommerce.model.Product;

public interface ProductRepositoty extends JpaRepository<Product, Long>{
    

    
}
