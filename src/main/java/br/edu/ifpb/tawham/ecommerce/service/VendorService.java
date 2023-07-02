package br.edu.ifpb.tawham.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.tawham.ecommerce.DTO.LoginDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.ProductDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.RegisterVendorDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.UserDTO;
import br.edu.ifpb.tawham.ecommerce.ErrorsCustomizer.VendorNotFoundException;
import br.edu.ifpb.tawham.ecommerce.model.Product;
import br.edu.ifpb.tawham.ecommerce.model.ProductSold;
import br.edu.ifpb.tawham.ecommerce.model.Vendor;
import br.edu.ifpb.tawham.ecommerce.repositories.CheckoutRepository;
import br.edu.ifpb.tawham.ecommerce.repositories.ProductRepositoty;
import br.edu.ifpb.tawham.ecommerce.repositories.VendorRepository;
import jakarta.transaction.Transactional;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ProductRepositoty productRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;


    @Transactional
    public void registerVendor(RegisterVendorDTO registerVendorDTO) {
        Vendor vendor = new Vendor(registerVendorDTO);
        vendorRepository.save(vendor);
        
    }

    public UserDTO login(LoginDTO loginDTO) throws VendorNotFoundException{
        Vendor vendor = vendorRepository.findByEmailAndPassword(loginDTO.email(), loginDTO.password());
        if (vendor != null) {
            return vendor.toDTO();
        } 
        throw new VendorNotFoundException(loginDTO.email());
    }

    public List<ProductDTO> getAllProductsVendor(Long id){
        Optional<Vendor> vendor = vendorRepository.findById(id);
        
        List<Product> products = vendor.get().getProducts();
        return products.stream().map(Product::toDTO).collect(Collectors.toList());
           
    }
    @Transactional
    public void registerProduct(Long id, ProductDTO productDTO) throws VendorNotFoundException{
        Optional<Vendor> vendor = vendorRepository.findById(id);
        Product product = new Product(productDTO);
        product.setVendor(vendor.get());

        productRepository.save(product);

        vendor.get().setProducts(product);
    }

    public List<ProductSold> getProductsSold(Long id) {
        List<ProductSold> productsSold = new ArrayList<>();
        checkoutRepository.getSoldsVendor(id).forEach(
            checkout -> {
                productsSold.add(checkout.getProductsSold());
            }
        );
        return productsSold;
    }
}
