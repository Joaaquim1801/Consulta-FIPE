package com.example.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;


public record DadoMarcas(@JsonAlias("codigo") String codigo,
                         @JsonAlias("nome") String nomeMarca) {
}
