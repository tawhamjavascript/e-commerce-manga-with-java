package br.edu.ifpb.tawham.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.tawham.ecommerce.model.Checkout;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CheckoutRepository extends JpaRepository<Checkout, Long>{
    @Query("SELECT ch FROM Client c JOIN c.checkouts ch where c.id = :id")
    public List<Checkout> getCheckoutsClient(@Param("id") Long id);

    @Query("SELECT sl FROM Vendor v JOIN v.solds sl where v.id = :id")
    public List<Checkout> getSoldsVendor(@Param("id") Long id);
}
