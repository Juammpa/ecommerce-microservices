# 🛒 E-Commerce Microservices

A microservices-based e-commerce backend built with Java, Spring Boot, and Docker.

## 🏗️ Architecture

The system is composed of 3 independent microservices and an API Gateway:

- **product-service** (port 8081) — manages products and stock
- **order-service** (port 8082) — manages orders and communicates with product-service
- **gateway-api** (port 8080) — single entry point, routes requests to each service

Each microservice has its own MySQL database.
```bash
Client → API Gateway (8080)
├── /api/products/** → product-service (8081) → db-products
└── /api/orders/**  → order-service  (8082) → db-orders
```

## 🚀 Tech Stack

| Technology | Version |
|------------|---------|
| ☕ Java | 17 |
| 🍃 Spring Boot | 3.5.14 |
| ☁️ Spring Cloud Gateway | latest |
| 🗄️ Spring Data JPA + Hibernate | latest |
| 🐬 MySQL | 8.0 |
| 🐳 Docker + Docker Compose | latest |
| 🔧 Maven | 3.x |

## ✅ Prerequisites

- 🐳 Docker Desktop installed and running
- ☕ Java 17+
- 🔧 Maven 3.x

## ⚡ Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/Juammpa/ecommerce-microservices.git
cd ecommerce-microservices
```

### 2. Build the JAR files

```bash
mvn clean install -DskipTests
```

### 3. Start all services with Docker

```bash
docker-compose up --build
```

This will start:
- 🐬 2 MySQL databases
- 📦 product-service
- 📋 order-service
- 🌐 gateway-api

All requests go through the API Gateway on port **8080**.

## 📡 API Endpoints

All endpoints are accessible through the API Gateway at `http://localhost:8080`

### 📦 Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| 🟢 GET | `/api/products` | Get all products |
| 🟢 GET | `/api/products/{id}` | Get product by id |
| 🔵 POST | `/api/products` | Create a product |
| 🟡 PUT | `/api/products/{id}` | Update a product |
| 🔴 DELETE | `/api/products/{id}` | Delete a product |

### 📋 Orders

| Method | Endpoint | Description |
|--------|----------|-------------|
| 🟢 GET | `/api/orders` | Get all orders |
| 🟢 GET | `/api/orders/{id}` | Get order by id |
| 🔵 POST | `/api/orders` | Create an order |
| 🟡 PUT | `/api/orders/{id}/status` | Update order status |

### 📝 Request examples

**Create a product:**
```json
POST /api/products
{
    "name": "Laptop",
    "description": "Gaming laptop",
    "price": 999.99,
    "stock": 10
}
```

**Create an order:**
```json
POST /api/orders
{
    "productId": 1,
    "quantity": 2
}
```

**Update order status:**
```json
PUT /api/orders/1/status
{
    "status": "CONFIRMED"
}

```
## 🗄️ Sample Data

The **product-service** automatically loads sample data on startup:

| Id | Product | Price | Stock |
|---|---------|-------|-------|
| 1 | Laptop | $999.99 | 10 |
| 2 | Mouse | $29.99 | 50 |
| 3 | Keyboard | $79.99 | 30 |
| 4 | Monitor | $499.99 | 15 |
| 5 | Headphones | $199.99 | 20 |

> No manual setup needed — just run `docker-compose up --build` and the data will be ready.

## ⚙️ How it works

When an order is created, **order-service** communicates with **product-service** via HTTP to:
1. ✅ Verify the product exists
2. ✅ Validate available stock
3. 💰 Calculate the total price
4. 📉 Reduce the stock after the order is placed

## 📁 Project Structure
```bash
ecommerce/
├── 📦 product-service/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── 📋 order-service/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── 🌐 gateway-api/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── 🐳 docker-compose.yml
└── 📄 pom.xml
```
