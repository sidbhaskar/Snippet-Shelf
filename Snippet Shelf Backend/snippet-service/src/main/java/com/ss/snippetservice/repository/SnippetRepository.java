package com.ss.snippetservice.repository;

import com.ss.snippetservice.model.Snippet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, UUID> {
    Page<Snippet> findByOwnerId(UUID ownerId, Pageable pageable);
    List<Snippet> findByOwnerId(UUID ownerId);
    Optional<Snippet> findByIdAndOwnerId(UUID id, UUID ownerId);
    List<Snippet> findByOwnerIdAndFavoriteTrue(UUID ownerId);
    Page<Snippet> findAll(Pageable pageable);
}
