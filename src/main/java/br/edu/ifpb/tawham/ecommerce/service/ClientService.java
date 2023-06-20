package br.edu.ifpb.tawham.ecommerce.service;
import org.springframework.stereotype.Service;

import br.edu.ifpb.tawham.ecommerce.model.Client;
import br.edu.ifpb.tawham.ecommerce.model.ResponseLogin;
import br.edu.ifpb.tawham.ecommerce.model.ShoppingCartView;
import br.edu.ifpb.tawham.ecommerce.repositories.ClientRepository;
import br.edu.ifpb.tawham.ecommerce.repositories.ShoppingCartRepository;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void registerClient(Client client) {
        clientRepository.save(client);
    }

    public ResponseLogin login(String email, String password) {
        Client client = clientRepository.findByEmailAndPassword(email, password);
        if (client != null) {
            return new ResponseLogin(client.getId());
        } 
        return null;
    }

    public ShoppingCartView getShoppingCart(Long id) {
        shoppingCartRepository.allProductsInShoppingCart(id);

    }

    
}


