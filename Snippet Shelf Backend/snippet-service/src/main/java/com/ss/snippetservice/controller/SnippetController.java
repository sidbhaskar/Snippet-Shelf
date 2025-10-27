package com.ss.snippetservice.controller;

import com.ss.snippetservice.dto.SnippetRequestDTO;
import com.ss.snippetservice.dto.SnippetResponseDTO;
import com.ss.snippetservice.service.SnippetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/snippets")
@Tag(name = "Snippet", description = "API for managing Code Snippets")
public class SnippetController {

    private final SnippetService snippetService;

    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping("/check")
    @Operation(summary = "Health check endpoint")
    public ResponseEntity<String> checkSnippet() {
        return ResponseEntity.ok("Snippet API is working");
    }

    @GetMapping
    @Operation(summary = "Get all snippets with pagination and sorting")
    public ResponseEntity<Page<SnippetResponseDTO>> getSnippets(
            @Parameter(description = "Sort order: 'recent' or 'popular'")
            @RequestParam(defaultValue = "recent") String sort,

            @Parameter(description = "Number of snippets per page")
            @RequestParam(defaultValue = "6") int limit,

            @Parameter(description = "Number of snippets to skip")
            @RequestParam(defaultValue = "0") int offset
    ) {
        Page<SnippetResponseDTO> snippets = snippetService.getSnippets(sort, limit, offset);
        return ResponseEntity.ok(snippets);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all snippets without pagination")
    public ResponseEntity<List<SnippetResponseDTO>> getAllSnippets() {
        List<SnippetResponseDTO> snippets = snippetService.getSnippets();
        return ResponseEntity.ok(snippets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get snippet by ID")
    public ResponseEntity<SnippetResponseDTO> getSnippetById(@PathVariable UUID id) {
        try {
            SnippetResponseDTO snippet = snippetService.getSnippetById(id);
            return ResponseEntity.ok(snippet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new snippet")
    public ResponseEntity<SnippetResponseDTO> createSnippet(
            @Valid @RequestBody SnippetRequestDTO snippetRequestDTO
    ) {
        SnippetResponseDTO snippetResponseDTO = snippetService.createSnippet(snippetRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(snippetResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing snippet")
    public ResponseEntity<SnippetResponseDTO> updateSnippet(
            @PathVariable UUID id,
            @Valid @RequestBody SnippetRequestDTO snippetRequestDTO
    ) {
        try {
            SnippetResponseDTO snippetResponseDTO = snippetService.updateSnippet(snippetRequestDTO, id);
            return ResponseEntity.ok(snippetResponseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/favorite")
    @Operation(summary = "Toggle favorite status of a snippet")
    public ResponseEntity<SnippetResponseDTO> toggleFavorite(@PathVariable UUID id) {
        try {
            SnippetResponseDTO snippetResponseDTO = snippetService.toggleFavorite(id);
            return ResponseEntity.ok(snippetResponseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/favorites")
    @Operation(summary = "Get all favorite snippets")
    public ResponseEntity<List<SnippetResponseDTO>> getFavoriteSnippets() {
        List<SnippetResponseDTO> favorites = snippetService.getFavoriteSnippets();
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a snippet")
    public ResponseEntity<Void> deleteSnippet(@PathVariable UUID id) {
        try {
            snippetService.deleteSnippet(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}