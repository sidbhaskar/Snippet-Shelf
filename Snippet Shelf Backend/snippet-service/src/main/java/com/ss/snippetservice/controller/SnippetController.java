package com.ss.snippetservice.controller;

import com.ss.snippetservice.dto.SnippetRequestDTO;
import com.ss.snippetservice.dto.SnippetResponseDTO;
import com.ss.snippetservice.service.SnippetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/snippet")

@Tag(name = "Snippet", description = "API for managing Code Snippets")
public class SnippetController {

    private final SnippetService snippetService;

    public SnippetController(SnippetService snippetService){
        this.snippetService = snippetService;
    }

    @GetMapping("/check")
    public String checkSnippet() {
        return "Api working";
    }

    @GetMapping
    @Operation(summary = "Get Snippets")
    public ResponseEntity<List<SnippetResponseDTO>> getSnippets(){
        List<SnippetResponseDTO> snippets = snippetService.getSnippets();
        return ResponseEntity.ok().body(snippets);
    }

    @PostMapping
    @Operation(summary = "Create a new snippet")
    public ResponseEntity<SnippetResponseDTO> createSnippet(@RequestBody SnippetRequestDTO snippetRequestDTO){
        SnippetResponseDTO snippetResponseDTO = snippetService.createSnippet(snippetRequestDTO);
        return ResponseEntity.ok().body(snippetResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a snippet")
    public ResponseEntity<SnippetResponseDTO> updateSnippet(
            @PathVariable UUID id,
            @RequestBody SnippetRequestDTO snippetRequestDTO){
        SnippetResponseDTO snippetResponseDTO = snippetService.updateSnippet(snippetRequestDTO, id);
        return ResponseEntity.ok().body(snippetResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a snippet")
    public ResponseEntity<SnippetResponseDTO> deleteSnippet(@PathVariable UUID id) {
        snippetService.deleteSnippet(id);
        return ResponseEntity.noContent().build();
    }




}
