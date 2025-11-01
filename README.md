# Snippet Shelf

Snippet Shelf is a powerful, cloud-native platform for developers to store, manage, and share code snippets. Built on a robust microservice architecture, it's designed for scalability, resilience, and performance.

---

## ✨ Core Features

* **Snippet Management**: Create, edit, delete, and tag code snippets with syntax highlighting.
* **User Authentication**: Secure sign-up and sign-in using JWT-based authentication.
* **Collections & Teams**: Organize snippets into personal or team-based collections for easy collaboration.
* **Asynchronous Processing**: Event-driven architecture using Kafka for generating activity feeds, analytics, and notifications.
* **Advanced Search**: Full-text search capabilities across all snippets powered by Elasticsearch/OpenSearch.
* **Scalable by Design**: Each component is a separate microservice, allowing for independent scaling and deployment.

---

## 🏛️ System Architecture

Snippet Shelf is built using a microservice architecture. The system is composed of several independent services that communicate with each other through a combination of synchronous (gRPC) and asynchronous (Apache Kafka) protocols. An API Gateway serves as the single entry point for all client requests.

# 🧩 Microservices Overview

## Services

### **Auth Service**
- Manages user sign-up/sign-in
- Handles user profiles
- Issues JWT tokens

### **Snippet Service**
- Core service for CRUD operations on snippets and tags

### **Collection Service**
- Manages collections of snippets

### **Team Service**
- Handles team creation, membership, and permissions

### **Activity Service**
- Creates and manages user activity feeds from events

### **Analytics Service**
- Aggregates usage data and provides insights

### **API Gateway**
- Single entry point for routing, authentication, and rate limiting

### **Search Service**
- Provides full-text search capabilities

---

## 💻 Technology Stack

| **Category**         | **Technology** |
|-----------------------|----------------|
| **Backend**           | Java 21+, Spring Boot 3.x |
| **Database**          | PostgreSQL (Primary)|
| **Communication**     | REST, gRPC, Apache Kafka |
| **Authentication**    | Keycloak / Spring Authorization Server (JWT-based) |
| **ORM & Migrations**  | Spring Data JPA (Hibernate)|
| **Deployment**        | Docker, Kubernetes|

---

## 📦 Architecture Highlights
- **Microservices-based** modular design for scalability and isolation  
- **Event-driven communication** using **Kafka**  
- **Secure authentication** with **JWT tokens**  
- **Full-text search** powered by **Elasticsearch/OpenSearch**  
- **Cloud-native deployment** with **Kubernetes & Helm**  


# 📄 API Documentation & Endpoints

The **API Gateway** exposes an **OpenAPI specification** for exploring the public REST endpoints.  
Once the services are running, you can access the interactive documentation via **Swagger UI**.

---

## ✂️ Snippet Operations

All snippet-related endpoints are available under the `/api/snippets` path.

| **Method** | **Endpoint** | **Description** |
|-------------|--------------|-----------------|
| `GET` | `/api/snippets` | Get all snippets (paginated). |
| `GET` | `/api/snippets/all` | Get all snippets (no pagination). |
| `GET` | `/api/snippets/{id}` | Get a single snippet by its ID. |
| `POST` | `/api/snippets` | Create a new snippet. |
| `PUT` | `/api/snippets/{id}` | Update an existing snippet. |
| `PATCH` | `/api/snippets/{id}/favorite` | Toggle the favorite status of a snippet. |
| `DELETE` | `/api/snippets/{id}` | Delete a snippet. |
| `GET` | `/api/snippets/favorites` | Get all snippets marked as favorites. |

---

## 🔍 Query Parameters

The primary `GET /api/snippets` endpoint supports the following query parameters for pagination and sorting:

| **Parameter** | **Description** | **Default** |
|----------------|------------------|--------------|
| `sort` | Defines the sorting order. Possible values: `recent`, `popular`. | `recent` |
| `limit` | Number of snippets to return per page. | `6` |
| `offset` | Number of snippets to skip from the beginning. | `0` |

---
