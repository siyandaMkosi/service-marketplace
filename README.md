# Service Marketplace API

A production-style Service Marketplace platform built with Spring Boot that demonstrates modern backend engineering practices, clean architecture, secure authentication, and cloud-ready deployment.

The project is being developed incrementally using agile sprints, with each sprint focusing on delivering a complete, production-quality feature.

## Technology Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL
* JWT Authentication
* MapStruct
* Maven
* Swagger / OpenAPI

### Development Practices

* Clean Architecture
* SOLID Principles
* DTO Pattern
* Repository Pattern
* Service Layer Pattern
* Facade Pattern
* Global Exception Handling
* Standard API Response Format
* Conventional Commits
* Feature Branch Workflow

---

# Current Features

## Authentication

* User registration
* User login
* BCrypt password encryption
* JWT access tokens
* Refresh token support
* Persistent user sessions
* Device tracking
* Session metadata
* Authentication facade
* Custom authentication exceptions

## Infrastructure

* BaseEntity for shared auditing
* Global exception handling
* Standard API response structure
* MapStruct mapping layer
* Swagger API documentation

---

# Project Status

## Completed

* Authentication Module
* Session Management Infrastructure
* JWT Authentication
* Exception Handling
* DTO Mapping
* API Standardization

## In Progress

* Refresh Token Rotation
* Logout
* Active Sessions API

## Planned

* Provider Management
* Service Listings
* Booking System
* Payments
* Messaging
* Reviews & Ratings
* Notifications
* Admin Dashboard
* Analytics
* Docker Deployment
* Kubernetes Deployment
* AWS Deployment
* CI/CD Pipeline

---

# Project Structure

backend/

* authentication
* common
* configuration
* security
* users

docs/

* architecture
* api
* sprints

---

# Development Workflow

Each feature is developed using:

1. Feature branch
2. Sprint planning
3. Implementation
4. Testing
5. Documentation update
6. Pull Request
7. Merge into main

---

# Current Sprint

Sprint 2.2 — Refresh Token Rotation

Next milestone:
Enterprise Authentication Module v2.0
