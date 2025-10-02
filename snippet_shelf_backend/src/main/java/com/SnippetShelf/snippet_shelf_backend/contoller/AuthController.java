package com.SnippetShelf.snippet_shelf_backend.contoller;


import com.SnippetShelf.snippet_shelf_backend.dto.UserDto;
import com.SnippetShelf.snippet_shelf_backend.models.User;
import com.SnippetShelf.snippet_shelf_backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getCurrentUser(@PathVariable String username) {
        User user = userService.findUser(username);
        UserDto currentUser = convertToDto(user);
        return ResponseEntity.ok(currentUser);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setImageUrl(user.getImageUrl());
        dto.setRole(user.getRole());
        return dto;
    }
}
