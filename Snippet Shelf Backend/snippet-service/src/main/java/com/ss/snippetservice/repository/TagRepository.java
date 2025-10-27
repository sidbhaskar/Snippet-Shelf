package com.ss.snippetservice.repository;

import com.ss.snippetservice.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    Optional<Tag> findByName(String name);

    List<Tag> findAllByNameIn(Set<String> names);
}
