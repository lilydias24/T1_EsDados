package src.controller;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class controllerLocacoes {
    public static void menuLocacoes(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println("\n _._._._._._._._._ MENU LOCAÇÕES _._._._._._._._._ \n");
            System.out.println("\t 1. Locar veículo");
            System.out.println("\t 2. Devolver veículo");
            System.out.println("\t 3. Listar locações ativas");
            System.out.println("\t 0. <- Voltar");
            System.out.println("\n _._._._._._._._._._._._._._._._._._._._._._._._._ \n");
            System.out.print(">> Escolha uma das opções acima : ");
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine();
                continue;
            }            
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\nCNH do cliente: ");
                    String cnh = scanner.nextLine();
                    System.out.print("Placa do veículo: ");
                    String placa = scanner.nextLine();
                    System.out.print("Data de retirada (AAAA-MM-DD): ");
                    LocalDate retirada = LocalDate.parse(scanner.nextLine());
                    System.out.print("Data de devolução (AAAA-MM-DD): ");
                    LocalDate devolucao = LocalDate.parse(scanner.nextLine());
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();
                    //Locacao.locarVeiculo(cnh, placa, retirada, devolucao, valor);
                    break;
                case 2:
                    System.out.print("\nPlaca do veículo para devolução: ");
                    String placaDevolver = scanner.nextLine();
                    //Locacao.devolverVeiculo(placaDevolver);
                    break;
                case 3:
                    //Locacao.listarLocacoesAtivas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);
    }
}
