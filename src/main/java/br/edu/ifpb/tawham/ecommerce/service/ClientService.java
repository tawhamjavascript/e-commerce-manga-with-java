package br.edu.ifpb.tawham.ecommerce.service;
import org.springframework.stereotype.Service;

import br.edu.ifpb.tawham.ecommerce.DTO.LoginDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.ProductDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.RegisterClientDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.ShoppingCartDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.UserDTO;
import br.edu.ifpb.tawham.ecommerce.ErrorsCustomizer.NoProductException;
import br.edu.ifpb.tawham.ecommerce.ErrorsCustomizer.ProductNotFoundException;
import br.edu.ifpb.tawham.ecommerce.model.Checkout;
import br.edu.ifpb.tawham.ecommerce.model.Client;
import br.edu.ifpb.tawham.ecommerce.model.Product;
import br.edu.ifpb.tawham.ecommerce.model.ProductSold;
import br.edu.ifpb.tawham.ecommerce.model.ShoppingCart;
import br.edu.ifpb.tawham.ecommerce.model.Vendor;
import br.edu.ifpb.tawham.ecommerce.repositories.CheckoutRepository;
import br.edu.ifpb.tawham.ecommerce.repositories.ClientRepository;
import br.edu.ifpb.tawham.ecommerce.repositories.ProductRepositoty;
import br.edu.ifpb.tawham.ecommerce.repositories.ShoppingCartRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepositoty productRepositoty;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Transactional
    public void registerClient(RegisterClientDTO registerClientDTO) {
        clientRepository.save(new Client(registerClientDTO));
    }
    

    public UserDTO login(LoginDTO loginDTO) {
        Client client = clientRepository.findByEmailAndPassword(loginDTO.email(), loginDTO.password());
        if (client != null) {
            return client.toDTO();
        } 
        return null;
    }

    public ShoppingCartDTO getShoppingCart(Long id) {
        return shoppingCartRepository.getShoppingCart(id).toDTO();

    }
    @Transactional
    public void addProductToShoppingCart(Long id, ProductDTO productDTO) throws ProductNotFoundException {
        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCart(id);
        Optional<Product> product = productRepositoty.findById(productDTO.id());
        if (product.isPresent()) {
            shoppingCart.setProducts(product.get());
            product.get().setShoppingCarts(shoppingCart);

        } else {
            throw new ProductNotFoundException(productDTO.id());
        }
    }
    @Transactional
    public void removeProductFromShoppingCart(Long idShoppingCart, Long idProduct) throws ProductNotFoundException {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(idShoppingCart);
        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            if(!shoppingCart.removeProduct(idProduct)) {
                throw new ProductNotFoundException(idProduct);
            }
        }        
    }

    @Transactional
    public void checkout(Long idShoppingCart) throws NoProductException {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(idShoppingCart);
        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            if(shoppingCart.getProducts().isEmpty()) {
                throw new NoProductException("Não há produtos no carrinho");
            }
            List<Product> products = shoppingCart.getProducts();
            Client client = shoppingCart.getClient();
            products.forEach(product -> {
                ProductSold productSold = new ProductSold(product);
                Vendor vendor = product.getVendor();
                Checkout checkout = new Checkout(LocalDate.now().toString(), client, vendor, productSold);
                checkoutRepository.save(checkout);
                vendor.setCheckouts(checkout);
                client.setCheckouts(checkout);
                product.removeShoppingCarts(shoppingCart);
            });
            shoppingCart.removeAllProducts();
        }
    }
    public List<ProductSold> getCheckouts(Long idClient) {
       List<ProductSold> productSold = new ArrayList<>();
       checkoutRepository.getCheckoutsClient(idClient).forEach(checkout -> {
            productSold.add(checkout.getProductsSold());
        });
        return productSold;
    }
}


