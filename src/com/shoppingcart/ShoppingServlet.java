package com.shoppingcart;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet controller for all shopping cart web actions.
 */
public class ShoppingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Displays the cart page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("cart", getCart(request));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles add, remove, update, and clear cart form submissions.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Cart cart = getCart(request);
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addProduct(request, cart);
                request.getSession().setAttribute("message", "Product added to cart successfully.");
            } else if ("remove".equals(action)) {
                removeProduct(request, cart);
            } else if ("update".equals(action)) {
                updateQuantity(request, cart);
            } else if ("clear".equals(action)) {
                cart.clearCart();
                request.getSession().setAttribute("message", "Cart cleared successfully.");
            }
        } catch (IllegalArgumentException exception) {
            request.getSession().setAttribute("error", exception.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }

    /**
     * Reads product form values and adds them to the cart.
     */
    private void addProduct(HttpServletRequest request, Cart cart) {
        int productId = parsePositiveInt(request.getParameter("productId"), "Product ID");
        String productName = request.getParameter("productName");
        int quantity = parsePositiveInt(request.getParameter("quantity"), "Quantity");
        double price = parseNonNegativeDouble(request.getParameter("price"), "Price");

        cart.addProduct(productId, productName, quantity, price);
    }

    /**
     * Removes selected product and stores a user-friendly status message.
     */
    private void removeProduct(HttpServletRequest request, Cart cart) {
        int productId = parsePositiveInt(request.getParameter("productId"), "Product ID");
        if (cart.removeProduct(productId)) {
            request.getSession().setAttribute("message", "Product removed from cart.");
        } else {
            request.getSession().setAttribute("error", "Product not found in cart.");
        }
    }

    /**
     * Updates the quantity for a selected cart item.
     */
    private void updateQuantity(HttpServletRequest request, Cart cart) {
        int productId = parsePositiveInt(request.getParameter("productId"), "Product ID");
        int quantity = parsePositiveInt(request.getParameter("quantity"), "Quantity");

        if (cart.updateProductQuantity(productId, quantity)) {
            request.getSession().setAttribute("message", "Quantity updated successfully.");
        } else {
            request.getSession().setAttribute("error", "Product not found in cart.");
        }
    }

    /**
     * Creates or returns the session cart for the current user.
     */
    private Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    private int parsePositiveInt(String value, String fieldName) {
        try {
            if (value == null || value.trim().isEmpty()) {
                throw new NumberFormatException();
            }

            int number = Integer.parseInt(value.trim());
            if (number <= 0) {
                throw new NumberFormatException();
            }
            return number;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(fieldName + " must be a positive whole number.");
        }
    }

    private double parseNonNegativeDouble(String value, String fieldName) {
        try {
            if (value == null || value.trim().isEmpty()) {
                throw new NumberFormatException();
            }

            double number = Double.parseDouble(value.trim());
            if (number < 0) {
                throw new NumberFormatException();
            }
            return number;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(fieldName + " must be a valid non-negative amount.");
        }
    }
}
