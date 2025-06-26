# 🎓 EduSphere - Learning Management System

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

EduSphere is a comprehensive monolithic Learning Management System (LMS) inspired by Google Classroom, Moodle, and Canvas. Built with enterprise-grade Java technologies, it focuses on robust business logic, multi-layered user roles, and efficient state management within a single, well-structured monolith.

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Tech Stack](#-tech-stack)
- [Features](#-features)
- [Installation Instructions](#-installation-instructions)
- [Database Setup](#-database-setup)
- [API Documentation](#-api-documentation)
- [User Roles & Permissions](#-user-roles--permissions)
- [Example Credentials](#-example-credentials)
- [Project Structure](#-project-structure)
- [Contributing](#-contributing)
- [Contact](#-contact)

## 🎯 Project Overview

EduSphere is intentionally designed as a monolithic application that demonstrates:

- **Complex Business Logic**: Multi-layered academic workflows and processes
- **Role-Based Access Control**: Four distinct user roles with granular permissions
- **State Management**: Comprehensive submission lifecycle and course management
- **Academic Relationships**: Parent-student linking and progress monitoring
- **Reporting System**: Detailed performance analytics and course reports

### Why Monolithic?

This project deliberately avoids modern trends like microservices, real-time communication, or event-driven architectures to focus on:
- Solid foundational architecture patterns
- Complex business domain modeling
- Traditional but robust state management
- Comprehensive security implementation

## 🛠 Tech Stack

### Backend
- **Java 17** - Modern LTS version with enhanced features
- **Spring Boot 3.5.3** - Application framework and dependency injection
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence layer
- **Hibernate** - ORM framework
- **Maven** - Dependency management and build tool

### Database
- **PostgreSQL** - Primary relational database
- **HikariCP** - Connection pooling (via Spring Boot)

### Additional Libraries
- **Lombok** - Boilerplate code reduction
- **MapStruct 1.5.5** - Bean mapping
- **JWT (JJWT) 0.12.6** - Token-based authentication
- **SpringDoc OpenAPI 2.8.9** - API documentation
- **Spring Boot DevTools** - Development utilities

### Documentation & Testing
- **Swagger UI** - Interactive API documentation
- **Spring Boot Test** - Testing framework
- **Spring Security Test** - Security testing utilities

## ✨ Features

### 👨‍💼 Admin Features
- Complete user management (CRUD operations)
- System-wide course management
- Comprehensive reporting and analytics
- User role assignment and permissions
- System configuration and monitoring

### 👩‍🏫 Teacher Features
- Course creation and management
- Lesson content upload and organization
- Assignment creation with custom criteria
- Student submission review and grading
- Class performance tracking and reports

### 👨‍🎓 Student Features
- Course enrollment and browsing
- Lesson content access and viewing
- Assignment submission with file uploads
- Grade tracking and performance monitoring
- Personal academic dashboard

### 👨‍👩‍👧‍👦 Parent Features
- Child's academic progress monitoring
- Attendance tracking and reports
- Grade and assignment visibility
- Communication with teachers (future enhancement)
- Multi-child account management

### 🔄 System Features
- **Submission Lifecycle**: Pending Review → Graded → Revision Requested
- **Parent-Student Relationships**: Secure linking and monitoring
- **Course Performance Reports**: Average scores and analytics
- **Role-Based Security**: Granular access control
- **RESTful API**: Clean, documented endpoints

## 🚀 Installation Instructions

### Prerequisites
- **Java 17** or higher
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Git**

### Step 1: Clone the Repository
```bash
git clone https://github.com/MuminjonovAsrorbek/EduSphere.git
cd EduSphere
```

### Step 2: Database Setup
1. Install and start PostgreSQL
2. Create a database named `edusphere`:
```sql
CREATE DATABASE edusphere;
```

### Step 3: Configure Application Properties
Update `src/main/resources/application.yaml` with your database credentials:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/edusphere
    username: your_username
    password: your_password
```

### Step 4: Build and Run
```bash
# Build the project
mvn clean compile

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Step 5: Access Swagger Documentation
Visit `http://localhost:8080/swagger-ui.html` for interactive API documentation.

## 🗄 Database Setup

### Automatic Schema Creation
The application uses Hibernate's `ddl-auto: update` setting to automatically create and update database tables on startup.

### Initial Data
The system automatically creates a default admin user on first startup:

```sql
-- Default admin user (created automatically)
INSERT INTO users (full_name, username, email, password, role) 
VALUES ('Administrator', 'admin', 'admin@gmail.com', '$2a$10$...', 'ADMIN');
```

### Manual Database Initialization (Optional)
If you prefer manual setup, here's the core schema:

```sql
-- Users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Courses table
CREATE TABLE courses (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    teacher_id BIGINT REFERENCES users(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Additional tables are created automatically by Hibernate
```

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/v1
```

### Authentication
All protected endpoints require a JWT token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

### Key Endpoints

#### Authentication
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin"
}
```

#### Course Management
```http
# Get all courses (Admin only)
GET /api/v1/course?page=0

# Get course by ID
GET /api/v1/course/{id}

# Create new course (Admin only)
POST /api/v1/course
Content-Type: application/json

{
  "title": "Introduction to Java",
  "description": "Basic Java programming concepts",
  "teacherId": 2
}
```

#### User Management
```http
# Get all users (Admin only)
GET /api/v1/user?page=0

# Create new user (Admin only)
POST /api/v1/user
Content-Type: application/json

{
  "fullName": "John Doe",
  "username": "johndoe",
  "email": "john@example.com",
  "password": "password123",
  "role": "STUDENT"
}
```

#### Assignment & Submissions
```http
# Create assignment (Teacher/Admin)
POST /api/v1/assignment

# Submit assignment (Student)
POST /api/v1/submission

# Grade submission (Teacher/Admin)
PUT /api/v1/submission/{id}/grade
```

### Complete API Documentation
Visit `/swagger-ui.html` after starting the application for complete interactive documentation.

## 🔐 User Roles & Permissions

| Feature | Admin | Teacher | Student | Parent |
|---------|-------|---------|---------|--------|
| User Management | ✅ Full | ❌ | ❌ | ❌ |
| Course Management | ✅ Full | ✅ Own courses | ❌ | ❌ |
| Lesson Management | ✅ Full | ✅ Own lessons | 👁 View enrolled | 👁 View child's |
| Assignment Creation | ✅ Full | ✅ Own courses | ❌ | ❌ |
| Assignment Submission | ❌ | ❌ | ✅ | ❌ |
| Grading | ✅ Full | ✅ Own assignments | ❌ | ❌ |
| Enrollment Management | ✅ Full | ✅ Own courses | ✅ Self-enroll | ❌ |
| Reports & Analytics | ✅ System-wide | ✅ Own courses | 👁 Own progress | 👁 Child's progress |
| Parent-Student Linking | ✅ Full | ❌ | ❌ | ✅ Own children |

### Permission Levels
- ✅ **Full Access**: Complete CRUD operations
- 👁 **View Only**: Read-only access
- ❌ **No Access**: Endpoint not accessible

## 🔑 Example Credentials

### Default Admin Account
```
Username: admin
Password: admin
Role: ADMIN
Email: admin@gmail.com
```

### Test Accounts (Create via API or add to DataInitializer)

#### Teacher Account
```
Username: teacher1
Password: teacher123
Role: TEACHER
Email: teacher1@edusphere.com
```

#### Student Account
```
Username: student1
Password: student123
Role: STUDENT
Email: student1@edusphere.com
```

#### Parent Account
```
Username: parent1
Password: parent123
Role: PARENT
Email: parent1@edusphere.com
```

> ⚠️ **Security Note**: Change default passwords in production environments!

## 📁 Project Structure

```
src/main/java/uz/dev/edusphere/
├── 📁 config/                 # Configuration classes
│   ├── security/              # Security configuration
│   └── OpenApiConfig.java     # Swagger configuration
├── 📁 controller/             # REST controllers
│   ├── AuthController.java    # Authentication endpoints
│   ├── UserController.java    # User management
│   ├── CourseController.java  # Course management
│   └── ...                    # Other controllers
├── 📁 dto/                    # Data Transfer Objects
│   ├── request/               # Request DTOs
│   ├── response/              # Response DTOs
│   └── *.java                 # Entity DTOs
├── 📁 entity/                 # JPA entities
│   ├── User.java              # User entity
│   ├── Course.java            # Course entity
│   └── ...                    # Other entities
├── 📁 enums/                  # Enumerations
│   ├── Role.java              # User roles
│   └── SubmissionStatus.java  # Submission states
├── 📁 service/                # Business logic
│   ├── template/              # Service interfaces
│   ├── security/              # Security services
│   └── *ServiceImpl.java      # Service implementations
├── 📁 repository/             # Data access layer
├── 📁 mapper/                 # MapStruct mappers
├── 📁 utils/                  # Utility classes
├── 📁 exceptions/             # Custom exceptions
└── EduSphereApplication.java  # Main application class
```

