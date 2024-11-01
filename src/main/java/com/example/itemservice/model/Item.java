package com.example.itemservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "items")
@Schema(description = "Details about the item")
public class Item {

    @Id
    @Schema(description = "Unique identifier of the item", example = "609e1278e4b0f1a3c4d3b2a1")
    private String id;

    @Schema(description = "Name of the item", example = "Wireless Mouse")
    private String name;

    @Schema(description = "Description of the item", example = "A high-quality wireless mouse")
    private String description;

    @Schema(description = "Price of the item", example = "29.99")
    private double price;

    @Schema(description = "Image URL of the item", example = "http://example.com/image.jpg")
    private String imageUrl;

    @Schema(description = "Universal Product Code", example = "123456789012")
    private String upc;

    @Schema(description = "Available inventory count", example = "100")
    private int inventoryCount;

    // Constructors
    public Item() {}

    public Item(String name, String description, double price, String imageUrl, String upc, int inventoryCount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.upc = upc;
        this.inventoryCount = inventoryCount;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}
