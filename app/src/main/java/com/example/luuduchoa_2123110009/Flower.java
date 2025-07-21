package com.example.luuduchoa_2123110009;

public class Flower {
    private String name;
    private int price;
    private int quantity;
    private int imageResId;
    private String imageUrl; // ✅ mới thêm

    // Constructor dùng drawable resource
    public Flower(String name, int price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = 1;
        this.imageUrl = null;
    }

    // Constructor dùng ảnh từ URL
    public Flower(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = 1;
        this.imageResId = 0;
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
