package com.ss.snippetservice.repository;

import com.ss.snippetservice.model.Snippet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    long countByOwnerId(UUID ownerId);
    long countByOwnerIdAndFavoriteTrue(UUID ownerId);

    @Query("SELECT s.language, COUNT(s) FROM Snippet s WHERE s.ownerId = :ownerId GROUP BY s.language ORDER BY COUNT(s) DESC")
    List<Object[]> countSnippetsByLanguage(@Param("ownerId") UUID ownerId);

    @Query("SELECT COUNT(DISTINCT t) FROM Snippet s JOIN s.tags t WHERE s.ownerId = :ownerId")
    long countDistinctTagsByOwnerId(@Param("ownerId") UUID ownerId);
}
