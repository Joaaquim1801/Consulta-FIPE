package com.example.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadoModelos(@JsonAlias("codigo") String codigo,
                          @JsonAlias("nome") String nomeModelo) {
}
