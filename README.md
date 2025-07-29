# BookStack - Backend API 📚

Book tracking platform backend built with Spring Boot 3.5 and Java 21.

![Spring Boot](https://img.shields.io/badge/Spring%2520Boot-3.5.3-brightgreen)
![Java](https://img.shields.io/badge/Java-21-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 🚀 Quick Start

### Prerequisites

Before you begin, ensure you have the following installed:

* **Java 21**
* **MySQL 8.0+**

### Installation

Follow these steps to get the project up and running:

1.  **Clone the repository:**

    ```bash
    git clone [https://github.com/zohra-b/S5.02.bookstack](https://github.com/zohra-b/S5.02.bookstack)
    cd 5.02.bookstack
    ```

2.  **Create your database:**
    Open MySql : 
    CREATE DATABASE bookstack;
  

3.  **Configure your database: (application.properties file):**
    Open `src/main/resources/application.properties` and update the MySQL connection details:
    
```properties
# --- MySQL Configuration ---
spring.datasource.url=jdbc:mysql://localhost:3306/bookstack?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# --- JWT Configuration ---
jwt.secret.key=your-secret-key

```


you can generate your secret key with this command : 
```
openssl rand -base64 32
```


4.  **Build and run the application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

---

## 🛠️ Tech Stack

This project leverages the following key technologies:

### Core

* **Spring Boot**: `3.5.3` (Web, Data JPA, Security, Validation)
* **Java**: `21`
* **MapStruct**: `1.6.3` (DTO mapping)
* **Lombok**: (Boilerplate reduction)

### Security

* **JWT Authentication**: `jjwt 0.12.5`
* **Spring Security**

### Database

* **MySQL**: (Production)
* **H2**: (Development/Testing)

### Documentation

* **OpenAPI 3.0**: `springdoc-openapi 2.8.9`

---
## 📦 Backend Project Structure

The backend is a Java Spring Boot application organized with a clean layered architecture.

---

## 📂 Project Structure

Here's an overview of the main directories and files in this project:
```
src/
└── main/
├── java/
│   └── com.cat.S5_2.bookstack/
│       ├── config/        # Spring configuration files (e.g., security, CORS, Swagger)
│       ├── controllers/   # REST controllers (API endpoints)
│       ├── dtos/          # Data Transfer Objects for input/output
│       ├── entities/      # JPA entities representing database tables
│       ├── enums/         # Enum definitions (e.g., book status)
│       ├── exceptions/    # Custom exceptions and handlers
│       ├── mappers/       # MapStruct interfaces for converting entities <-> DTOs
│       ├── repositories/  # Spring Data JPA repositories (CRUD)
│       ├── security/      # Security config (JWT, filters, user details)
│       ├── services/      # Business logic and service layer
│       └── Application.java # Main Spring Boot application entry point
└── resources/
├── application.properties # App configuration (port, DB, JWT secrets, etc.)
└── logback-spring.xml   # Logging configuration
```
---

## 📦 Domain Models

Here's an overview of the main entities and their key fields:

| Entity   | Key Fields                                                                               |
| :------- | :--------------------------------------------------------------------------------------- |
| `User`     | `username`, `email`, `password`, `role` (USER/ADMIN)                                   |
| `Book`     | `title`, `authors` (Set), `ISBN`, `genres` (Set), `publicationYear`                    |
| `UserBook` | `status` (WISHLIST/TO_READ/READING/FINISHED/ON_HOLD, DROPPED), `rating`, `comment`     |
| `Author`   | `authorId`, `lastName`, `firstName`                                                    |
| `Genre`    | `id`, `name`                                                                           |

---

## 🔍 API Documentation

API documentation is automatically generated at runtime:

* **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🛡️ Security Endpoints

The following endpoints are available for user authentication:

| Method | Endpoint               | Description               |
| :----- | :--------------------- | :------------------------ |
| `POST`   | `/api/auth/register` | Register a new user       |
| `POST`   | `/api/auth/login`    | Authenticate and get JWT  |

---

## 🏗️ Database Initialization
### Seeding your database importing datas
1. Log in to phpMyAdmin / MySQL
2. Click on the **"Import"** tab
3. Select the `bookstack_dump.sql` file


### Sample SQL Data
```sql
-- Users
INSERT INTO users (email, password, user_name, role) VALUES
('admin', 'admin@bookstack.com', 'mypassword', 'ROLE_ADMIN'),
('user1', 'user1@email.com', 'mypassword', 'role_USER');

-- Books
INSERT INTO books (title, isbn, publication_year, language, image_url, description) VALUES
('Children of the Alley', ' 0385420943', 1996, 'English', 'https://m.media-amazon.com/images/I/91rO7YtH76L._SL1500_.jpg', 'First published in Arabic in 1959, the story of an Egyptian family mirrors the spiritual history of humankind as a feudal lord disowns one son for diabolical pride and puts another son to the ultimate test. By the Nobel Prize-winning author of Arabian Nights and Days. ')

-- Books / Author
INSERT INTO books_authors (book_id, author_id) VALUES
(254, 136)

-- UserBooks
INSERT INTO user_book_associations (user_id, book_id, status, rating, comment) VALUES
(2, 254, 'FINISHED', 5, "I loved it !")
```

## 🧪 Testing

To run the test suite, execute the following command:

```bash
mvn test
```

## 🤝 Contributing

    Fork the project

    Create your feature branch (git checkout -b feature/AmazingFeature)

    Commit your changes (git commit -m 'Add some feature')

    Push to the branch (git push origin feature/AmazingFeature)

    Open a Pull Request

## 🔗 Recommended Frontend
For the complete BookStack application, check out the [BookStack Frontend Repository](https://github.com/zohra-b/S5.02.bookstack-front.git).

