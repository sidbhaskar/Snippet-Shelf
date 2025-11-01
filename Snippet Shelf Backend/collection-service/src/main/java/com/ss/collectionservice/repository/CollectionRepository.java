package com.ss.collectionservice.repository;

import com.ss.collectionservice.model.Collection;
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
public interface CollectionRepository extends JpaRepository<Collection, UUID> {

    /**
     * Find collections owned by a specific user
     */
    List<Collection> findByOwnerId(UUID ownerId);

    /**
     * Find a collection by ID and owner (for authorization)
     */
    Optional<Collection> findByIdAndOwnerId(UUID id, UUID ownerId);

    /**
     * Find collections owned by user OR shared with user
     * This is the main query for getting all accessible collections
     */
    @Query("""
        SELECT DISTINCT c FROM Collection c 
        LEFT JOIN c.members m 
        WHERE c.ownerId = :userId 
        OR m.userId = :userId
        ORDER BY c.updatedAt DESC
        """)
    List<Collection> findAccessibleCollections(@Param("userId") UUID userId);

    /**
     * Find team collections (for team dashboard)
     */
    List<Collection> findByTeamId(UUID teamId);

    /**
     * Find shared collections only
     */
    List<Collection> findByIsSharedTrue();

    /**
     * Check if user has access to a collection (either owner or member)
     */
    @Query("""
        SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END 
        FROM Collection c 
        LEFT JOIN c.members m 
        WHERE c.id = :collectionId 
        AND (c.ownerId = :userId OR m.userId = :userId)
        """)
    boolean hasAccess(@Param("collectionId") UUID collectionId, @Param("userId") UUID userId);

    /**
     * Count collections by owner
     */
    long countByOwnerId(UUID ownerId);
}