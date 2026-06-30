# Sprint 1 – Authentication Foundation

## Sprint Goal

Establish the foundation of the Service Marketplace backend by implementing secure authentication, project architecture, and shared infrastructure.

---

# Objectives

* Create the Spring Boot project
* Configure project dependencies
* Configure MySQL
* Configure Swagger/OpenAPI
* Implement JWT authentication
* Create user registration
* Implement login
* Standardize API responses
* Establish project architecture

---

# Completed Work

## Project Setup

* Spring Boot project created
* Maven configured
* MySQL integration
* Swagger/OpenAPI configured
* Environment configuration
* Git repository initialized

---

## Authentication

Implemented:

* User registration
* Password encryption using BCrypt
* Login endpoint
* JWT access token generation
* Protected endpoints

---

## Shared Infrastructure

Implemented:

* BaseEntity
* Global exception handling
* Standard API response format
* ApiErrorResponse
* MapStruct
* DTO layer
* Mapper layer

---

## Architecture

Introduced:

* Repository Pattern
* Service Layer
* Facade Pattern
* DTO Pattern
* Clean package structure

---

# Challenges Encountered

During this sprint several issues were identified and resolved:

* MySQL authentication configuration
* Swagger security configuration
* Spring Security endpoint permissions
* Bean definition conflicts
* GitHub's authentication using SSH
* Environment variable configuration

Each issue was resolved before moving to the next feature to maintain a stable codebase.

---

# Sprint Outcome

Sprint 1 delivered a secure authentication foundation and established the architectural standards that will be followed throughout the remainder of the project.

This sprint intentionally focused on building reusable infrastructure before implementing marketplace-specific functionality.
