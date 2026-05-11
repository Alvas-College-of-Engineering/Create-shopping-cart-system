# Shopping Cart Web Application

A complete Java Servlet and JSP based Shopping Cart System created for a college mini project or lab project. The project demonstrates Object-Oriented Programming concepts such as classes, objects, constructors, encapsulation, methods, and `ArrayList`, with a responsive web interface for Apache Tomcat.

## Folder Structure

```text
Shopping_Cart/
|-- README.md
|-- DEPLOYMENT.md
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
`-- docs/
    `-- PROJECT_DOCUMENTATION.md
```

## How To Run



### Requirements

- JDK 8 or later
- Apache Tomcat 9.x
- VS Code with Java extensions

### Compile

```bat
set TOMCAT_HOME=C:\apache-tomcat-9.0.89
run.bat
```

### Deploy

1. Copy this project folder into Tomcat `webapps`.
2. Rename the copied folder to `ShoppingCart`.
3. Start Tomcat.
4. Open `http://localhost:8080/ShoppingCart`.

More details are available in `DEPLOYMENT.md`.

## Classes Used

- `Product`: Stores product id, name, quantity, price, and subtotal calculation.
- `Cart`: Stores products in `ArrayList<Product>` and manages add, remove, update, clear, and billing operations.
- `ShoppingServlet`: Servlet controller that handles web requests and session cart data.

## Features

- Add products to cart
- Remove products from cart
- Update product quantity
- Display all cart items
- Show item-wise subtotal
- Calculate total bill automatically
- Clear cart
- Responsive JSP, HTML, and CSS UI
- Servlet mapping through `WEB-INF/web.xml`
- Exception handling and input validation

## Possible Future Enhancements

- Add login system for customers and admins
- Store product and cart data in files or a database
- Add discount coupons and tax calculation
- Add product catalog and stock management
- Generate printable invoice files
- Build a GUI using JavaFX or Swing
- Add unit tests with JUnit
## output
output.png






