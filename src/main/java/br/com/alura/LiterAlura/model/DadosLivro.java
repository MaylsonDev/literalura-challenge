package br.com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        String title,
        List<Map<String, Object>> authors,
        List<String> summaries,
        List<String> subjects,
        List<String> languages,
        int download_count

) {
}
