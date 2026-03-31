# 🛒 E-Commerce Backend API

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring
Boot](https://img.shields.io/badge/SpringBoot-3.x-green)
![Build](https://img.shields.io/badge/Build-Maven-orange)
![Status](https://img.shields.io/badge/Status-Active-success)

------------------------------------------------------------------------

## 📌 Overview

This is a **production-ready E-Commerce Backend API** built using
**Spring Boot**.\
It supports authentication, cart, product, and order management with
clean architecture and best practices.

------------------------------------------------------------------------

## 🚀 Features

-   🔐 JWT Authentication (Login / Register / Refresh Token)
-   👤 Role-Based Access (USER, ADMIN, VENDOR)
-   🛍 Product & Category Management
-   🛒 Cart & Cart Items
-   📦 Order & Order Items
-   🔄 Token Revocation System
-   ❗ Global Exception Handling
-   📄 Standard API Response Wrapper
-   ✅ Input Validation

------------------------------------------------------------------------

## 🏗️ Tech Stack

Layer        Technology
  ------------ -----------------------
Language     Java 17
Framework    Spring Boot
Security     Spring Security + JWT
ORM          Hibernate / JPA
Database     MySQL
Build Tool   Maven

------------------------------------------------------------------------

## 📂 Project Structure

com.ecommerce ├── controllers ├── services │ ├── interfaces │ └── impl
├── repositories ├── entities ├── dtos ├── configures ├── errorhandlers

------------------------------------------------------------------------

## 🔐 Authentication APIs

Method   Endpoint
  -------- --------------------
POST     /api/register
POST     /api/login
POST     /api/refresh-token

------------------------------------------------------------------------

## 🛒 Cart APIs

Method   Endpoint
  -------- ------------------
POST     /api/cart/add
PUT      /api/cart/update
DELETE   /api/cart/remove
DELETE   /api/cart/clear
GET      /api/cart

------------------------------------------------------------------------

## 📦 Order APIs

Method   Endpoint
  -------- -------------------------
POST     /api/orders/place
GET      /api/orders
GET      /api/orders/{id}
PUT      /api/orders/cancel/{id}

------------------------------------------------------------------------

## 📥 Sample Request

``` json
{
  "firstName": "dave",
  "lastName": "scott",
  "email": "dave@test.com",
  "password": "password@123"
}
```

------------------------------------------------------------------------

## 📤 Standard Response

``` json
{
  "success": true,
  "message": "Order placed successfully",
  "status": 201,
  "data": {}
}
```

------------------------------------------------------------------------

## ❗ Error Response

``` json
{
  "message": "Invalid credentials",
  "status": 401,
  "timestamp": "2026-03-30T16:25:09"
}
```

------------------------------------------------------------------------

## ⚙️ Configuration

``` properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

application.security.jwt.secret-key=your_secret
```

------------------------------------------------------------------------

## 🔄 Flow

1.  Register / Login\
2.  Receive JWT Token\
3.  Add products to cart\
4.  Place order\
5.  Cart cleared automatically

------------------------------------------------------------------------

## 🌟 Future Enhancements

-   💳 Payment Gateway
-   🔍 Search & Filters
-   ⭐ Reviews & Ratings
-   📦 Order Tracking
-   📊 Admin Dashboard

------------------------------------------------------------------------

## 👨‍💻 Author

Shravan Kumar\
Java Backend Developer\
Focused on building scalable backend systems using Spring Boot.\
📧 bshravankumar0@gmail.com\
🔗 LinkedIn: https://www.linkedin.com/in/shravan-kumar-a0b9ab226 \
💻 GitHub: https://github.com/ShravanKumar24

------------------------------------------------------------------------

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
