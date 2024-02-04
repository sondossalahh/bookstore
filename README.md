# Online Bookstore Management System

## Interfaces

### Customer Interface

- **Browse Books by Categories**
  - **HTTP Method:** GET
  - **Endpoint:** `http://localhost:8080/categories/1/books`

- **View Book Details**
  - **HTTP Method:** GET
  - **Endpoint:** `http://localhost:8080/books/19`

- **Request to Borrow a Book**
  - **HTTP Method:** POST
  - **Endpoint:** `http://localhost:8080/books/{book_id}/borrow`

### Administrator Interface

- **Create New Category**
  - **HTTP Method:** POST
  - **Endpoint:** `http://localhost:8080/categories`

- **Display All Categories**
  - **HTTP Method:** GET
  - **Endpoint:** `http://localhost:8080/categories`

- **Add New Book**
  - **HTTP Method:** POST
  - **Endpoint:** `http://localhost:8080/books/addBook`
  - **Request Body:**
    ```json
    {
      "title": "Information System",
      "author": "sondos",
      "availability": true,
      "category": {
          "id": 1
      }
    }
    ```

- **Update Book Details**
  - **HTTP Method:** POST
  - **Endpoint:** `http://localhost:8080/books/update/{bookId}`

- **Change Book Availability**
  - **HTTP Method:** POST
  - **Endpoint:** `http://localhost:8080/books/3/setAvailability`
  - **Request Body:**
    ```json
    {
      "availability": true/false
    }
    ```

---

## SQL Scripts (using mySQL)

The following SQL scripts are used to create the necessary database tables:

```sql
CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    author VARCHAR(255),
    availability BOOLEAN
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    email VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE borrow_requests (
    id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT,
    customer_id INT,
    borrow_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (customer_id) REFERENCES users(id)
);

CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

ALTER TABLE books
ADD COLUMN category_id INT,
ADD FOREIGN KEY (category_id) REFERENCES categories(id);
