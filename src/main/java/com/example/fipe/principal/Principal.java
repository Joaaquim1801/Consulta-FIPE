package com.example.fipe.principal;

import com.example.fipe.model.DadoMarcas;
import com.example.fipe.model.DadoModelos;
import com.example.fipe.service.ConverteDados;
import com.example.fipe.service.consumoAPI;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private consumoAPI consumoAPI = new consumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        System.out.println("----------|  OPÇÕES  |-------------");
        System.out.println("Carros");
        System.out.println("Motos");
        System.out.println("Caminhoes");
        System.out.println("-----------------------------------");

        var nomeOpcao = sc.nextLine();
        nomeOpcao = nomeOpcao.toLowerCase();
        var jsonMarcas = consumoAPI.obterDados(ENDERECO + nomeOpcao + "/marcas");

        List<DadoMarcas> dadoMarcas = converteDados.obterDados(jsonMarcas, DadoMarcas.class);
        dadoMarcas.forEach(m -> System.out.println("Cód: " + m.codigo() + " | Descrição: " + m.nomeMarca()));

        System.out.println("Informe o código da marca para consulta: ");
        var codMarca = sc.nextLine();
        var jsonModelos = consumoAPI.obterDados(ENDERECO + nomeOpcao + "/marcas/" + codMarca + "/modelos");
        System.out.println(jsonModelos);

        List<DadoModelos> dadoModelos = converteDados.obterDadosLista(jsonModelos, "modelos",DadoModelos.class);
        System.out.println(dadoModelos);

    }
}
