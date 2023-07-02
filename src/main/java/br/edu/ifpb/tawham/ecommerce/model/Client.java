package br.edu.ifpb.tawham.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.tawham.ecommerce.DTO.RegisterClientDTO;
import br.edu.ifpb.tawham.ecommerce.DTO.UserDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart = new ShoppingCart();

    @OneToMany(mappedBy = "client", 
               cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
               })
    private List<Checkout> checkouts = new ArrayList<>();

    public Client() {
    }

    public Client(Long id, String name, String email, String password, 
                  ShoppingCart shoppingCart, List<Checkout> checkouts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.shoppingCart = shoppingCart;
        this.checkouts = checkouts;
    }
    public Client(RegisterClientDTO registerClientDTO) {
        this.name = registerClientDTO.name();
        this.email = registerClientDTO.email();
        this.password = registerClientDTO.password();
    }


    public UserDTO toDTO() {
        return new UserDTO(this.id);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    public List<Checkout> getCheckouts() {
        return checkouts;
    }
    public void setCheckouts(Checkout checkout) {
        this.checkouts.add(checkout);
    }
}
