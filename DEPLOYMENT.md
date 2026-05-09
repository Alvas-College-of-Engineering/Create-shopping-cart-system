# Shopping Cart Web Application Deployment

This project is a Java Servlet and JSP web application for Apache Tomcat.

## Folder Structure

```text
Shopping_Cart/
|-- index.jsp
|-- cart.jsp
|-- css/
|   `-- styles.css
|-- WEB-INF/
|   |-- web.xml
|   `-- classes/              created after compilation
|-- src/
|   `-- com/
|       `-- shoppingcart/
|           |-- Product.java
|           |-- Cart.java
|           `-- ShoppingServlet.java
|-- run.bat
`-- README.md
```

## Requirements

- JDK 8 or later
- Apache Tomcat 9.x
- VS Code with Java extensions

This code uses the `javax.servlet` API, which matches Tomcat 9. For Tomcat 10 or newer, convert imports from `javax.servlet` to `jakarta.servlet`.

## Compile in VS Code or Command Prompt

1. Set `TOMCAT_HOME` to your Tomcat installation folder.
   Example:

   ```bat
   set TOMCAT_HOME=C:\apache-tomcat-9.0.89
   ```

2. Run:

   ```bat
   run.bat
   ```

The script compiles Java files into `WEB-INF\classes`.

## Deploy on Apache Tomcat

1. Copy the complete `Shopping_Cart` folder into Tomcat `webapps`.
2. Rename the copied folder to `ShoppingCart`.
3. Start Tomcat using `bin\startup.bat`.
4. Open:

   ```text
   http://localhost:8080/ShoppingCart
   ```

## Features

- Add products
- Remove products
- Update quantity
- Display cart items
- Calculate total bill
- Clear cart
- Responsive JSP, HTML, and CSS interface
