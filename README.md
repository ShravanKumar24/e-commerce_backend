# 🛒 E-Commerce Backend (Spring Boot)

A secure and scalable **E-Commerce Backend API** built using **Java and Spring Boot**.
This project implements authentication, authorization, and core backend architecture for an e-commerce system.

---

# 🚀 Tech Stack

* Java 17+
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* MySQL / PostgreSQL
* Maven
* Lombok

---

# 🔐 Authentication Features

✔ User Signup
✔ User Login
✔ JWT Token Authentication
✔ Secure Password Encryption (BCrypt)
✔ Token Revocation / Logout
✔ Custom Password Validation
✔ Global Exception Handling

---

# 📂 Project Structure

```
com.ecommerce
│
├── config
│   └── SecurityConfigure
│
├── controllers
│   ├── LoginController
│   └── Demo
│
├── dtos
│   ├── AuthenticationResponse
│   ├── SignInDto
│   ├── SignupDto
│   ├── ChangePassword
│   └── ErrorResponse
│
├── entities
│   ├── User
│   ├── Role
│   ├── Address
│   ├── Token
│   └── TokenType
│
├── repositories
│   ├── UserRepo
│   └── TokenRepo
│
├── services
│   ├── interfaces
│   │   ├── JwtService
│   │   ├── LoginService
│   │   ├── LogoutService
│   │   └── UserService
│   │
│   └── implementations
│       ├── JwtServiceImpl
│       ├── LoginServiceImpl
│       └── LogoutService
│
├── security
│   └── JwtFilter
│
├── validation
│   ├── ValidPassword
│   └── PasswordValidator
│
└── errorhandlers
    └── GlobalExceptionHandler
```

---

# 🔑 Authentication Flow

```
Client Request
      │
      ▼
Controller
      │
      ▼
Service Layer
      │
      ▼
Spring Security Filter (JWT)
      │
      ▼
Database
```

### Login Flow

1. User sends email & password.
2. AuthenticationManager verifies credentials.
3. System generates JWT token.
4. Token returned to client.
5. Client sends JWT in Authorization header for protected APIs.

Example:

```
Authorization: Bearer <JWT_TOKEN>
```

---

# 📦 API Endpoints

### Authentication APIs

| Method | Endpoint             | Description       |
|--------|----------------------|-------------------|
| POST   | `/api/signup`        | Register new user |
| POST   | `/api/login`         | Authenticate user |
| POST   | `/api/logout`        | Logout user       |
| POST   | `/api/refresh-token` | Refresh Token     |

---

# 🔐 Password Security

Passwords are encrypted using:

BCryptPasswordEncoder

Rules enforced:

* Minimum 8 characters
* At least one number
* At least one special character

---

# ⚙️ Configuration

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# ▶️ Running the Project

Clone the repository

```
git clone https://github.com/yourusername/ecommerce-backend.git
```

Navigate to project folder

```
cd ecommerce-backend
```

Run the application

```
mvn spring-boot:run
```

Application will start on:

```
http://localhost:8080
```

---

# 🧪 Testing APIs

You can test APIs using:

* Postman
* Swagger
* Curl

---

# 📌 Future Improvements

* Product Management APIs
* Order Management
* Payment Integration
* Email OTP Authentication
* Refresh Token Support
* Role Based Authorization (Admin / Seller / Customer)

---

# 👨‍💻 Author

**Shravan Kumar**

Java Backend Developer
Focused on building scalable backend systems using Spring Boot.
