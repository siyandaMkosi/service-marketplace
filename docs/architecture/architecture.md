# Service Marketplace Architecture

## Purpose

The Service Marketplace API is a production-style backend built to demonstrate enterprise software engineering practices using Java and Spring Boot.

Rather than focusing solely on implementing features, this project emphasizes clean architecture, maintainability, scalability, security, and cloud readiness.

---

# Architecture Goals

The application has been designed around the following principles:

* Separation of concerns
* Single Responsibility Principle (SRP)
* Open/Closed Principle (OCP)
* Dependency Injection
* Domain-driven package organization
* Secure authentication and authorization
* Scalability
* Testability

---

# Architectural Style

The project follows a layered architecture with package-by-feature organization.

Request

↓

Controller

↓

Facade

↓

Service

↓

Repository

↓

Database

The Facade layer coordinates business workflows while Services remain focused on a single responsibility.

---

# Design Decisions

## Why JWT?

JWT provides stateless authentication for API clients while keeping the application horizontally scalable.

---

## Why Refresh Tokens?

Access tokens are intentionally short-lived.

Refresh tokens allow secure token renewal without requiring users to log in repeatedly.

Refresh tokens are stored in the database to support:

* Logout
* Logout from all devices
* Session revocation
* Device management
* Refresh token rotation

---

## Why Persistent Sessions?

Many JWT tutorials use completely stateless authentication.

This project intentionally stores user sessions because it enables enterprise features such as:

* Active sessions
* Device history
* Forced logout
* Token revocation
* Security auditing

---

## Why AuthenticationFacade?

Authentication requires multiple services:

* User lookup
* Password verification
* JWT generation
* Session creation
* Response mapping

The Facade coordinates these operations while individual services remain focused on one responsibility.

---

## Why MapStruct?

MapStruct generates compile-time mappers instead of relying on reflection.

Benefits include:

* Better performance
* Compile-time validation
* Cleaner service classes
* Easier maintenance

---

## Why BaseEntity?

Most entities require common auditing information.

The BaseEntity centralizes:

* Primary key
* Created timestamp
* Updated timestamp

This avoids duplication across entities.

---

## Package Structure

The project follows a package-by-feature approach instead of package-by-layer.

Example:

auth/
user/
common/
security/

Each feature contains its own controllers, services, repositories, DTOs, mappers, and exceptions.

This improves maintainability as the project grows.

---

# Future Architecture

The architecture has been designed to support future modules including:

* Provider Management
* Customer Management
* Service Listings
* Bookings
* Payments
* Messaging
* Notifications
* Reviews
* Administration
* Analytics

These modules will follow the same architectural principles established by the authentication module.
