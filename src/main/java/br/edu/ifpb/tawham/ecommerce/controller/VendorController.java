package br.edu.ifpb.tawham.ecommerce.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.tawham.ecommerce.DTO.LoginDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.ProductDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.RegisterVendorDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.UserDTO;
import br.edu.ifpb.tawham.ecommerce.ErrorsCustomizer.VendorNotFoundException;
import br.edu.ifpb.tawham.ecommerce.model.ProductSold;
import br.edu.ifpb.tawham.ecommerce.service.ProductService;
import br.edu.ifpb.tawham.ecommerce.service.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public void registerVendor(@RequestBody RegisterVendorDTO vendor) {
        vendorService.registerVendor(vendor);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) throws VendorNotFoundException {
        try {
            UserDTO user = vendorService.login(loginDTO);
            return ResponseEntity.ok(user);

        } catch (VendorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/products")
    public ResponseEntity<List<ProductDTO>> getAllProductsVendor(@PathVariable Long id) {
        List<ProductDTO> products = vendorService.getAllProductsVendor(id);
        return ResponseEntity.ok(products);
    }
    @GetMapping("product/{idProduct}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long idProduct) {
        ProductDTO product = productService.getProductById(idProduct);
        return ResponseEntity.ok(product);
    }

    @PostMapping("{id}/product")
    public void registerProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) throws VendorNotFoundException {
        vendorService.registerProduct(id, productDTO);
    }

    @PutMapping("product")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDTO productDTO) {        
        productService.editProduct(productDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/checkout")
    public ResponseEntity<List<ProductSold>> getAllProductsCheckout(@PathVariable Long id) {
        List<ProductSold> products = vendorService.getProductsSold(id);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("product/{idProduct}")
    public void deleteProduct(@PathVariable Long idProduct) {
        productService.deleteProduct(idProduct);
    }
    
}
