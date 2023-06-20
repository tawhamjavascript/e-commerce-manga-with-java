package br.edu.ifpb.tawham.ecommerce.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.tawham.ecommerce.model.Client;
import br.edu.ifpb.tawham.ecommerce.model.Login;
import br.edu.ifpb.tawham.ecommerce.model.ProductView;
import br.edu.ifpb.tawham.ecommerce.model.ResponseLogin;
import br.edu.ifpb.tawham.ecommerce.service.ClientService;
import br.edu.ifpb.tawham.ecommerce.service.ProductService;

@RestController
@RequestMapping("/client")

public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerClient(@RequestBody Client client) {
        clientService.registerClient(client);
        URI uri = URI.create("/client/" + client.getId());
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseLogin login(@RequestBody Login login) {
        return clientService.login(login.getEmail(), login.getPassword());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductView>> getAllProducts() {

        List<ProductView> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
