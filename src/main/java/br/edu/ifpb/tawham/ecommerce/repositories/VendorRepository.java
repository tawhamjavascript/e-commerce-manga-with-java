package br.edu.ifpb.tawham.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifpb.tawham.ecommerce.model.ProductSold;
import br.edu.ifpb.tawham.ecommerce.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByEmailAndPassword(String email, String password);

    @Query("SELECT ps FROM Vendor v JOIN v.solds s JOIN s.productsSold ps WHERE v.id = :id")
    public List<ProductSold> getProductsSold(@Param("id") Long id); 
    
}
