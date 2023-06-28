package br.edu.ifpb.tawham.ecommerce.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.ifpb.tawham.ecommerce.DTO.ProductDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.ShoppingCartDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "shoppingCart", 
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinColumn(name = "shoppingCart_id")
    private Client client;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<Product> products = new ArrayList<>();

    public ShoppingCart() { /* TODO document why this constructor is empty */ }

    public ShoppingCart(Long id, Client client, List<Product> products) {
        this.id = id;
        this.client = client;
        this.products = products;
    }

    public ShoppingCartDTO toDTO() {
        List<ProductDTO> productsDTO = new ArrayList<>();
        for (Product product : this.products) {
            productsDTO.add(product.toDTO());
        }
        return new ShoppingCartDTO(this.id, productsDTO);
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }
    
    public boolean removeProduct(Long id) {
        return this.products.removeIf(product -> {
            if (Objects.equals(product.getId(), id)) {
                product.getShoppingCarts().remove(this);
                product.removeShoppingCarts(this);
                return true;
            }
            return false;
        });
    }

    public void removeAllProducts() {
        for (Product product : this.products) {
            product.removeShoppingCarts(this);
        }
        this.products.clear();
    }
}
