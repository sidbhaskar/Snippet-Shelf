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

    /**
     * Get all snippets (will be replaced with owner filtering when auth is implemented)
     */
    public List<SnippetResponseDTO> getSnippets() {
        List<Snippet> snippetsList = snippetRepository.findAll();
        return snippetsList.stream()
                .map(SnippetMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get snippets with pagination and sorting
     * @param sort - "recent" or "popular" (for now, only recent is implemented)
     * @param limit - number of snippets per page
     * @param offset - number of snippets to skip
     */
    public Page<SnippetResponseDTO> getSnippets(String sort, int limit, int offset) {
        Sort sortOrder = "recent".equals(sort)
                ? Sort.by(Sort.Direction.DESC, "createdDate")
                : Sort.by(Sort.Direction.DESC, "createdDate"); // TODO: implement "popular" sorting

        Pageable pageable = PageRequest.of(offset / limit, limit, sortOrder);
        Page<Snippet> snippetPage = snippetRepository.findAll(pageable);

        return snippetPage.map(SnippetMapper::toDTO);
    }

    /**
     * Get snippet by ID
     */
    public SnippetResponseDTO getSnippetById(UUID id) {
        Snippet snippet = snippetRepository.findById(id)
                .orElseThrow(() -> new SnippetNotFoundException("Snippet not found with id: " + id));
        return SnippetMapper.toDTO(snippet);
    }

    /**
     * Create a new snippet with tags
     */
    @Transactional
    public SnippetResponseDTO createSnippet(SnippetRequestDTO snippetRequestDTO) {
        // Convert DTO to entity
        Snippet snippet = SnippetMapper.toEntity(snippetRequestDTO);

        // TODO: Set owner_id from JWT token when auth is implemented
        // For now, using a default value
        snippet.setOwnerId(1L); // placeholder

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
    public SnippetResponseDTO updateSnippet(SnippetRequestDTO snippetRequestDTO, UUID id) {
        // Find existing snippet
        Snippet existingSnippet = snippetRepository.findById(id)
                .orElseThrow(() -> new SnippetNotFoundException("Snippet not found with id: " + id));

        // TODO: Verify ownership when auth is implemented
        // if (!existingSnippet.getOwnerId().equals(currentUserId)) throw new ForbiddenException();

        // Update snippet fields
        SnippetMapper.updateEntity(existingSnippet, snippetRequestDTO);

        // Update tags if provided
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
    public void deleteSnippet(UUID id) {
        // Verify snippet exists
        Snippet snippet = snippetRepository.findById(id)
                .orElseThrow(() -> new SnippetNotFoundException("Snippet not found with id: " + id));

        // TODO: Verify ownership when auth is implemented
        // if (!snippet.getOwnerId().equals(currentUserId)) throw new ForbiddenException();

        snippetRepository.deleteById(id);

        // TODO: Emit Kafka event (snippet.deleted) when event system is implemented
    }

    /**
     * Toggle favorite status for a snippet
     */
    @Transactional
    public SnippetResponseDTO toggleFavorite(UUID id) {
        Snippet snippet = snippetRepository.findById(id)
                .orElseThrow(() -> new SnippetNotFoundException("Snippet not found with id: " + id));

        snippet.setFavorite(!snippet.isFavorite());
        Snippet updatedSnippet = snippetRepository.save(snippet);

        return SnippetMapper.toDTO(updatedSnippet);
    }

    /**
     * Get all favorite snippets
     */
    public List<SnippetResponseDTO> getFavoriteSnippets() {
        List<Snippet> snippets = snippetRepository.findAll();
        return snippets.stream()
                .filter(Snippet::isFavorite)
                .map(SnippetMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Search snippets by tag name
     */
    public List<SnippetResponseDTO> searchByTag(String tagName) {
        // This will need a custom query in the repository
        // For now, filtering in memory (not recommended for production)
        String normalizedTag = tagName.trim().toLowerCase();
        List<Snippet> snippets = snippetRepository.findAll();

        return snippets.stream()
                .filter(snippet -> snippet.getTags().stream()
                        .anyMatch(tag -> tag.getName().equals(normalizedTag)))
                .map(SnippetMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Search snippets by language
     */
    public List<SnippetResponseDTO> searchByLanguage(String language) {
        List<Snippet> snippets = snippetRepository.findAll();

        return snippets.stream()
                .filter(snippet -> snippet.getLanguage().equalsIgnoreCase(language))
                .map(SnippetMapper::toDTO)
                .collect(Collectors.toList());
    }
}