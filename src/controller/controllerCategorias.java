package src.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import src.model.Categoria;

public class controllerCategorias {

    public static void menuCategorias(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println("\n ._._._._._._._._. MENU CATEGORIAS ._._._._._._._._. \n");
            System.out.println("\t 1. Inserir categoria");
            System.out.println("\t 2. Excluir categoria");
            System.out.println("\t 3. Editar categoria");
            System.out.println("\t 4. Listar categorias");
            System.out.println("\t 0. <- Voltar");
            System.out.println("\n ._._._._._._._._._._._._._._._._._._._._._._._._. \n");
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
                    System.out.print("\nNome da categoria: ");
                    String nome = scanner.nextLine();
                    System.out.print("Identificador: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Categoria.cadastrarCategoria(nome, id);
                    break;
                case 2:
                    System.out.print("\nIdentificador da categoria a remover: ");
                    int idRemover = scanner.nextInt();
                    scanner.nextLine();
                    Categoria.removerCategoria(idRemover);
                    break;
                case 3:
                    System.out.print("\nIdentificador da categoria a editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    Categoria.editarCategoria(idEditar, novoNome);
                    break;
                case 4:
                    Categoria.listarCategorias(true);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);
    }
}
