package com.example.fipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class ConverteDados implements IConverteDados{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(
                    json,
                    objectMapper
                        .getTypeFactory()
                        .constructCollectionType(List.class, classe)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(json,classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
