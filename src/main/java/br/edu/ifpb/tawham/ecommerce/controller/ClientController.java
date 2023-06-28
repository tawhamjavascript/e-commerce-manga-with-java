package br.edu.ifpb.tawham.ecommerce.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.tawham.ecommerce.DTO.LoginDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.ProductDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.RegisterClientDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.ShoppingCartDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.UserDTO;
import br.edu.ifpb.tawham.ecommerce.model.ProductSold;
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
    public ResponseEntity<Void> registerClient(@RequestBody RegisterClientDTO client) {
        clientService.registerClient(client);
        URI uri = URI.create("/client/" + client.name());
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO login) {
        UserDTO user =  clientService.login(login);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}/shoppingCart")
    public ResponseEntity<ShoppingCartDTO> getShoppingCart(@PathVariable Long id) {
        ShoppingCartDTO shoppingCartDTO = clientService.getShoppingCart(id);
        return ResponseEntity.ok(shoppingCartDTO);
    }

    @PostMapping("/{id}/shoppingCart")
    public ResponseEntity<Void> addProductToShoppingCart(@PathVariable Long id, @RequestBody ProductDTO product) {
        try {
            clientService.addProductToShoppingCart(id, product);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/shoppingCart/{idSc}/{idP}")
    public ResponseEntity<Void> removeProductFromShoppingCart(@PathVariable Long idSc, @PathVariable Long idP) {
        try {
            clientService.removeProductFromShoppingCart(idSc, idP);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/shoppingCart/{id}/checkout")
    public ResponseEntity<Void> checkout(@PathVariable Long id) {
        try {
            clientService.checkout(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/checkouts")
    public ResponseEntity<List<ProductSold>> getCheckouts(@PathVariable Long id) {
        List<ProductSold> checkouts = clientService.getCheckouts(id);
        return ResponseEntity.ok(checkouts);
    }
}
