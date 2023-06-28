package br.edu.ifpb.tawham.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.tawham.ecommerce.model.Checkout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CheckoutRepository extends JpaRepository<Checkout, Long>{
    @Query("SELECT ch FROM CLIENT c JOIN c.checkouts ch WHERE c.id = :id")
    public List<Checkout> getCheckouts(@Param("id") Long id);

    
}
