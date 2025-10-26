package com.ss.snippetservice.service;

import com.ss.snippetservice.dto.SnippetRequestDTO;
import com.ss.snippetservice.dto.SnippetResponseDTO;
import com.ss.snippetservice.model.Snippet;
import com.ss.snippetservice.repository.SnippetRepository;
import mapper.SnippetMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SnippetService {

    private final SnippetRepository snippetRepository;

    public SnippetService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    public List<SnippetResponseDTO> getSnippets() {
        List<Snippet> snippetsList = snippetRepository.findAll();
        return snippetsList.stream().map(SnippetMapper::toDTO).toList();
    }


    public SnippetResponseDTO createSnippet(SnippetRequestDTO snippetRequestDTO) {
        Snippet newSnippet =snippetRepository.save(SnippetMapper.toEntity(snippetRequestDTO));
        return SnippetMapper.toDTO(newSnippet);
    }

    public SnippetResponseDTO updateSnippet(SnippetRequestDTO snippetRequestDTO, UUID id) {
        Snippet requestSnippet = snippetRepository.findById(id).orElse(null);
        if (requestSnippet == null) {
            return null;
        }
        requestSnippet.setTitle(snippetRequestDTO.getTitle());
        requestSnippet.setDescription(snippetRequestDTO.getDescription());
        requestSnippet.setLanguage(snippetRequestDTO.getLanguage());
        requestSnippet.setSourceCode(snippetRequestDTO.getSourceCode());

        Snippet updatedSnippet = snippetRepository.save(requestSnippet);
        return SnippetMapper.toDTO(updatedSnippet);
    }

    public void deleteSnippet(UUID id) {
        snippetRepository.deleteById(id);
    }
}
