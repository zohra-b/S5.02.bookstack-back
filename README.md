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
  

3.  **Configure your database: (application.properties):**
    Open `src/main/resources/application.properties` and update the MySQL connection details:

    ```properties
    # MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/bookstack?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

  # JWT Configuration
jwt.secret.key=your-secret-key
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

## 🧪 Testing

To run the test suite, execute the following command:

```bash
mvn test

## 🤝 Contributing

    Fork the project

    Create your feature branch (git checkout -b feature/AmazingFeature)

    Commit your changes (git commit -m 'Add some feature')

    Push to the branch (git push origin feature/AmazingFeature)

    Open a Pull Request

## 🔗 Recommended Frontend
For the complete BookStack application, check out the [BookStack Frontend Repository](https://github.com/zohra-b/S5.02.bookstack-front.git).

