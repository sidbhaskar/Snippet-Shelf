package com.ss.snippetservice.mapper;

import com.ss.snippetservice.dto.SnippetRequestDTO;
import com.ss.snippetservice.dto.SnippetResponseDTO;
import com.ss.snippetservice.model.Snippet;
import com.ss.snippetservice.model.Tag;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SnippetMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Convert Snippet entity to SnippetResponseDTO
     */
    public static SnippetResponseDTO toDTO(Snippet snippet) {
        if (snippet == null) return null;

        SnippetResponseDTO dto = new SnippetResponseDTO();
        dto.setId(snippet.getId().toString());
        dto.setTitle(snippet.getTitle());
        dto.setDescription(snippet.getDescription());
        dto.setLanguage(snippet.getLanguage());
        dto.setSourceCode(snippet.getSourceCode());
        dto.setFavorite(snippet.isFavorite());
        dto.setCreatedDate(snippet.getCreatedDate() != null
                ? snippet.getCreatedDate().format(DATE_FORMATTER)
                : null);

        // Map tags to list of strings
        dto.setTags(mapTagsToStringList(snippet.getTags()));

        return dto;
    }

    /**
     * Convert SnippetRequestDTO to Snippet entity (without tags)
     * Tags should be set separately using TagService
     */
    public static Snippet toEntity(SnippetRequestDTO dto) {
        if (dto == null) return null;

        Snippet snippet = new Snippet();
        snippet.setTitle(dto.getTitle());
        snippet.setDescription(dto.getDescription());
        snippet.setLanguage(dto.getLanguage());
        snippet.setSourceCode(dto.getSourceCode());
        snippet.setFavorite(dto.isFavorite());
        snippet.setCreatedDate(java.time.LocalDate.now());

        // Note: Tags are not set here - they should be handled by the service layer
        // using TagService.getOrCreateTags()

        return snippet;
    }

    /**
     * Update existing Snippet entity from SnippetRequestDTO
     */
    public static void updateEntity(Snippet snippet, SnippetRequestDTO dto) {
        if (snippet == null || dto == null) return;

        snippet.setTitle(dto.getTitle());
        snippet.setDescription(dto.getDescription());
        snippet.setLanguage(dto.getLanguage());
        snippet.setSourceCode(dto.getSourceCode());
        snippet.setFavorite(dto.isFavorite());

        // Note: Tags should be updated separately in the service layer
    }

    /**
     * Helper method to convert Set<Tag> to List<String>
     */
    private static List<String> mapTagsToStringList(Set<Tag> tags) {
        if (tags == null) return List.of();
        return tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
    }
}