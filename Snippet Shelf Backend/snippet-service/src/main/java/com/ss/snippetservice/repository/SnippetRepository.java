package com.ss.snippetservice.repository;

import com.ss.snippetservice.model.Snippet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, UUID> {
    // For future auth implementation - filter by owner
    Page<Snippet> findByOwnerId(Long ownerId, Pageable pageable);

    Optional<Snippet> findByIdAndOwnerId(UUID id, Long ownerId);

    // Find all snippets (for now, without owner filtering)
    Page<Snippet> findAll(Pageable pageable);
}
