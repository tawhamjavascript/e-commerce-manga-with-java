package br.edu.ifpb.tawham.ecommerce.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifpb.tawham.ecommerce.model.Client;
import br.edu.ifpb.tawham.ecommerce.model.Product;


public interface ClientRepository extends JpaRepository<Client, Long>{
    public Client findByEmailAndPassword(String email, String password);
    

    

    // TODO implement this class
}