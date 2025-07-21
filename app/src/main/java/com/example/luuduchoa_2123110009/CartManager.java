package com.example.luuduchoa_2123110009;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Flower> cart;

    private CartManager() {
        cart = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Flower flower) {
        for (Flower f : cart) {
            if (f.getName().equals(flower.getName())) {
                f.increaseQuantity();
                return;
            }
        }

        // ✅ Tạo bản sao giữ đúng loại ảnh
        Flower flowerCopy;
        if (flower.getImageUrl() != null) {
            flowerCopy = new Flower(flower.getName(), flower.getPrice(), flower.getImageUrl());
        } else {
            flowerCopy = new Flower(flower.getName(), flower.getPrice(), flower.getImageResId());
        }

        flowerCopy.setQuantity(1);
        cart.add(flowerCopy);
    }

    public List<Flower> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }
}
