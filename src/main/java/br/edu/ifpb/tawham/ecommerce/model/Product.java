package br.edu.ifpb.tawham.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.tawham.ecommerce.DTO.ProductDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int chapter;
    private String description;
    private String genre;
    private String author;
    private String publication;
    private double price;
    private String image;

    @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private Vendor vendor;

    @ManyToMany(mappedBy = "products",
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
    })
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();


    public Product(Long id, String name, int chapter, String description, String genre, 
                   String author, String publication, double price, String image, 
                   Vendor vendor, List<ShoppingCart> shoppingCarts) {
        this.id = id;
        this.name = name;
        this.chapter = chapter;
        this.description = description;
        this.genre = genre;
        this.author = author;
        this.publication = publication;
        this.price = price;
        this.image = image;
        this.vendor = vendor;
        this.shoppingCarts = shoppingCarts;
    

    }
    public Product(ProductDTO productDTO) {
        this.name = productDTO.name();
        this.chapter = productDTO.chapter();
        this.description = productDTO.description();
        this.genre = productDTO.genre();
        this.author = productDTO.author();
        this.publication = productDTO.publication();
        this.price = productDTO.price();
        this.image = productDTO.image();
    }



    public Product() {
    }

    public ProductDTO toDTO() {
        return new ProductDTO(
            this.id,
            this.name,
            this.chapter,
            this.description,
            this.genre,
            this.author,
            this.publication,
            this.price,
            this.image
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(ShoppingCart shoppingCarts) {
        this.shoppingCarts.add(shoppingCarts);
    }

    public void removeShoppingCarts(ShoppingCart shoppingCarts) {
        this.shoppingCarts.remove(shoppingCarts);
    }

    public void removeAllShoppingCarts() {
        this.shoppingCarts.clear();
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", chapter=" + chapter + ", description=" + description
                + ", genre=" + genre + ", author=" + author + ", publication=" + publication + ", price=" + price
                + ", image=" + image + ", vendor=" + vendor + ", shoppingCarts=" + shoppingCarts + "]";
    }  
}
