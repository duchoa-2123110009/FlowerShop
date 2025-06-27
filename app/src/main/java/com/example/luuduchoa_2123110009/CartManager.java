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
        Flower flowerCopy = new Flower(flower.getName(), flower.getPrice());
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
