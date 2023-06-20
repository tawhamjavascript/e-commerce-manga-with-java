package br.edu.ifpb.tawham.ecommerce.model;


import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;



@Entity
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;

    @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private Client client;

    @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private Vendor vendor;

    @ElementCollection
    private ArrayList<ProductSold> productsSold = new ArrayList<>();

    public Checkout() { /* TODO document why this constructor is empty */ }

    public Checkout(Long id, String date, Client client, Vendor vendor, ArrayList<ProductSold> productsSold) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.vendor = vendor;
        this.productsSold = productsSold;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public ArrayList<ProductSold> getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(ProductSold productsSold) {
        this.productsSold.add(productsSold);
    }

    
}
