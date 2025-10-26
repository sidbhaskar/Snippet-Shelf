package com.ss.snippetservice.repository;

import com.ss.snippetservice.model.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, UUID> {

}
