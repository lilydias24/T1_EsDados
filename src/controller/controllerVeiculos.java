package src.controller;
import java.util.InputMismatchException;
import java.util.Scanner;

import src.model.Categoria;
import src.model.Veiculo;

public class controllerVeiculos {

    public static void menuVeiculos(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println("\n .-.-.-.-.-.-.-.-. MENU VEÍCULOS .-.-.-.-.-.-.-.-. \n");
            System.out.println("\t 1. Inserir veículo");
            System.out.println("\t 2. Excluir veículo");
            System.out.println("\t 3. Editar veículo");
            System.out.println("\t 4. Listar veículos (início -> fim)");
            System.out.println("\t 5. Listar veículos (fim -> início)");
            System.out.println("\t 0. <- Voltar");
            System.out.println("\n .-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-. \n");
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
                    System.out.print("\nPlaca: ");
                    String placa = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();
                    System.out.print("Potência: ");
                    int potencia = scanner.nextInt();
                    System.out.print("Número de lugares: ");
                    int lugares = scanner.nextInt();
                    System.out.print("ID da Categoria: ");
                    int idCategoria = scanner.nextInt();
                    scanner.nextLine();
                    Categoria categoria = Categoria.buscarCategoria(idCategoria);
                    
                    if (categoria != null) {
                        Veiculo.cadastrarVeiculo(placa, modelo, marca, ano, potencia, lugares, categoria);
                    } else {
                        System.out.println("\nCategoria não encontrada!");
                    }
                    
                    break;
                case 2:
                    System.out.print("\nPlaca do veículo a remover: ");
                    String placaRemover = scanner.nextLine();
                    Veiculo.removerVeiculo(placaRemover);
                    break;
                case 3:
                    System.out.print("\nPlaca do veículo a editar: ");
                    String placaEditar = scanner.nextLine();
                    System.out.print("Novo modelo: ");
                    String novoModelo = scanner.nextLine();
                    System.out.print("Nova marca: ");
                    String novaMarca = scanner.nextLine();
                    System.out.print("Novo ano: ");
                    int novoAno = scanner.nextInt();
                    System.out.print("Nova potência: ");
                    int novaPotencia = scanner.nextInt();
                    System.out.print("Novo número de lugares: ");
                    int novosLugares = scanner.nextInt();
                    System.out.print("Nova categoria (ID): ");
                    int novaCategoriaId = scanner.nextInt();
                    scanner.nextLine();
                    Categoria novaCategoria = Categoria.buscarCategoria(novaCategoriaId);
                    
                    if (novaCategoria != null) {
                        Veiculo.editarVeiculo(placaEditar, novoModelo, novaMarca, novoAno, novaPotencia, novosLugares, novaCategoria);
                    } else {
                        System.out.println("\nCategoria não encontrada!");
                    }
                    break;
                case 4:
                    Veiculo.listarVeiculos(true);
                    break;
                case 5:
                    Veiculo.listarVeiculos(false);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);
    }
}