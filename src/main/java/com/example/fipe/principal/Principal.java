package com.example.fipe.principal;

import com.example.fipe.model.*;
import com.example.fipe.service.ConverteDados;
import com.example.fipe.service.consumoAPI;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private consumoAPI consumoAPI = new consumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private String endereco = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        System.out.println("----------|  OPÇÕES  |-------------");
        System.out.println("Carros");
        System.out.println("Motos");
        System.out.println("Caminhoes");
        System.out.println("-----------------------------------");

        var nomeOpcao = sc.nextLine();
        nomeOpcao = nomeOpcao.toLowerCase();

        endereco = endereco + nomeOpcao + "/marcas/";
        var jsonMarcas = consumoAPI.obterDados(endereco);


        List<Dado> dadoMarcas = converteDados.obterLista(jsonMarcas, Dado.class);
        dadoMarcas.forEach(m -> System.out.println("Cód: " + m.codigo() + " | Descrição: " + m.nome()));

        System.out.println("Informe o código da marca para consulta: ");
        var codMarca = sc.nextLine();

        endereco = endereco + codMarca + "/modelos/";
        var jsonModelos = consumoAPI.obterDados(endereco);

        var modeloLista = converteDados.obterDados(jsonModelos, Modelos.class);
        modeloLista.modelos().forEach(System.out::println);

        System.out.println("Digite um trecho do nome do veículo: ");
        var trechoVeiculo =  sc.nextLine();


        List<Dado> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toUpperCase().contains(trechoVeiculo.toUpperCase()))
                .collect(Collectors.toList());
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o codigo do modelo par buscar os valores de avaliação: ");
        var codigoModelo =  sc.nextLine();

        endereco = endereco + codigoModelo + "/anos/";
        var jsonAnos = consumoAPI.obterDados(endereco);

        List<Dado> dadoAnos =  converteDados.obterLista(jsonAnos, Dado.class);

        List<Veiculo> veiculos = dadoAnos.stream()
                .map(a -> {
                    var jsonValor = consumoAPI.obterDados(endereco + a.codigo());
                    return converteDados.obterDados(jsonValor, Veiculo.class);})
                .toList();
        veiculos.forEach(System.out::println);
    }
}
