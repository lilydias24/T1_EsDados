package src.controller;
import java.util.InputMismatchException;
import java.util.Scanner;

import src.model.Cliente;

public class controllerClientes {

        public static String formatarCPF(String cpf) {
        try {
            if (cpf == null) return null;
            cpf = cpf.replaceAll("\\D", ""); // remove tudo que não é número
            if (cpf.length() != 11) {
                throw new IllegalArgumentException("CPF inválido! Deve conter 11 dígitos.");
            }
            return cpf.substring(0, 3) + "." +
                   cpf.substring(3, 6) + "." +
                   cpf.substring(6, 9) + "-" +
                   cpf.substring(9);
        } catch (Exception e) {
            System.out.println("Erro ao formatar CPF: " + e.getMessage());
            return null;
        }
    }

    public static String formatarCNH(String cnh) {
        try {
            if (cnh == null) return null;
            cnh = cnh.replaceAll("\\D", "");
            if (cnh.length() < 7 || cnh.length() > 11) {
                throw new IllegalArgumentException("CNH inválida! Deve conter entre 7 e 11 dígitos.");
            }
            return cnh;
        } catch (Exception e) {
            System.out.println("Erro ao formatar CNH: " + e.getMessage());
            return null;
        }
    }

    public static String formatarTelefone(String telefone) {
        try {
            if (telefone == null) return null;
            telefone = telefone.replaceAll("\\D", "");
            if (telefone.length() != 10 && telefone.length() != 11) {
                throw new IllegalArgumentException("Telefone inválido! Deve conter 10 ou 11 dígitos.");
            }
            return telefone;
        } catch (Exception e) {
            System.out.println("Erro ao formatar telefone: " + e.getMessage());
            return null;
        }
    }
    
    public static void menuClientes(Scanner scanner) {
        int opcao = -1;
        do{
            System.out.println("\n -.-.-.-.-.-.-.-.- MENU CLIENTES -.-.-.-.-.-.-.-.- \n");
            System.out.println("\t 1. Inserir cliente");
            System.out.println("\t 2. Excluir cliente");
            System.out.println("\t 3. Editar cliente");
            System.out.println("\t 4. Listar clientes (início -> fim)");
            System.out.println("\t 5. Listar clientes (fim -> início)");
            System.out.println("\t 0. <- Voltar");
            System.out.println("\n -.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.- \n");
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
                    System.out.print("\nNome: ");
                    String nome = scanner.nextLine();
                
                    System.out.print("CNH: ");
                    String cnhEntrada = scanner.nextLine();
                    String cnh = formatarCNH(cnhEntrada);
                    if (cnh == null) {
                        System.out.println("CNH inválida. Cancelando cadastro.");
                        break;
                    }
                
                    System.out.print("Telefone: ");
                    String telefoneEntrada = scanner.nextLine();
                    String telefone = formatarTelefone(telefoneEntrada);
                    if (telefone == null) {
                        System.out.println("Telefone inválido. Cancelando cadastro.");
                        break;
                    }

                    System.out.print("CPF (somente números): ");
                    String cpfEntrada = scanner.nextLine();
                    String cpf = formatarCPF(cpfEntrada);
                    if (cpf == null) {
                        System.out.println("Não foi possível cadastrar o cliente. CPF inválido.");
                        break;
                    }
                
                    Cliente.cadastrarCliente(nome, cnh, telefone, cpf);
                
                    break;
                case 2:
                    System.out.print("\nCPF do cliente a remover: ");
                    String cpfRemoverEntrada = scanner.nextLine();
                    String cpfRemover = formatarCPF(cpfRemoverEntrada);
                    if (cpfRemover == null) {
                        System.out.println("CPF inválido. Cancelando remoção.");
                        break;
                    }
                    Cliente.removerCliente(cpfRemover);
                    break;
                case 3:
                    System.out.print("\nCPF do cliente a editar: ");
                    String cpfEditarEntrada = scanner.nextLine();
                    String cpfEditar = formatarCPF(cpfEditarEntrada);
                    if (cpfEditar == null) {
                        System.out.println("CPF inválido. Cancelando edição.");
                        break;
                    }
                
                    System.out.print("Novo nome: ");
                    String nomeNovo = scanner.nextLine();
                
                    System.out.print("Nova CNH: ");
                    String cnhNovaEntrada = scanner.nextLine();
                    String cnhNova = formatarCNH(cnhNovaEntrada);
                    if (cnhNova == null) {
                        System.out.println("CNH inválida. Cancelando cadastro.");
                        break;
                    }
                
                    System.out.print("Novo telefone: ");
                    String telefoneNovoEntrada = scanner.nextLine();
                    String telefoneNovo = formatarTelefone(telefoneNovoEntrada);
                    if (telefoneNovo == null) {
                        System.out.println("Telefone inválido. Cancelando cadastro.");
                        break;
                    }                    
                    Cliente.editarCliente(cpfEditar, nomeNovo, cnhNova, telefoneNovo);
                    break;
                case 4:
                    Cliente.listarClientes(true);
                    break;
                case 5:
                    Cliente.listarClientes(false);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida! ");
            }
        } while (opcao != 0);
    }
}
    

