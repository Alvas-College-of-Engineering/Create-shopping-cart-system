package com.shoppingcart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Handles cart operations such as add, remove, update, clear, and billing.
 */
public class Cart {
    private final ArrayList<Product> cartItems;

    /**
     * Creates an empty cart backed by ArrayList.
     */
    public Cart() {
        cartItems = new ArrayList<>();
    }

    /**
     * Adds a product to the cart. If the product already exists, quantity is increased.
     *
     * @param product product to add
     */
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        Product existingProduct = searchProduct(product.getProductId());
        if (existingProduct == null) {
            cartItems.add(new Product(product));
        } else {
            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
        }
    }

    /**
     * Adds a product using individual values.
     *
     * @param productId unique product id
     * @param productName product name
     * @param quantity selected quantity
     * @param price unit price
     */
    public void addProduct(int productId, String productName, int quantity, double price) {
        addProduct(new Product(productId, productName, quantity, price));
    }

    /**
     * Removes a product by id.
     *
     * @param productId product id to remove
     * @return true when removed
     */
    public boolean removeProduct(int productId) {
        Product product = searchProduct(productId);
        return product != null && cartItems.remove(product);
    }

    /**
     * Updates selected product quantity.
     *
     * @param productId product id to update
     * @param newQuantity replacement quantity
     * @return true when product exists and quantity is updated
     */
    public boolean updateProductQuantity(int productId, int newQuantity) {
        Product product = searchProduct(productId);
        if (product == null) {
            return false;
        }
        product.setQuantity(newQuantity);
        return true;
    }

    /**
     * Searches product by id.
     *
     * @param productId product id
     * @return matching product or null
     */
    public Product searchProduct(int productId) {
        for (Product product : cartItems) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Removes every item from the cart.
     */
    public void clearCart() {
        cartItems.clear();
    }

    /**
     * Calculates final payable bill amount.
     *
     * @return total bill
     */
    public double calculateTotalBill() {
        double totalBill = 0;
        for (Product product : cartItems) {
            totalBill += product.calculateSubtotal();
        }
        return totalBill;
    }

    /**
     * Counts all quantities in the cart.
     *
     * @return total selected units
     */
    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Product product : cartItems) {
            totalQuantity += product.getQuantity();
        }
        return totalQuantity;
    }

    /**
     * Checks whether cart is empty.
     *
     * @return true when no products exist
     */
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    /**
     * Returns a read-only view to protect internal ArrayList storage.
     *
     * @return cart products
     */
    public List<Product> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }
}
