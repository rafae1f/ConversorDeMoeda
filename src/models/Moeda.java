package models;

import java.util.Map;

public class Moeda {
    private String codigo;
    private static Map<String, Double> taxaDeCambio;

    public Moeda(String codigo, Map<String, Double> taxaDeCambio) {
        this.codigo = codigo;
        Moeda.taxaDeCambio = taxaDeCambio;
    }

    public Moeda(MoedaExchangeRate moedaExchangeRate) {
        codigo = moedaExchangeRate.base_code();
        taxaDeCambio = moedaExchangeRate.conversion_rates();
    }

    public static void menuMoedas() {
        System.out.println(
                """
                        1 - ARS - Peso argentino
                        2 - BOB - Boliviano boliviano
                        3 - BRL - Real brasileiro
                        4 - CLP - Peso chileno
                        5 - COP - Peso colombiano
                        6 - USD - Dólar americano
                        0 - Sair"""
        );
    }

    @Override
    public String toString() {
        return "Moeda de origem: " + codigo;
    }

    public static Map<String, Double> getTaxaDeCambio() {
        return taxaDeCambio;
    }
}