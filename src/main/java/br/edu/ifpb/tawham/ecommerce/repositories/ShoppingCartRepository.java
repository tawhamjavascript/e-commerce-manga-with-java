package br.edu.ifpb.tawham.ecommerce.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.edu.ifpb.tawham.ecommerce.model.ShoppingCart;


public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{

    @Query("SELECT sc FROM Client c JOIN c.shoppingCart sc where c.id = :id")
    public ShoppingCart getShoppingCart(@Param("id") Long id);
    
}
