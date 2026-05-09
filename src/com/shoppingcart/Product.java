package com.shoppingcart;

/**
 * Represents one product item in the shopping cart.
 * This class demonstrates encapsulation with private fields and public methods.
 */
public class Product {
    private int productId;
    private String productName;
    private int quantity;
    private double price;

    /**
     * Creates a product with all required details.
     *
     * @param productId unique product id
     * @param productName product name
     * @param quantity selected quantity
     * @param price unit price
     */
    public Product(int productId, String productName, int quantity, double price) {
        setProductId(productId);
        setProductName(productName);
        setQuantity(quantity);
        setPrice(price);
    }

    /**
     * Creates a safe copy of another product.
     *
     * @param product product object to copy
     */
    public Product(Product product) {
        this(product.getProductId(), product.getProductName(), product.getQuantity(), product.getPrice());
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero.");
        }
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        this.productName = productName.trim();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    /**
     * Calculates item-wise subtotal.
     *
     * @return quantity multiplied by unit price
     */
    public double calculateSubtotal() {
        return quantity * price;
    }
}
