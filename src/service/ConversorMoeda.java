package service;

import java.util.Map;

public class ConversorMoeda {

    public static double converteValor(double valor, String moedaOrigem, String moedaDestino, Map<String, Double> taxaDeCambio) {
        Double taxaOrigem = taxaDeCambio.get(moedaOrigem);
        Double taxaDestino = taxaDeCambio.get(moedaDestino);

        if (taxaOrigem == null || taxaDestino == null) {
            throw new IllegalArgumentException("Moeda de origem ou destino inválida.");
        }

        return valor * (taxaDestino / taxaOrigem);
    }
}