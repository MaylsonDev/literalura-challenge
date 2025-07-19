package br.com.alura.LiterAlura.service;

import br.com.alura.LiterAlura.model.DadosLivro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;


import java.util.List;

public class ConverteDados implements IConverteDados{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json,classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class,classe);

        try {
            return mapper.readValue(json,lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public List<DadosLivro> obterListaDeLivros(String json) {
        try {
            JsonNode root = mapper.readTree(json);
            JsonNode resultsNode = root.get("results");

            if (resultsNode == null || !resultsNode.isArray()) {
                throw new RuntimeException("Campo 'results' não encontrado ou não é um array");
            }

            CollectionType listaType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, DadosLivro.class);

            return mapper.readValue(resultsNode.toString(), listaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON do campo 'results'", e);
        }
    }

}
