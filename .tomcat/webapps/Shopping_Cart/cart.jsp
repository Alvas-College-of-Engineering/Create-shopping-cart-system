<%@ page import="java.util.List" %>
<%@ page import="com.shoppingcart.Cart" %>
<%@ page import="com.shoppingcart.Product" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%!
    // Escapes user-entered text before displaying it on the JSP page.
    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
%>
<%
    Cart cart = (Cart) request.getAttribute("cart");
    if (cart == null) {
        cart = new Cart();
    }

    String message = (String) session.getAttribute("message");
    String error = (String) session.getAttribute("error");
    session.removeAttribute("message");
    session.removeAttribute("error");

    List<Product> items = cart.getCartItems();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart System</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
    <main class="app-shell">
        <section class="hero">
            <div>
                <p class="eyebrow">Java Web Application</p>
                <h1>Shopping Cart System</h1>
                <p class="hero-copy">Manage products, quantities, and billing through a clean Servlet and JSP interface.</p>
            </div>
            <div class="hero-stats" aria-label="Cart summary">
                <div>
                    <span><%= items.size() %></span>
                    <small>Products</small>
                </div>
                <div>
                    <span><%= cart.getTotalQuantity() %></span>
                    <small>Units</small>
                </div>
                <div>
                    <span>Rs. <%= String.format("%.2f", cart.calculateTotalBill()) %></span>
                    <small>Total</small>
                </div>
            </div>
        </section>

        <% if (message != null) { %>
            <div class="alert success"><%= escapeHtml(message) %></div>
        <% } %>

        <% if (error != null) { %>
            <div class="alert error"><%= escapeHtml(error) %></div>
        <% } %>

        <section class="content-grid">
            <div class="panel">
                <div class="panel-heading">
                    <h2>Add Product</h2>
                    <p>Enter product details to add a new item or increase an existing quantity.</p>
                </div>

                <form class="product-form" action="<%= request.getContextPath() %>/cart" method="post">
                    <input type="hidden" name="action" value="add">

                    <label>
                        Product ID
                        <input type="number" name="productId" min="1" placeholder="101" required>
                    </label>

                    <label>
                        Product Name
                        <input type="text" name="productName" maxlength="60" placeholder="Wireless Mouse" required>
                    </label>

                    <div class="form-row">
                        <label>
                            Quantity
                            <input type="number" name="quantity" min="1" value="1" required>
                        </label>

                        <label>
                            Unit Price
                            <input type="number" name="price" min="0" step="0.01" placeholder="599.00" required>
                        </label>
                    </div>

                    <button class="primary-button" type="submit">Add to Cart</button>
                </form>
            </div>

            <div class="panel total-panel">
                <div class="panel-heading">
                    <h2>Total Bill</h2>
                    <p>Final amount is calculated automatically from all cart items.</p>
                </div>

                <div class="bill-amount">Rs. <%= String.format("%.2f", cart.calculateTotalBill()) %></div>
                <div class="bill-meta">
                    <span><%= cart.getTotalQuantity() %> units selected</span>
                    <span><%= items.size() %> product rows</span>
                </div>

                <form action="<%= request.getContextPath() %>/cart" method="post">
                    <input type="hidden" name="action" value="clear">
                    <button class="danger-button" type="submit" <%= cart.isEmpty() ? "disabled" : "" %>>Clear Cart</button>
                </form>
            </div>
        </section>

        <section class="panel cart-panel">
            <div class="panel-heading table-heading">
                <div>
                    <h2>Cart Items</h2>
                    <p>Update quantities, remove products, and review item-wise subtotal.</p>
                </div>
            </div>

            <% if (items.isEmpty()) { %>
                <div class="empty-state">
                    <h3>Your cart is empty</h3>
                    <p>Add a product from the form above to start calculating the bill.</p>
                </div>
            <% } else { %>
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>Product ID</th>
                                <th>Product Name</th>
                                <th>Quantity</th>
                                <th>Unit Price</th>
                                <th>Subtotal</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Product product : items) { %>
                                <tr>
                                    <td data-label="Product ID"><%= product.getProductId() %></td>
                                    <td data-label="Product Name"><strong><%= escapeHtml(product.getProductName()) %></strong></td>
                                    <td data-label="Quantity">
                                        <form class="inline-form" action="<%= request.getContextPath() %>/cart" method="post">
                                            <input type="hidden" name="action" value="update">
                                            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                                            <input class="quantity-input" type="number" name="quantity" min="1" value="<%= product.getQuantity() %>" required>
                                            <button class="small-button" type="submit">Update</button>
                                        </form>
                                    </td>
                                    <td data-label="Unit Price">Rs. <%= String.format("%.2f", product.getPrice()) %></td>
                                    <td data-label="Subtotal">Rs. <%= String.format("%.2f", product.calculateSubtotal()) %></td>
                                    <td data-label="Actions">
                                        <form action="<%= request.getContextPath() %>/cart" method="post">
                                            <input type="hidden" name="action" value="remove">
                                            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                                            <button class="ghost-button" type="submit">Remove</button>
                                        </form>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            <% } %>
        </section>
    </main>
</body>
</html>
