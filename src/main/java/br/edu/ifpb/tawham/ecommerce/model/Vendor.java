package br.edu.ifpb.tawham.ecommerce.model;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String description;

    @OneToMany(mappedBy = "vendor",
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private ArrayList<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "vendor",
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private ArrayList<Checkout> solds = new ArrayList<>();

    public Vendor(Long id, String name, String email, String password, String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
    }
    
    public Vendor() {

    }

    public Vendor(Long id, String name, String email, String password, String description, 
    ArrayList<Product> products, ArrayList<Checkout> solds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
        this.products = products;
        this.solds = solds;
    }

    public Long getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }

    public ArrayList<Checkout> getSolds() {
        return solds;
    }

    public void setCheckouts(Checkout sold) {
        this.solds.add(sold);
    }

    @Override
    public String toString() {
        return "Vendor [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", description="
                + description + ", products=" + products + ", checkouts=" + solds + "]";
    }
   
}
