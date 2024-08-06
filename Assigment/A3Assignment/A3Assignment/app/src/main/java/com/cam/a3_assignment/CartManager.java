package com.cam.a3_assignment;

import com.cam.a3_assignment.model.Product;

import java.util.ArrayList;

public class CartManager {
    private static CartManager instance;
    private ArrayList<Product> cartProducts;

    private CartManager() {
        cartProducts = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        cartProducts.add(product);
    }

    public ArrayList<Product> getCartProducts() {
        return cartProducts;
    }

    public void clearCart() {
        cartProducts.clear();
    }
}
