# 🏥 Healthcare Microservices System (Spring Boot, gRPC, Kafka, Docker, JWT Auth)

A full-featured **microservices-based healthcare platform** built using **Spring Boot**, **gRPC**, **Kafka**, **Docker**, and **JWT-based Authentication**.  
This project demonstrates how to design and build a **scalable, event-driven architecture** with multiple services communicating over **REST, gRPC, and Kafka** — fully containerized and orchestrated with Docker.

---

## 📘 Table of Contents

- [Overview](#-overview)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Services](#-services)
- [Setup & Installation](#-setup--installation)
- [API Gateway & Authentication](#-api-gateway--authentication)
- [Kafka Event Streaming](#-kafka-event-streaming)
- [gRPC Communication](#-grpc-communication)
- [Testing](#-testing)
- [OpenAPI & Documentation](#-openapi--documentation)
- [Dockerization](#-dockerization)

---

## 🧠 Overview

This project simulates a **healthcare system** with multiple interacting services:

- **Patient Service** – manages patient data and communicates with other services.  
- **Billing Service** – provides billing calculations over gRPC.  
- **Analytics Service** – consumes patient data events from Kafka for analytics.  
- **Auth Service** – provides JWT-based authentication for secure API access.  
- **API Gateway** – routes and secures incoming requests to backend services.

It’s designed to illustrate **real-world microservice communication** patterns:  
✅ REST APIs  
✅ gRPC inter-service calls  
✅ Kafka-based event streaming  
✅ JWT authentication  
✅ Centralized API Gateway  
✅ Docker containerization

---

## 🧩 Architecture


                          ┌────────────────────────┐
                          │      API Gateway       │
                          │  (Spring Cloud Gateway)│
                          └──────────┬─────────────┘
                                     │
                ┌────────────────────┼────────────────────┐
                │                    │                    │
       ┌────────▼────────┐   ┌────────▼───────┐   ┌────────▼────────┐
       │ Patient Service │   │ Auth Service   │   │ Analytics Svc   │
       │ (REST + gRPC)   │   │ (JWT + DB)     │   │ (Kafka Consumer)│
       └────────┬────────┘   └────────────────┘   └────────┬────────┘
                │                                           │
                │                                           │
       ┌────────▼────────┐                         ┌────────▼─────────┐
       │ Billing Service │                         │   Kafka Broker   │
       │   (gRPC Server) │                         │  (Patient Events)│
       └─────────────────┘                         └──────────────────┘

# Technology Stack

| Layer                       | Technology                                    |
|-----------------------------|-----------------------------------------------|
| Language                    | Java 17                                       |
| Framework                   | Spring Boot 3.x                               |
| Inter-Service Communication | gRPC                                          |
| Messaging                   | Apache Kafka                                  |
| Authentication              | Spring Security + JWT                         |
| API Gateway                 | Spring Cloud Gateway                          |
| Database                    | PostgreSQL / H2 (for development)             |
| Containerization            | Docker                                        |
| Build Tool                  | Maven                                         |
| Testing                     | JUnit 5, Spring Boot Test                     |
| Documentation               | OpenAPI (Swagger)                             |
