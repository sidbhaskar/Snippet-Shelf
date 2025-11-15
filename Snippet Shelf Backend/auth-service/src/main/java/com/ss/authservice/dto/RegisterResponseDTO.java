package com.ss.authservice.dto;

import java.time.LocalDate;
import java.util.UUID;

public class RegisterResponseDTO {
    private UUID id;
    private String email;
    private String role;
    private LocalDate createdDate;

    public RegisterResponseDTO(UUID id, String email, String role, LocalDate createdDate) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.createdDate = createdDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}