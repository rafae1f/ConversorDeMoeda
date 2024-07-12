import service.ConversorMoeda;
import models.ConsultaMoeda;
import models.Moeda;

import java.util.Scanner;
import static models.Moeda.menuMoedas;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        String opcao = "";

        while(!opcao.equals("0")) {
            exibirMenu();
            opcao = leitura.nextLine();

            if (!opcao.equals("0")) {
                String moedaOrigem = selecionarMoeda("origem", opcao);
                if (moedaOrigem.isEmpty()) {
                    continue;
                }

                System.out.print("Digite o valor a ser convertido: ");
                double valor;
                try {
                    valor = leitura.nextDouble();
                } catch (Exception e) {
                    System.out.println("Valor inválido. Por favor, insira um número válido.");
                    leitura.nextLine();
                    continue;
                }

                leitura.nextLine();

                String moedaDestino = selecionarMoeda("destino", exibirMenuMoedas(leitura));
                if (moedaDestino.isEmpty()) {
                    continue;
                }

                double resultado = ConversorMoeda.converteValor(valor, moedaOrigem, moedaDestino, Moeda.getTaxaDeCambio());
                System.out.printf("%s = %.2f -> %s = %.2f\n", moedaOrigem, valor, moedaDestino, resultado);

                System.out.println("Deseja realizar outra conversão? (0 - Sair)");
                opcao = leitura.nextLine();
                if (opcao.equals("0")) {
                    System.out.println("O programa finalizou corretamente!");
                }
            } else {
                System.out.println("O programa finalizou corretamente!");
            }
        }

        leitura.close();
    }

    private static void exibirMenu() {
        System.out.println("""
                        ************************************************************
                                    Bem-vindo ao Conversor de Moedas
                        ************************************************************

                        Selecione a moeda de origem:"""
        );
        menuMoedas();
    }

    private static String exibirMenuMoedas(Scanner leitura) {
        System.out.println("Selecione a moeda de destino:");
        menuMoedas();
        return leitura.nextLine();
    }

    private static String selecionarMoeda(String tipo, String opcao) {
        String codigo = obterCodigoMoeda(opcao);

        if (codigo.isEmpty()) {
            System.out.println("Opção inválida, tente novamente.");
        } else {
            if (tipo.equals("origem")) {
                ConsultaMoeda consultaMoeda = new ConsultaMoeda();
                Moeda minhaMoeda = consultaMoeda.consultaMoeda(codigo);
                if (minhaMoeda == null) {
                    System.out.println("Moeda de origem não encontrada.");
                    return "";
                }
                System.out.println(minhaMoeda);
            } else {
                System.out.println("Moeda de destino: " + codigo);
            }
        }
        return codigo;
    }

    private static String obterCodigoMoeda(String opcao) {
        return switch (opcao) {
            case "1" -> "ARS";
            case "2" -> "BOB";
            case "3" -> "BRL";
            case "4" -> "CLP";
            case "5" -> "COP";
            case "6" -> "USD";
            default -> "";
        };
    }
}
