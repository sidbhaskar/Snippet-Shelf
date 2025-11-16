package com.ss.snippetservice.service;

import com.ss.snippetservice.dto.SnippetRequestDTO;
import com.ss.snippetservice.dto.SnippetResponseDTO;
import com.ss.snippetservice.exception.SnippetNotFoundException;
import com.ss.snippetservice.model.Snippet;
import com.ss.snippetservice.model.Tag;
import com.ss.snippetservice.repository.SnippetRepository;
import com.ss.snippetservice.mapper.SnippetMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SnippetService {

    private final SnippetRepository snippetRepository;
    private final TagService tagService;

    public SnippetService(SnippetRepository snippetRepository, TagService tagService) {
        this.snippetRepository = snippetRepository;
        this.tagService = tagService;
    }

    // Get all snippets
    @Transactional(readOnly = true)
    public List<SnippetResponseDTO> getSnippets(UUID userId) {
        List<Snippet> snippetsList = snippetRepository.findByOwnerId(userId);
        return snippetsList.stream()
                .map(SnippetMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get snippets with pagination and sorting for a specific user
    @Transactional(readOnly = true)
    public Page<SnippetResponseDTO> getSnippets(UUID userId,String sort, int limit, int offset) {
        Sort sortOrder = Sort.by(Sort.Direction.DESC, "createdDate");

//        Sort sortOrder = "recent".equals(sort)
//                ? Sort.by(Sort.Direction.DESC, "createdDate")
//                : Sort.by(Sort.Direction.DESC, "createdDate");

        Pageable pageable = PageRequest.of(offset / limit, limit, sortOrder);
        Page<Snippet> snippetPage = snippetRepository.findByOwnerId(userId,pageable);

        return snippetPage.map(SnippetMapper::toDTO);
    }


     // Get snippet by ID (only if owned by user)
     @Transactional(readOnly = true)
    public SnippetResponseDTO getSnippetById(UUID id, UUID userId) {
        Snippet snippet = snippetRepository.findByIdAndOwnerId(id, userId)
                .orElseThrow(() -> new SnippetNotFoundException("Snippet not found with id: " + id));
        return SnippetMapper.toDTO(snippet);
    }

     // Create a new snippet with tags
    @Transactional
    public SnippetResponseDTO createSnippet(SnippetRequestDTO snippetRequestDTO, UUID userId) {
        Snippet snippet = SnippetMapper.toEntity(snippetRequestDTO);
        snippet.setOwnerId(userId);

        // Handle tags: get or create them
        if (snippetRequestDTO.getTags() != null && !snippetRequestDTO.getTags().isEmpty()) {
            Set<Tag> tags = tagService.getOrCreateTags(snippetRequestDTO.getTags());
            snippet.setTags(tags);
        }

        // Save snippet
        Snippet savedSnippet = snippetRepository.save(snippet);

        // TODO: Emit Kafka event (snippet.created) when event system is implemented

        return SnippetMapper.toDTO(savedSnippet);
    }

    /**
     * Update an existing snippet
     */
    @Transactional
    public SnippetResponseDTO updateSnippet(SnippetRequestDTO snippetRequestDTO, UUID id, UUID userId) {
        // Find existing snippet
        Snippet existingSnippet = snippetRepository.findByIdAndOwnerId(id, userId)
                .orElseThrow(() -> new SnippetNotFoundException("Snippet not found with id: " + id));

        SnippetMapper.updateEntity(existingSnippet, snippetRequestDTO);

        if (snippetRequestDTO.getTags() != null) {
            Set<Tag> updatedTags = tagService.getOrCreateTags(snippetRequestDTO.getTags());
            existingSnippet.setTags(updatedTags);
        }

        // Save updated snippet
        Snippet updatedSnippet = snippetRepository.save(existingSnippet);

        // TODO: Emit Kafka event (snippet.updated) when event system is implemented

        return SnippetMapper.toDTO(updatedSnippet);
    }

    /**
     * Delete a snippet by ID
     */
    @Transactional
    public void deleteSnippet(UUID id, UUID userId) {
        // Verify snippet exists
        Snippet snippet = snippetRepository.findByIdAndOwnerId(id, userId)
                .orElseThrow(() -> new SnippetNotFoundException("Snippet not found with id: " + id));
        snippetRepository.deleteById(id);

        // TODO: Emit Kafka event (snippet.deleted) when event system is implemented
    }

     // Toggle favorite status for a snippet
    @Transactional
    public SnippetResponseDTO toggleFavorite(UUID id, UUID userId) {
        Snippet snippet = snippetRepository.findByIdAndOwnerId(id, userId)
                .orElseThrow(() -> new SnippetNotFoundException("Snippet not found with id: " + id));

        snippet.setFavorite(!snippet.isFavorite());
        Snippet updatedSnippet = snippetRepository.save(snippet);

        return SnippetMapper.toDTO(updatedSnippet);
    }


    // Get all favorite snippets
    @Transactional(readOnly = true)
    public List<SnippetResponseDTO> getFavoriteSnippets(UUID userId) {
        List<Snippet> snippets = snippetRepository.findByOwnerIdAndFavoriteTrue(userId);
        return snippets.stream()
                .filter(Snippet::isFavorite)
                .map(SnippetMapper::toDTO)
                .collect(Collectors.toList());
    }


    // Search snippets by tag name
    @Transactional(readOnly = true)
    public List<SnippetResponseDTO> searchByTag(String tagName, UUID userId) {
        String normalizedTag = tagName.trim().toLowerCase();
        List<Snippet> snippets = snippetRepository.findByOwnerId(userId);

        return snippets.stream()
                .filter(snippet -> snippet.getTags().stream()
                        .anyMatch(tag -> tag.getName().equals(normalizedTag)))
                .map(SnippetMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Search snippets by language
     */
    @Transactional(readOnly = true)
    public List<SnippetResponseDTO> searchByLanguage(String language, UUID userId) {
        List<Snippet> snippets = snippetRepository.findByOwnerId(userId);

        return snippets.stream()
                .filter(snippet -> snippet.getLanguage().equalsIgnoreCase(language))
                .map(SnippetMapper::toDTO)
                .collect(Collectors.toList());
    }
}