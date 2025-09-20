# Krishi Saarathi Backend API

🌾 **Partner in Prosperity** - A comprehensive agricultural platform connecting farmers, drivers, markets, and administrators.

## 📋 Project Overview

Krishi Saarathi is a farmer-first digital platform designed to transform Indian agriculture through a 360° integrated ecosystem. This Spring Boot backend provides robust APIs for user management, role-based access control (RBAC), and agricultural services.

### 🎯 Key Features

- **Multi-Role Support**: ADMIN, FARMER, DRIVER, MARKET users
- **JWT-based Authentication** (Ready for implementation)
- **PostgreSQL Database** with optimized relationships
- **RESTful API Design** with comprehensive endpoints
- **Role-Based Access Control (RBAC)**
- **Production-Ready Architecture**

## 🛠️ Technology Stack

### Backend Framework
- **Spring Boot 3.5.5** - Main application framework
- **Spring Data JPA** - Database operations and ORM
- **Spring Security** - Authentication and authorization
- **Spring Validation** - Input validation

### Database
- **PostgreSQL 17.6** - Primary database
- **Hibernate** - ORM implementation
- **HikariCP** - Connection pooling

### Development Tools
- **Java 17+** - Programming language
- **Maven** - Build and dependency management
- **Lombok** - Reducing boilerplate code
- **Jackson** - JSON serialization/deserialization

### Additional Libraries
- **JWT (io.jsonwebtoken)** - Token-based authentication
- **Validation API** - Input validation annotations

## 🚀 Getting Started

### Prerequisites
- **Java 17 or higher**

- **PostgreSQL 13+**

- **Maven 3.6+**

- **Git**

### Installation & Setup

1. **Clone the repository**
- git clone **https://github.com/agristartuprecords99-maker/Krishi_saarathi_app_V1.git**
- cd Krishi_saarathi_app_V1


2. **Database Setup**

- CREATE DATABASE krishi_saarathi;


3. **Configure Database Connection**

Update `src/main/resources/application.properties`:

spring.datasource.url=jdbc:postgresql://localhost:5432/krishi_saarathi
spring.datasource.username=postgres
spring.datasource.password=your_password_here



4. **Build and Run**

Using Maven Wrapper
- ./mvnw clean install
- ./mvnw spring-boot:run

Or using Maven (if installed globally)
- mvn clean install
- mvn spring-boot:run


5. **Access the API**

Base URL: `http://localhost:8080/api/v1`

## 📊 Database Schema

### Core Entities

| Entity | Description | Key Relationships |
|--------|------------|-------------------|
| **roles** | System roles (ADMIN, FARMER, DRIVER, MARKET) | One-to-Many with users |
| **users** | All platform users with authentication | Many-to-One with roles, One-to-One with user_profiles |
| **user_profiles** | Extended user information | One-to-One with users |

### Default Roles

- **ADMIN**: System Administrator with full platform access
- **FARMER**: Agricultural producers using farming services
- **DRIVER**: Transportation providers for agricultural logistics
- **MARKET**: Product vendors managing sales and inventory

## 🔌 API Endpoints

### Authentication & User Management

| Method | Endpoint | Description | Status |
|--------|----------|------------|--------|
| POST | `/users/register` | Register new user | ✅ Working |
| GET | `/users/roles` | Get all available roles | ✅ Working |
| GET | `/users` | Get all users (admin) | ✅ Working |
| GET | `/users/stats` | Get user statistics by role | ✅ Working |
| GET | `/users/role/{roleName}` | Get users by specific role | ✅ Working |

### Sample API Usage

#### Register a New User

- POST /api/v1/users/register
- Content-Type: application/json

{
"email": "farmer1@krishi.com",
"password": "password123",
"firstName": "Ravi",
"lastName": "Kumar",
"phoneNumber": "9876543210",
"role": "FARMER"
}


#### Get User Statistics

### GET /api/v1/users/stats

## Response:
{
"totalUsers": 1,
"adminCount": 0,
"farmerCount": 1,
"driverCount": 0,
"marketCount": 0
}



## 🔒 Security Features

- **CORS enabled** for cross-origin requests
- **JWT token support** (ready for implementation)
- **Password validation** (minimum 6 characters)
- **Email validation** with unique constraints
- **SQL injection prevention** through JPA
- **Input validation** with Bean Validation

## 📈 Current Implementation Status

### ✅ Completed Features

- [x] **Database Setup**: PostgreSQL with proper schema
- [x] **User Management**: Registration, retrieval, role assignment
- [x] **RBAC System**: Four distinct user roles
- [x] **Data Initialization**: Automatic role creation on startup
- [x] **API Documentation**: Comprehensive endpoint coverage
- [x] **Error Handling**: Proper JSON responses
- [x] **Validation**: Input validation and constraints

### 🚧 Upcoming Features (Phase 2)

- [ ] **JWT Authentication**: Complete implementation
- [ ] **User Profiles**: Extended profile management
- [ ] **Agricultural Services**: Crop advisory APIs
- [ ] **Transportation**: Driver and logistics management
- [ ] **Marketplace**: Product and order management
- [ ] **Real-time Features**: WebSocket integration
- [ ] **File Upload**: Image and document handling

## 🧪 Testing the APIs

### Using Browser (GET requests)
- Roles: `http://localhost:8080/api/v1/users/roles`
- Stats: `http://localhost:8080/api/v1/users/stats`
- Users: `http://localhost:8080/api/v1/users`

### Using Postman/Curl (POST requests)
Use the sample JSON above for user registration.

## 👥 Contributing

1. Create a feature branch: `git checkout -b feature/new-feature`
2. Make changes and commit: `git commit -m "Add new feature"`
3. Push to branch: `git push origin feature/new-feature`
4. Create a Pull Request

## 📄 License

This project is part of Krishi Saarathi agricultural platform.

## 🤝 Team

- **Karthik K R** - Founder & CEO, Lead Developer
- Built with passion for transforming Indian agriculture

---

**🌾 Krishi Saarathi - Partner in Prosperity 🌾**
