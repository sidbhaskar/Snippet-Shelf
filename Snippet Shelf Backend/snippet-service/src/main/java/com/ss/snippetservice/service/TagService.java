package com.ss.snippetservice.service;

import com.ss.snippetservice.model.Tag;
import com.ss.snippetservice.repository.TagRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Get or create tags from a collection of tag names.
     * Normalizes tag names (lowercase, trimmed) and handles deduplication.
     */
    @Transactional
    public Set<Tag> getOrCreateTags(Collection<String> names) {
        if (names == null || names.isEmpty()) {
            return new HashSet<>();
        }

        // Normalize tag names: lowercase and trim
        Set<String> normalized = names.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());

        if (normalized.isEmpty()) {
            return new HashSet<>();
        }

        // Find existing tags
        Map<String, Tag> existing = tagRepository.findAllByNameIn(normalized).stream()
                .collect(Collectors.toMap(Tag::getName, Function.identity()));

        Set<Tag> result = new HashSet<>(existing.values());

        // Create new tags for names that don't exist
        normalized.removeAll(existing.keySet());
        for (String tagName : normalized) {
            try {
                Tag newTag = new Tag();
                newTag.setName(tagName);
                result.add(tagRepository.save(newTag));
            } catch (DataIntegrityViolationException e) {
                // Handle race condition: tag was created by another transaction
                // Fetch the existing tag instead
                tagRepository.findByName(tagName).ifPresent(result::add);
            }
        }

        return result;
    }

    /**
     * Get all tags
     */
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    /**
     * Find tag by name
     */
    public Optional<Tag> findByName(String name) {
        String normalized = name.trim().toLowerCase();
        return tagRepository.findByName(normalized);
    }
}