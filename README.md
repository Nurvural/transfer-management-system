# 💸 Transfer Management System (Spring Boot REST API)

This project is a sample bank transfer management system developed using Java and Spring Boot. Designed with RESTful architecture, it supports creating, listing, updating, and deleting transfers.

---

## 🔧 Technologies Used

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Spring Validation  
- H2 DB 
- Lombok  
- Postman 

---

## 📌 Features

- `GET /api/transfers` → Lists all transfers 
- `POST /api/transfers` → Creates a new transfer  
- `PUT /api/transfers/{id}` → Updates an existing transfer  
- `DELETE /api/transfers/{id}` → Deletes a transfer  
- Global Exception Handling via `@ControllerAdvice`
- DTO usage for separating API layer from data model
- Layered architecture (Controller, Service, Repository)
