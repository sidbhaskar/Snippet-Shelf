package com.SnippetShelf.snippet_shelf_backend.dto;

import com.SnippetShelf.snippet_shelf_backend.models.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private String name;
    private String imageUrl;
    private UserRole role;
}
