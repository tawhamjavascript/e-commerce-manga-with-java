package br.edu.ifpb.tawham.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductSold {
    private String name;
    private int chapter;
    private String description;
    private String genre;
    private String author;
    private String publication;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double price;
    private String image;

    public ProductSold() {
        // implements
    }

    public ProductSold(String name, int chapter, String description, String genre, String author, String publication, double price, String image) {
        this.name = name;
        this.chapter = chapter;
        this.description = description;
        this.genre = genre;
        this.author = author;
        this.publication = publication;
        this.price = price;
        this.image = image;
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

    @Override
    public String toString() {
        return "ProductSold [name=" + name + ", chapter=" + chapter + ", description=" + description + ", genre="
                + genre + ", author=" + author + ", publication=" + publication + ", price=" + price + ", image="
                + image + "]";
    }
}