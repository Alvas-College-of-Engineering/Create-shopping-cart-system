# Mini Project Report: Shopping Cart Web Application

## Introduction

The Shopping Cart System is a Java web application designed to manage products in a customer's cart through a browser-based interface. It allows the user to add products, remove products, update quantities, display cart items, clear the cart, and calculate the total bill automatically.

## Objective

The objective is to convert a Java console shopping cart system into a complete Servlet and JSP web application for Apache Tomcat while preserving Object-Oriented Programming principles.

## Technologies Used

- Java
- Java Servlets
- JSP
- HTML
- CSS
- Apache Tomcat 9
- `ArrayList<Product>` collection
- VS Code

## OOP Concepts Applied

- Class: `Product`, `Cart`, and `ShoppingServlet`
- Object: Product and Cart objects are created and used at runtime
- Constructor: Parameterized constructors initialize product data
- Encapsulation: Private fields are accessed through getters and setters
- Methods: Each cart operation is separated into reusable methods
- Method Overloading: `addProduct(Product)` and `addProduct(int, String, int, double)`
- ArrayList: Cart items are stored using `ArrayList<Product>`

## Project Structure

```text
Shopping_Cart/
|-- index.jsp
|-- cart.jsp
|-- css/
|   `-- styles.css
|-- WEB-INF/
|   |-- web.xml
|   `-- classes/
|-- src/
|   `-- com/
|       `-- shoppingcart/
|           |-- Product.java
|           |-- Cart.java
|           `-- ShoppingServlet.java
|-- run.bat
`-- DEPLOYMENT.md
```

## Step-By-Step Explanation

1. `Product.java` defines the product model with product id, name, quantity, price, and subtotal calculation.
2. `Cart.java` stores all cart items in an `ArrayList<Product>`.
3. `Cart.java` provides methods to add, remove, update, clear, and calculate the bill.
4. `ShoppingServlet.java` acts as the backend controller.
5. The servlet stores each user's cart in the HTTP session.
6. `cart.jsp` displays the frontend interface and cart table.
7. `styles.css` creates the responsive and modern UI.
8. `web.xml` maps `ShoppingServlet` to the `/cart` URL.
9. `index.jsp` redirects users to the cart page.

## Algorithm

1. User opens `http://localhost:8080/ShoppingCart`.
2. `index.jsp` redirects the user to `/cart`.
3. `ShoppingServlet` creates or retrieves the session cart.
4. `cart.jsp` displays the add-product form and current cart items.
5. When the user submits a form, the servlet reads the selected action.
6. For Add, the servlet validates product details and adds the product.
7. For Remove, the servlet removes the product by id.
8. For Update, the servlet updates the selected quantity.
9. For Clear, the servlet removes all products.
10. The JSP displays the updated cart and total bill.

## Servlet Mapping

```xml
<servlet-mapping>
    <servlet-name>ShoppingServlet</servlet-name>
    <url-pattern>/cart</url-pattern>
</servlet-mapping>
```

## Viva Questions And Answers

### 1. What is the main purpose of this project?

The purpose is to manage shopping cart operations such as adding products, removing products, updating quantities, clearing the cart, and calculating the total bill through a web interface.

### 2. Which OOP concepts are used?

The project uses classes, objects, constructors, encapsulation, methods, method overloading, and collection-based object storage.

### 3. Why is `ArrayList` used?

`ArrayList` is used because the number of products in the cart can change dynamically at runtime.

### 4. What is the role of `ShoppingServlet`?

`ShoppingServlet` handles backend logic, reads form data, updates the cart, and forwards or redirects requests.

### 5. What is the role of JSP?

JSP is used to create the frontend page that displays forms, cart items, messages, and total bill details.

### 6. What is `web.xml`?

`web.xml` is the deployment descriptor that configures servlet names, servlet classes, URL mappings, and welcome files.

### 7. Where is the cart stored?

The cart is stored in the HTTP session so each browser session gets its own cart.

### 8. How is the total bill calculated?

The total bill is calculated by adding the subtotal of every product. Each subtotal is quantity multiplied by price.

## Conclusion

The Shopping Cart Web Application successfully converts the console project into a browser-based Java web application using Servlets, JSP, HTML, CSS, and Apache Tomcat. It keeps the code modular, demonstrates OOP concepts clearly, and provides an attractive UI suitable for college project demonstration.
