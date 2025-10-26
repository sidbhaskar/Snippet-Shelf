package mapper;


import com.ss.snippetservice.dto.SnippetRequestDTO;
import com.ss.snippetservice.dto.SnippetResponseDTO;
import com.ss.snippetservice.model.Snippet;

import java.time.format.DateTimeFormatter;

public class SnippetMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static SnippetResponseDTO toDTO(Snippet snippet) {
        if (snippet == null) return null;

        SnippetResponseDTO dto = new SnippetResponseDTO();
        dto.setId(snippet.getId().toString());
        dto.setTitle(snippet.getTitle());
        dto.setDescription(snippet.getDescription());
        dto.setLanguage(snippet.getLanguage());
        dto.setSourceCode(snippet.getSourceCode());
        dto.setCreatedDate(snippet.getCreatedDate() != null ? snippet.getCreatedDate().format(DATE_FORMATTER) : null);

        return dto;
    }

    public static Snippet toEntity(SnippetRequestDTO dto) {
        if (dto == null) return null;

        Snippet snippet = new Snippet();
        snippet.setTitle(dto.getTitle());
        snippet.setDescription(dto.getDescription());
        snippet.setLanguage(dto.getLanguage());
        snippet.setSourceCode(dto.getSourceCode());
        snippet.setCreatedDate(java.time.LocalDate.now());

        return snippet;
    }
}
